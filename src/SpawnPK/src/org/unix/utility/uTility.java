package org.unix.utility;

import simple.api.coords.WorldArea;
import simple.api.coords.WorldPoint;
import simple.api.script.Category;
import simple.api.script.Script;
import simple.api.script.ScriptManifest;

@ScriptManifest(author = "unix", name = "uTility", category = Category.UTILITY, version = "1", description = "Simple Tools to help you Code!", discord = "", servers = {"Ikov, RuneX"})
public class uTility extends Script {

    boolean guiVisible = false;
    public static boolean SCRIPT_END = false;

    @Override
    public boolean onExecute() {
        if (!guiVisible) {
            new MainGUI().setVisible(true);
            guiVisible = true;
        }
        return false;
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
}
