package org.unix.thief.uMasterFarmer;

import net.runelite.standalone.B;
import org.unix.ScriptController;
import org.unix.Task;
import org.unix.thief.uMasterFarmer.tasks.Bank;
import org.unix.thief.uMasterFarmer.tasks.Steal;
import simple.hooks.scripts.Category;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.simplebot.ChatMessage;
import simple.robot.script.Script;

import java.awt.*;
import java.util.HashMap;

@ScriptManifest(discord = "x_unix", version = "1", servers = {"Zenyte"}, name = "uMasterFarmer", category = Category.THIEVING, author = "unix", description = "Collects seeds from Master Farmers in Draynor. Banks for the food you specify in GUI, and when your inventory is full. Turn on drop mode to only collect specific seeds")
public class uMasterFarmer extends Script {

    public static ScriptController sc;

    @Override
    public void onExecute() {
        //TODO - add GUI
        sc = new ScriptController();
        sc.addTask("steal", new Steal());
        sc.addTask("bank", new Bank());
        sc.setTask("steal");
    }

    @Override
    public void onChatMessage(ChatMessage chatMessage) {

    }

    @Override
    public void onProcess() {
        sc.runTask();
    }

    @Override
    public void onTerminate() {

    }

    @Override
    public void paint(Graphics graphics) {

    }


}
