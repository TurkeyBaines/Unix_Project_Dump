package org.unix.agility;

import org.unix.Database;
import org.unix.ScriptController;
import org.unix.agility.course.Gnome;
import simple.hooks.filters.SimpleSkills;
import simple.hooks.scripts.Category;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.simplebot.ChatMessage;
import simple.robot.script.Script;

import java.awt.*;

import static org.unix.Methods.*;
import static org.unix.Database.*;

@ScriptManifest(discord = "x_unix", version = "1", servers = {"Zenyte"}, name = "uAgility", category = Category.AGILITY, author = "unix", description = "shite test")
public class uAgility extends Script {

    long startTime;
    int startXP;
    int startLevel;
    int startMarks;

    public ScriptController scriptController;

    @Override
    public void onExecute() {
        startTime = System.currentTimeMillis();
        startXP = ctx.skills.experience(SimpleSkills.Skills.AGILITY);
        startLevel = ctx.skills.level(SimpleSkills.Skills.AGILITY);

        scriptController = new ScriptController();
        scriptController.addTask("gnome", new Gnome());
        scriptController.setTask("gnome ");

        System.out.println(scriptController.getTask().toString());
        try {
            startMarks = ctx.inventory.populate().filter(items.MARK_OF_GRACE).next().getQuantity();
        } catch (Exception e) { startMarks = 0; }
    }

    @Override
    public void onChatMessage(ChatMessage chatMessage) {

    }

    @Override
    public void onTerminate() {
        scriptController = null;
        startMarks = 0;
        startXP = 0;
        startLevel = 0;
        startTime = 0;
    }

    @Override
    public void onProcess() {
        try {
            scriptController.runTask();

        } catch(Exception e) {
            System.out.println("[onProcess] Exception");
            e.printStackTrace();
        }
    }


    private final Color color1 = new Color(255, 255, 255);

    private final Font font1 = new Font("Arial", 1, 32);
    private final Font font2 = new Font("Arial", 1, 15);


    @Override
    public void paint(Graphics g) {
        g.setFont(font1);
        g.setColor(color1);
        g.drawString("uAgility", 6, 40);
        g.setFont(font2);
        g.drawString("Running: " + formatTimeRunning(startTime, System.currentTimeMillis()), 10, 60);
        g.drawString("Course: " + getCourseName(), 10, 80);
        g.drawString("Lvl Gained: " + formatLevelGained(startLevel, ctx.skills.level(SimpleSkills.Skills.AGILITY)), 10, 100);
        g.drawString("Xp Gained: " + formatExperienceGained(startXP, ctx.skills.experience(SimpleSkills.Skills.AGILITY)), 10, 120);
        g.drawString("Marks Gained: " + getMarksGained(), 10, 140);
    }

    public int getMarksGained() {
        try {
            return ctx.inventory.populate().filter(20590).next().getQuantity() - startMarks;
        } catch (Exception e) { return 0; }
    }

    public String getCourseName() {
        return "null";
    }


}
