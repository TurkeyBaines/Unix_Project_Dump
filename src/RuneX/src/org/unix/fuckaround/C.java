package org.unix.fuckaround;

import simple.api.events.ProjectileSpawnedEvent;
import simple.api.listeners.SimpleProjectileSpawnedListener;
import simple.api.script.Category;
import simple.api.script.Script;
import simple.api.script.ScriptManifest;

@ScriptManifest(author = "unix", name = "C", category = Category.OTHER, version = "0", description = "3", discord = "", servers = {"RuneX"})
public class C extends Script implements SimpleProjectileSpawnedListener {
    @Override
    public boolean onExecute() {
        return true;
    }

    @Override
    public void onProcess() {

    }

    @Override
    public void onTerminate() {

    }

    @Override
    public void onProjectileSpawned(ProjectileSpawnedEvent pse) {
        System.out.println(pse.getProjectileId() + " | " +pse.getSource().toString());
    }
}
