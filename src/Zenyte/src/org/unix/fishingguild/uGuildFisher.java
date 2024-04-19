package org.unix.fishingguild;

import org.unix.fishingguild.tasks.Bank;
import org.unix.fishingguild.tasks.Fish;
import org.unix.fishingguild.tasks.Task;
import simple.hooks.scripts.Category;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.simplebot.ChatMessage;
import simple.robot.script.Script;

import java.awt.*;
import java.util.HashMap;

@ScriptManifest(discord = "unix", version = "1", servers = {"Zenyte"}, name = "uGuildFisher", category = Category.FISHING, author = "unix", description = "Fishes... in the fishing guild!")
public class uGuildFisher extends Script {

    ScriptController sc;
    @Override
    public void onExecute() {
        sc = new ScriptController();
    }

    @Override
    public void onChatMessage(ChatMessage chatMessage) {

    }

    @Override
    public void onProcess() {
        ScriptController.runTask();
    }

    @Override
    public void onTerminate() {

    }

    @Override
    public void paint(Graphics graphics) {

    }

    public static class ScriptController {
        private static HashMap<String, Task> tasks;
        private static Task current;

        public ScriptController() {
            tasks = new HashMap<>();
            tasks.put("fish", new Fish());
            tasks.put("bank", new Bank());
            setTask("fish");
        }

        public static void setTask(String task) {
            current = tasks.get(task);
        }

        public static void runTask() {
            current.run();
        }
    }
}
