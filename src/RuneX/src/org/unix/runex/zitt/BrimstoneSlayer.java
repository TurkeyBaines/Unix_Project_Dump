package org.unix.runex.zitt;

import org.unix.runex.zitt.task.AbyssalDemons;
import org.unix.runex.zitt.task.Jellys;
import org.unix.runex.zitt.task.Task;
import simple.api.coords.WorldPoint;
import simple.api.events.ChatMessageEvent;
import simple.api.listeners.SimpleMessageListener;
import simple.api.queries.SimpleEntityQuery;
import simple.api.script.Category;
import simple.api.script.Script;
import simple.api.script.ScriptManifest;
import simple.api.script.interfaces.SimplePaintable;
import simple.api.wrappers.SimpleSceneObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

@ScriptManifest(author = "unix", name = "BrimSlayer", description = "private script for Zitt", servers = { "RuneX" }, version = "1", discord = "", category = Category.SLAYER)
public class BrimstoneSlayer extends Script implements SimplePaintable, SimpleMessageListener {

    public static boolean cannonInInventory = true;
    public static boolean cannonFueled = false;

    int a = 0;
    boolean accept = true;
    String RTask;
    Task task;
    int totalSkippedTasks = 0;
    int taskAmount = 0;
    public static int totalItemsLooted = 0;
    public static int abyssalTasks = 0;
    public static int jellyKills = 0;
    public static int abyssalKills = 0;
    public static int jellyTasks = 0;

    long startTime;

    @Override
    public boolean onExecute() {
        startTime = System.currentTimeMillis();
        //if (ctx.inventory.populate().filter(23212).size() > 0) {
            //System.out.println("we got an urn");
            //hasUrn = true;
        //}
        return true;
    }

    @Override
    public void onProcess() {

        RTask = ctx.widgets.populate().filter(16042).next().getText().split("@gre@")[1].toLowerCase();
        if (RTask.contains("none")) {
            if (collectCannon()) {
                return;
            }

            if (Constants.DEMON_AREA.containsPoint(ctx.players.getLocal().getLocation())) {
                ctx.combat.toggleAutoRetaliate(false);
                ctx.pathing.step(new WorldPoint(1672, 10088, 0));
            } else if (Constants.JELLY_AREA.containsPoint(ctx.players.getLocal().getLocation())) {
                ctx.combat.toggleAutoRetaliate(false);
                ctx.pathing.step(new WorldPoint(1691, 10011, 0));
                sleep(1000);
            } else {
                getNewTask();
                sleep(250);
                return;
            }
        }

        if (isAcceptableTask()) {
            refuelCannon();
            task.processTask();
            sleep(250);
        } else {
            permaRemoval();
            permaTask(1);
            sleep(250);
            totalSkippedTasks+=1;
        }
    }

    @Override
    public void onTerminate() {

    }


    //START: Code generated using Enfilade's Easel
    private Image getImage(String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch(IOException e) {
            return null;
        }
    }

    private final Color color1 = new Color(0, 0, 0);

    private final Font font1 = new Font("Arial", 0, 20);

    private final Image img1 = getImage("https://i.ibb.co/YR2H3vC/0QpIz5m.png");

    @Override
    public void onPaint(Graphics2D g1) {
        Graphics2D g = (Graphics2D)g1;
        g.drawImage(img1, -1, -2, null);
        g.setFont(font1);
        g.setColor(color1);
        g.drawString("" + formatTimeRunning(startTime), 174, 391);
        g.drawString("" + formatCannonText(), 174, 448);
    }
    //END: Code generated using Enfilade's Easel

    public String formatTimeRunning(long st) {
        long running = System.currentTimeMillis() - st;
        long millis = running % 1000;
        long second = (running / 1000) % 60;
        long minute = (running / (1000 * 60)) % 60;
        long hour = (running / (1000 * 60 * 60)) % 24;

        return String.format("%02d:%02d:%02d.%d", hour, minute, second, millis);
    }

    public String formatCannonText() {
        String a = "";
        if (cannonInInventory) {
            a = "Inventory | ";
        } else {
            a = "On Ground | ";
        }

        if (cannonFueled) {
            a = a + "Is Firing";
        } else {
            a = a + "Is Empty";
        }

        return a;
    }

    public void getNewTask() {
        ctx.menuActions.sendAction(315, 1017, 248, 4196);
        ctx.sleep(250);
        ctx.menuActions.sendAction(315, 1017, 248, 54113);
        ctx.sleep(500);
    }

    public void cancelTask(int method) {
        if (method == 0) {
            // Slayer Points
        } else {
            ctx.menuActions.sendAction(315, 1422, 233, 41016);
            ctx.sleep(250);
            ctx.menuActions.sendAction(315, 821, 227, 2462);
            ctx.sleep(250);
        }
    }

    public void permaTask(int method) {
        ctx.menuActions.sendAction(315, 688, 0, 41017);
        ctx.sleep(250);
        ctx.menuActions.sendAction(315, 1422, 0, 2462);
        ctx.sleep(250);
    }

    public void permaRemoval() {
        ctx.menuActions.sendAction(315, 1329, 104, 41026);
        ctx.sleep(250);
    }

    public void sleep(int ms) {
        ctx.sleep(ms);
    }

    public boolean isAcceptableTask() {
        if (RTask.contains("abyssal") && RTask.contains("kourend")) { accept = true; task = new AbyssalDemons();
        }
        else if (RTask.contains("jelly") && RTask.contains("kourend")) { accept = true; task = new Jellys();
        }
        else { accept = false; }

        return accept;
    }

    public boolean collectCannon() {
        SimpleEntityQuery<SimpleSceneObject> seq = ctx.objects.populate().filter("Dwarf multicannon");
        if (seq.size() > 0) {
            SimpleSceneObject cannon = ctx.objects.populate().filter("Dwarf multicannon").next();
            cannon.interact(900);
            sleep(1000);
            return true;
        }
        return false;
    }

    public void refuelCannon() {
        if (!cannonInInventory) {
            if (cannonFueled == false) {
                SimpleEntityQuery<SimpleSceneObject> seq = ctx.objects.populate().filter("Dwarf multicannon");
                if (seq.size() > 0) {
                    SimpleSceneObject cannon = ctx.objects.populate().filter("Dwarf multicannon").next();
                    cannon.interact(502);
                    while (!cannonFueled) {
                        sleep(250);
                    }
                }
            }
        }
    }

    @Override
    public void onChatMessage(ChatMessageEvent chatMessageEvent) {
        if (chatMessageEvent.getMessageType() == 0) {
            if (chatMessageEvent.getMessage().contains("run out of cannon balls")) {
                cannonFueled = false;
            } else if (chatMessageEvent.getMessage().contains("furnace down")) {
                cannonInInventory = false;
                cannonFueled = false;
            } else if (chatMessageEvent.getMessage().contains("pickup the ")) {
                cannonInInventory = true;
                cannonFueled = false;
            } else if (chatMessageEvent.getMessage().contains("You fill the")) {
                cannonFueled = true;
            }
        }
    }
}
