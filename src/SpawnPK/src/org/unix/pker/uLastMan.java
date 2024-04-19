package org.unix.pker;

import org.unix.pker.tasks.*;
import simple.api.script.Category;
import simple.api.script.Script;
import simple.api.script.ScriptManifest;

@ScriptManifest(author = "unix", name = "uLMS", category = Category.COMBAT, version = "0", description = "LMS Bot Attempt", discord = "", servers = {"Ikov"})
public class uLastMan extends Script {

    Fight fight;

    @Override
    public boolean onExecute() {
        fight = new Fight();
        return true;
    }

    @Override
    public void onProcess() {
        fight.process();
    }

    @Override
    public void onTerminate() {

    }
}
