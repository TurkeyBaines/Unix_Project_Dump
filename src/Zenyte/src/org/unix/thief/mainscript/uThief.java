package org.unix.thief.mainscript;

import org.unix.ScriptController;
import org.unix.thief.mainscript.gui.Setup;
import org.unix.thief.mainscript.task.Bank;
import org.unix.thief.mainscript.task.Eat;
import org.unix.thief.mainscript.task.Steal;
import simple.hooks.scripts.Category;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.simplebot.ChatMessage;
import simple.robot.script.Script;

import java.awt.*;

@ScriptManifest(discord = "", version = "1", servers = {"Zenyte"}, name = "uThief", category = Category.THIEVING, author = "unix", description = "AIO Thieving Bot")
public class uThief extends Script {

    public static ScriptController sc;
    public static boolean START = false;

    @Override
    public void onExecute() {
        START = false;
        sc = new ScriptController();
        sc.addTask("steal", new Steal());
        sc.addTask("eat", new Eat());
        sc.addTask("bank", new Bank());
        sc.setTask("steal");
        new Setup().setVisible(true);

    }

    @Override
    public void onChatMessage(ChatMessage chatMessage) {

    }

    @Override
    public void onProcess() {
        if (sc.getTask() == null) {
            sc.setTask("steal");
        }
        if (START) {
            sc.runTask();
        }
    }

    @Override
    public void onTerminate() {
        Config.npcID = new int[]{-1};
        Config.foodID = -1;
        Config.useFood = true;
        Config.upgradeTask = false;
        Config.isStall = false;
    }

    @Override
    public void paint(Graphics graphics) {

    }



}
