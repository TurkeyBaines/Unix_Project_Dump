package org.unix.runex.defender;

import org.unix.runex.defender.tasks.TaskManager;
import simple.api.script.Category;
import simple.api.script.Script;
import simple.api.script.ScriptManifest;

@ScriptManifest(category = Category.MINIGAMES, description = "Completes your defenders.. so you don't have to!", version = "1", servers = {"RuneX"}, discord = "", author = "unix", name = "uDefender")
public class uDefender extends Script {

    TaskManager tm;
    int sRunex = 0;
    long sTime = 0;
    int tStart = 0;

    @Override
    public void onProcess() {
        tm.processAllTasks();
    }

    @Override
    public boolean onExecute() {
        tm = new TaskManager();
        sRunex = Integer.parseInt(ctx.widgets.populate().filter(16029).next().getText().split("@gre@")[1]);
        sTime = System.currentTimeMillis();
        if (!ctx.inventory.populate().filter(Constants.TOKEN_ID).isEmpty())
            tStart = ctx.inventory.next().getQuantity();
        return true;
    }

    @Override
    public void onTerminate() {
        int cRunex = Integer.parseInt(ctx.widgets.populate().filter(16029).next().getText().split("@gre@")[1]);
        System.out.println("Ran For: " + formatTimeRunning(sTime));
        System.out.println("Total Tokens Gained: " + (ctx.inventory.populate().filter(Constants.TOKEN_ID).next().getQuantity() - tStart));
        System.out.println("RuneX Gained: " + (cRunex - sRunex));
    }

    public String formatTimeRunning(long st) {
        long running = System.currentTimeMillis() - st;
        long millis = running % 1000;
        long second = (running / 1000) % 60;
        long minute = (running / (1000 * 60)) % 60;
        long hour = (running / (1000 * 60 * 60)) % 24;

        return String.format("%02d:%02d:%02d.%d", hour, minute, second, millis);
    }
}
