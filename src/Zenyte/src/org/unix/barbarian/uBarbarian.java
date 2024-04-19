package org.unix.barbarian;

import org.unix.barbarian.tasks.Cook;
import org.unix.barbarian.tasks.Fish;
import org.unix.barbarian.tasks.Task;
import simple.hooks.filters.SimpleSkills;
import simple.hooks.scripts.Category;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.simplebot.ChatMessage;
import simple.robot.script.Script;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import static org.unix.Methods.*;

@ScriptManifest(discord = "", version = "1", servers = {"Zenyte"}, name = "uBarbarian", category = Category.FISHING, author = "unix", description = "Fishes at Barbarian Village, uses the fire close by to cook the food. Will logout when you run out of feathers!")
public class uBarbarian extends Script {

    ScriptController sc;

    int startCookXP = 0, startCookLevel = 0, startFishXP = 0, startFishLevel = 0;
    long startTime = -1;

    @Override
    public void onExecute() {
        sc = new ScriptController();

        startCookLevel = ctx.skills.level(SimpleSkills.Skills.COOKING);
        startCookXP = ctx.skills.experience(SimpleSkills.Skills.COOKING);
        startFishXP = ctx.skills.experience(SimpleSkills.Skills.FISHING);
        startFishLevel = ctx.skills.level(SimpleSkills.Skills.FISHING);
        startTime = System.currentTimeMillis();
    }

    @Override
    public void onChatMessage(ChatMessage chatMessage) {
        if (chatMessage.getSender() == null) {
            if (chatMessage.getMessage().toLowerCase().contains("you successfully cook")) {
                totalCooked++;
            } else if (chatMessage.getMessage().toLowerCase().contains("you catch a")) {
                totalCaught++;
            }
        }
    }

    @Override
    public void onProcess() {
        sc.run();
    }

    @Override
    public void onTerminate() {

    }


    private final Color color1 = new Color(255, 255, 255);
    private final Color color2 = new Color(255, 102, 0);
    private final Color color3 = new Color(255, 255, 255);

    private final Font font1 = new Font("Arial", 1, 13);

    private final Image img1 = getImage("https://i.imgur.com/ISXEKkG.png");

    public static int totalCooked = 0, totalCaught = 0;

    @Override
    public void paint(Graphics graphics) {

        Graphics2D g = (Graphics2D)graphics;
        g.drawImage(img1, 0, 0, null);
        g.setFont(font1);
        g.setColor(color1);
        g.drawString(formatExperienceGained(startFishXP, ctx.skills.experience(SimpleSkills.Skills.FISHING)) + " " + formatLevelGained(startFishLevel, ctx.skills.level(SimpleSkills.Skills.FISHING)), 127, 118);
        g.drawString(formatExperienceGained(startCookXP, ctx.skills.experience(SimpleSkills.Skills.COOKING)) + " " + formatLevelGained(startCookLevel, ctx.skills.level(SimpleSkills.Skills.COOKING)), 127, 100);
        g.drawString("" + totalCaught + " | Cooked: " + totalCooked, 126, 138);
        g.setColor(color2);
        g.drawString(formatTimeRunning(startTime, System.currentTimeMillis()), 412, 496);
        g.setColor(color3);
        g.drawString("t:" + ScriptController.taskString, 475, 18);
    }
    //END: Code generated using Enfilade's Easel

    public static class ScriptController {
        private static HashMap<String, Task> taskList;
        private static Task current;
        public static String taskString = "";

        public static void setTask(String taskName) {
            taskString = taskName;
            current = taskList.get(taskName);
        }

        public ScriptController() {
            taskList = new HashMap<>();
            taskList.put("fish", new Fish());
            taskList.put("cook", new Cook());
            ScriptController.setTask("fish");
        }

        public void run() {
            current.runtime();
        }
    }

    private Image getImage(String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch(IOException e) {
            return null;
        }
    }
}
