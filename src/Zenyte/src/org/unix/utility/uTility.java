package org.unix.utility;


import simple.hooks.scripts.Category;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.simplebot.ChatMessage;
import simple.robot.script.Script;

import java.awt.*;

@ScriptManifest(author = "unix", name = "uTility", category = Category.UTILITY, version = "1", description = "Simple Tools to help you Code!", discord = "", servers = {"Ikov, RuneX"})
public class uTility extends Script {

    boolean guiVisible = false;
    public static boolean SCRIPT_END = false;

    @Override
    public void onExecute() {
        if (!guiVisible) {
            new MainGUI().setVisible(true);
            guiVisible = true;
        }
    }

    @Override
    public void onChatMessage(ChatMessage chatMessage) {

    }

    @Override
    public void onProcess() {
        if (SCRIPT_END) {
            ctx.stopScript();
        }
    }

    @Override
    public void onTerminate() {
        guiVisible = false;
    }

    @Override
    public void paint(Graphics graphics) {

    }
}
