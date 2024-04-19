package org.unix.mlm;

import net.runelite.api.Point;
import net.runelite.api.coords.WorldArea;
import net.runelite.api.coords.WorldPoint;
import simple.hooks.queries.SimpleEntityQuery;
import simple.hooks.scripts.Category;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.simplebot.ChatMessage;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.script.Script;

import java.awt.*;

@ScriptManifest(description = "Simple Motherload Mine Script", version = "1", servers = {"Zaros"}, discord = "", category = Category.MINING, author = "unix", name = "uMLM")
public class uMLM extends Script {

    //(3760, 5666, 0),  (3748, 5674, 0), (3733, 5679, 0), (3734, 5689, 0)
    WorldPoint[] path = new WorldPoint[] {
            new WorldPoint(3760, 5666, 0),
            new WorldPoint(3749, 5674, 0),
            new WorldPoint(3733, 5680, 0),
            new WorldPoint(3734, 5689, 0)
    };

    boolean needCollection = false;

    int deposit = 26674;
    int withdraw = 26677;
    int bankchest = 26707;

    int[] notedOre = {
            439, 441, 443, 445, 448, 450, 452
    };

    int[] objectID = {26662, 26663, 26664};

    WorldPoint loc;

    public WorldArea miningArea = new WorldArea(3731, 5687, 20, 7, 0);


    @Override
    public void onChatMessage(ChatMessage chatMessage) {

    }

    @Override
    public void onTerminate() {

    }

    @Override
    public void onProcess() {
        loc = ctx.players.getLocal().getLocation();

        if (!ctx.inventory.populate().filter(notedOre).isEmpty() && needCollection)
            needCollection = false;

        if (ctx.inventory.populate().size() == 28) {
            if (miningArea.contains(loc)) {
                ctx.pathing.step(path[path.length-2]);
                ctx.sleepCondition(() -> ctx.players.getLocal().getLocation().equals(path[path.length-1]), 2500);
            } else if (ctx.objects.populate().filter(deposit).next().getLocation().distanceTo(loc) < 3) {
                SimpleEntityQuery<SimpleObject> soq = ctx.objects.populate().filter(deposit);
                if (soq.isEmpty()) { ctx.stopScript(); }
                SimpleObject so = soq.next();
                if (!so.visibleOnScreen()) {
                    so.turnTo();
                    return;
                }
                so.click(0);
                ctx.sleepCondition(() -> ctx.inventory.populate().size() != 28, 2500);
                needCollection = true;
            } else {
                for (int i = path.length-1; i > -1; i--) {
                    if (loc.equals(path[i])) {
                        ctx.pathing.step(path[i-1]);
                        int finalI = i;
                        ctx.sleepCondition(() -> loc.equals(path[finalI -1]), 2500);
                    }
                }
            }
        } else if (needCollection) {
            SimpleObject so = ctx.objects.populate().filter(withdraw).next();
            if (so == null) {
                ctx.stopScript();
            }
            if (!so.visibleOnScreen()) {
                so.turnTo();
                return;
            }
            so.click(0);
            ctx.sleepCondition(() -> !ctx.inventory.populate().filter(notedOre).isEmpty(), 2500);

        } else if (!ctx.inventory.populate().filter(notedOre).isEmpty()) {
            if (ctx.bank.bankOpen()) {
                ctx.bank.depositInventory();
                ctx.sleepCondition(() -> ctx.inventory.populate().isEmpty(), 2500);
                needCollection = false;
            } else {
                SimpleObject so = ctx.objects.populate().filter(bankchest).next();
                if (so == null) { ctx.stopScript(); }
                if (!so.visibleOnScreen()) {
                    so.clickOnMinimap();
                    return;
                }
                so.click("Use");
                ctx.sleepCondition(() -> ctx.bank.bankOpen(), 2500);
            }
        } else if (miningArea.contains(loc)) {
            if (ctx.players.getLocal().getAnimation() != -1) { return; }
            ctx.objects.populate().filter(objectID).nearest().next().click(0);
            ctx.sleep(2500);
        } else {
            for (int i = 0; i < path.length; i++) {
                if (loc.equals(path[i])) {
                    ctx.pathing.step(path[i+1]);
                    int finalI = i;
                    ctx.sleepCondition(() -> loc.equals(path[finalI +1]), 2500);
                    return;
                }
            }
        }
    }

    @Override
    public void onExecute() {
    }

    @Override
    public void paint(Graphics graphics) {

    }
}
