package org.unix.shaco;

import simple.hooks.scripts.Category;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.simplebot.ChatMessage;
import simple.robot.script.Script;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@ScriptManifest(discord = "", name = "uBarrows - private", servers = {"Battlescape"}, version = "1", description = "completes barrows with magic", category = Category.MINIGAMES, author = "unix")
public class uBarrows extends Script {

    ScriptManager scriptManager;

    @Override
    public void onProcess() {
        ScriptManager.run();
    }

    @Override
    public void onChatMessage(ChatMessage chatMessage) {

    }

    @Override
    public void onExecute() {
        scriptManager = new ScriptManager();
    }

    @Override
    public void onTerminate() {

    }

    @Override
    public void paint(Graphics graphics) {

    }

    public static class ScriptManager {

        static List<Task> taskList;
        static Task current;

        public ScriptManager() {
            taskList = new ArrayList<>();
            taskList.add(new FightBarrows());
            taskList.add(new Bank());
            current = taskList.get(1);
        }

        public static void run() {
            current.run();
        }

        public static void forceBank() {
            current = taskList.get(1);
        }

        public static void forceKill() {
            current = taskList.get(0);
        }

    }

}
