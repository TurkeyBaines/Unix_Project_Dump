package org.unix.lms;

import org.unix.lms.tasks.Fight;
import org.unix.lms.tasks.Lobby;
import simple.api.script.Category;
import simple.api.script.Script;
import simple.api.script.ScriptManifest;

@ScriptManifest(author = "unix", name = "uLMS", category = Category.COMBAT, version = "0", description = "LMS Bot Attempt", discord = "", servers = {"Ikov"})
public class uLastMan extends Script {

    Lobby lobby;
    Fight fight;

    @Override
    public boolean onExecute() {
        lobby = new Lobby();
        fight = new Fight();
        return true;
    }

    @Override
    public void onProcess() {
        lobby.process();
        fight.process();
    }

    @Override
    public void onTerminate() {

    }
}
