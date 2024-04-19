package org.unix.minnow;

import org.unix.Database;
import simple.hooks.queries.SimpleEntityQuery;
import simple.hooks.scripts.Category;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.simplebot.ChatMessage;
import simple.hooks.wrappers.SimpleNpc;
import simple.hooks.wrappers.SimplePlayer;
import simple.robot.api.ClientContext;
import simple.robot.script.Script;

import java.awt.*;

import static org.unix.Database.*;

@ScriptManifest(discord = "", version = "1", servers = {"Zenyte"}, name = "uMinnow", category = Category.FISHING, author = "unix", description = "Fishes Minnows... duh!")
public class uMinnow extends Script {

    boolean swap = false;

    ClientContext c;
    SimplePlayer p;
    int anim;
    SimpleEntityQuery<SimpleNpc> seq;
    int startCount = -1;

    @Override
    public void onExecute() {
        c = ClientContext.instance();
        try {
            startCount = c.inventory.populate().filter(food.MINNOW).next().getQuantity();
        } catch (Exception e) {
            startCount = 0;
        }
    }

    @Override
    public void onChatMessage(ChatMessage chatMessage) {
        if (chatMessage.getMessage().toLowerCase().equals("a flying fish gobbles up some of your minnows.")) {
            System.out.println("Swapping!");
            swap = true;
        }
    }

    @Override
    public void onProcess() {
        p = c.players.getLocal();
        anim = p.getAnimation();
        if (swap) {
            seq = c.npcs.populate().filter(objects.FISHING_MINNOW);
            if (seq.isEmpty()) {
                System.out.println("Somethings very wrong! shutting down...");
                c.sendLogout();
                c.stopScript();
                return;
            }

            SimpleNpc n = seq.nearest().next();
            n = seq.next();
            if (n != null) {
                if (!n.visibleOnScreen()) {
                    System.out.println("Turning...");
                    n.turnTo();
                    return;
                }
                n.click(0);
                swap = false;
                c.sleepCondition(() -> anim == animations.FISHING, 1500);
                return;
            }
            return;
        }

        if (!p.isAnimating()) {
            seq = c.npcs.populate().filter(objects.FISHING_MINNOW);
            if (seq.isEmpty()) {
                System.out.println("Something is very wrong...!");
                c.sendLogout();
                c.stopScript();
                return;
            }

            SimpleNpc n = seq.nearest().nearest().next();
            if (n != null) {
                if (!n.visibleOnScreen()) {
                    System.out.println("Turning...");
                    n.turnTo();
                    return;
                }
                c.sleep(1000);
                n.click(0);
                c.sleepCondition(() -> anim == animations.FISHING, 2000);
                return;
            }
            return;
        }


        c.sleepCondition(() -> !p.isAnimating(), 1000);
    }

    @Override
    public void onTerminate() {
        System.out.println("Finishing Script!");
        System.out.println("Thank you for using uMinnow, you Minnow!");
        System.out.println("Total catch: " + (c.inventory.populate().filter(food.MINNOW).next().getQuantity() - startCount));
    }

    @Override
    public void paint(Graphics graphics) {

    }

    public class ScriptController {

    }
}
