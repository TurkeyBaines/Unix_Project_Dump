package org.unix.trk;

import simple.hooks.scripts.Category;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.simplebot.ChatMessage;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.script.Script;

import java.awt.*;

@ScriptManifest(description = "test", version = "0", servers = {"Zaros"}, discord = "", category = Category.UTILITY, author = "unix", name = "TRK")
public class TRK extends Script {
    @Override
    public void onChatMessage(ChatMessage chatMessage) {

    }

    @Override
    public void onTerminate() {

    }

    @Override
    public void onProcess() {
        for (SimpleObject so : ctx.objects.populate()) {
            if (so != null) {
                if (so.getName() != null) {
                    if (so.getName().toLowerCase().contains("sack")) {
                        System.out.println("N: " + so.getName() + ", ID: " + so.getId() + ", L: " + so.getLocation());
                        System.out.println("");
                        for (String s : so.getActions()) {
                            if (s != null) {
                                System.out.print("\n [A=" + s + "]");
                            }
                        }
                        System.out.println("");
                    }
                }
            }
        }
    }

    @Override
    public void onExecute() {

    }

    @Override
    public void paint(Graphics graphics) {

    }
}
