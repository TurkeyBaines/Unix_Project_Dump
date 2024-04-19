package org.unix.runex.agility;

import simple.api.filters.SimpleSkills;
import simple.api.queries.SimpleEntityQuery;
import simple.api.script.Category;
import simple.api.script.Script;
import simple.api.script.ScriptManifest;
import simple.api.script.interfaces.SimplePaintable;
import simple.api.wrappers.SimpleSceneObject;

import java.awt.*;

@ScriptManifest(servers = {"RuneX"}, discord = "", description = "AIO Agility Script", category = Category.AGILITY, name = "uAgility", author = "unix", version = "2")
public class uAgility extends Script implements SimplePaintable {

    boolean guiVisible = false;
    public static boolean startScript = true;

    long startTime;
    int startXP;
    int startLevel;
    int startMarks;

    TaskMaster tm;

    @Override
    public boolean onExecute() {
        tm = new TaskMaster(ctx);
        tm.setTask("gnome");
        startTime = System.currentTimeMillis();
        startXP = ctx.skills.getExperience(SimpleSkills.Skill.AGILITY);
        startLevel = ctx.skills.getLevel(SimpleSkills.Skill.AGILITY);
        try {
            startMarks = ctx.inventory.populate().filter(20590).next().getQuantity();
        } catch (Exception e) { startMarks = 0; }
        return startScript;
    }

    @Override
    public void onTerminate() {

    }

    @Override
    public void onProcess() {

        //if (tm.currentTask.nextCourse.reqLevel() <= ctx.skills.getLevel(SimpleSkills.Skill.AGILITY)) {
        //    tm.setTask(tm.currentTask.nextText().toLowerCase());
        //}

        tm.processTask();
        /*
        SimpleEntityQuery<SimpleSceneObject> fm = ctx.objects.populate().filter((n) -> (n.getActions() != null) && n.getActions().length > 0);
        for (SimpleSceneObject sso : fm) {
            if (sso != null) {
                if (sso.getActions()[0] != null) {
                    if (sso.getActions()[0].contains("Cross")) {
                        try {
                            System.out.println("[Found] " + sso.getName() + " - " + sso.getId());
                        } catch (NullPointerException e) {
                        }
                    }
                }
            }
        }
        */
    }


    private final Color color1 = new Color(0, 0, 0);

    private final Font font1 = new Font("Arial", 1, 32);
    private final Font font2 = new Font("Arial", 1, 15);


    @Override
    public void onPaint(Graphics2D g) {
        g.setFont(font1);
        g.setColor(color1);
        g.drawString("uAgility", 6, 40);
        g.setFont(font2);
        g.drawString("Running: " + formatTimeRunning(startTime), 10, 60);
        g.drawString("Course: " + getCourseName(), 10, 80);
        g.drawString("Lvl Gained: " + getLvlGained(), 10, 100);
        g.drawString("Xp Gained: " + getXpGained(), 10, 120);
        g.drawString("Marks Gained: " + getMarksGained(), 10, 140);
    }

    public String getXpGained() {
        return formatGained(startXP);
    }
    public int getLvlGained() {
        return ctx.skills.getLevel(SimpleSkills.Skill.AGILITY) - startLevel;
    }

    public int getMarksGained() {
        try {
            return ctx.inventory.populate().filter(20590).next().getQuantity() - startMarks;
        } catch (Exception e) { return 0; }
    }

    public String getCourseName() {
        if (tm.currentTask != null) {
            String s = tm.currentTask.name() + " (" + ctx.skills.getLevel(SimpleSkills.Skill.AGILITY) + "/" + tm.currentTask.upgradeLevel() +")";
            return s;
        }

        return "null";
    }

    public String formatTimeRunning(long st) {
        long running = System.currentTimeMillis() - st;
        long millis = running % 1000;
        long second = (running / 1000) % 60;
        long minute = (running / (1000 * 60)) % 60;
        long hour = (running / (1000 * 60 * 60)) % 24;

        return String.format("%02d:%02d:%02d.%d", hour, minute, second, millis);
    }

    public String formatGained(int sxp) {
        int gained = ctx.skills.getExperience(SimpleSkills.Skill.AGILITY) - sxp;
        String output = "";
        if (gained > 1000 && gained < 10000) { output = String.valueOf(gained).substring(0,1) + "K"; }
        else if (gained > 9999 && gained < 100000) { output = String.valueOf(gained).substring(0, 2) + "K"; }
        else if (gained > 99999 && gained < 1000000) { output = String.valueOf(gained).substring(0, 3) + "K"; }
        else if (gained > 999999 && gained < 10000000) { output = String.valueOf(gained).substring(0, 1) + "M"; }
        else if (gained > 9999999) { output = String.valueOf(gained).substring(0, 2) + "M"; }
        else { output = String.valueOf(gained); }

        return output;
    }
}
