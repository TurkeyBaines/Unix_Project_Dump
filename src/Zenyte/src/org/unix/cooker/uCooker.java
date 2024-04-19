package org.unix.cooker;

import org.apache.commons.lang3.ObjectUtils;
import org.unix.Database;
import org.unix.Methods;
import org.unix.cooker.tasks.Bank;
import org.unix.cooker.tasks.Cook;
import org.unix.cooker.tasks.Task;
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

@ScriptManifest(discord = "unix", version = "1", servers = {"Zenyte"}, name = "uCooker", category = Category.COOKING, author = "unix", description = "Cook in Catherby")
public class uCooker extends Script {

    ScriptManager sm;

    @Override
    public void onExecute() {
        sm = new ScriptManager();
        Config.RAW_ID = Database.food.RAW_SHARK;
        Config.COOK_ID = Database.food.SHARK;
        Config.INTERACTION = "Shark";
        startXP = ctx.skills.experience(SimpleSkills.Skills.COOKING);
        startLvL = ctx.skills.level(SimpleSkills.Skills.COOKING);
    }

    @Override
    public void onChatMessage(ChatMessage chatMessage) {
        if (chatMessage.getSender() == null) {
            if (chatMessage.getMessage().toLowerCase().contains("you successfully cook")) {
                totalCooked++;
            } else if (chatMessage.getMessage().toLowerCase().contains("you accidentally burn")) {
                totalBurnt++;
            }
        }
    }

    @Override
    public void onProcess() {
        try {
            sm.runTask();
        } catch (NullPointerException npe) {}
    }

    @Override
    public void onTerminate() {
        startXP = 0;
        startLvL = 0;
        totalBurnt = 0;
        totalCooked = 0;
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

    private final Font font1 = new Font("Calibri", 1, 10);

    private final Image img1 = getImage("https://i.imgur.com/PTBdeqA.png");

    public int startXP = 0, startLvL = 0;
    public static int totalCooked = 0, totalBurnt = 0;

    @Override
    public void paint(Graphics graphics) {
        Graphics2D g = (Graphics2D)graphics;
        g.drawImage(img1, 0, -1, null);
        g.setFont(font1);
        g.setColor(color1);
        g.drawString(Methods.formatExperienceGained(startXP, ctx.skills.experience(SimpleSkills.Skills.COOKING)) + " "+ Methods.formatLevelGained(startLvL, ctx.skills.level(SimpleSkills.Skills.COOKING)), 234, 332);
        g.drawString((totalCooked) + " | " + (totalBurnt), 440, 332);
    }
    //END: Code generated using Enfilade's Easel

    public static class ScriptManager {
        private static HashMap<String, Task> tasks;
        private static Task currentTask;

        public void runTask() {
            currentTask.runtime();
        }

        public ScriptManager() {
            tasks = new HashMap<>();
            tasks.put("bank", new Bank());
            tasks.put("cook", new Cook());
            currentTask = tasks.get("cook");
        }

        public static void setTask(String name) {
            currentTask = tasks.get(name);
            System.out.println("Setting task to: " + name);
        }

    }
}
