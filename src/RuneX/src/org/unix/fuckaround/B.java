package org.unix.fuckaround;

import simple.api.queries.SimpleItemQuery;
import simple.api.script.Category;
import simple.api.script.Script;
import simple.api.script.ScriptManifest;
import simple.api.wrappers.SimpleItem;

@ScriptManifest(author = "unix", name = "HP% TEST", category = Category.OTHER, version = "0", description = "", discord = "", servers = {"RuneX"})
public class B extends Script {
    @Override
    public boolean onExecute() {
        return true;
    }

    @Override
    public void onProcess() {
        System.out.println("HP: " + ctx.combat.health());
        System.out.println("HP%: " + ctx.combat.healthPercent());
    }

    @Override
    public void onTerminate() {
        
    }
}
