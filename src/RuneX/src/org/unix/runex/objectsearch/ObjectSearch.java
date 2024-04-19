package org.unix.runex.objectsearch;

import simple.api.script.Category;
import simple.api.script.Script;
import simple.api.script.ScriptManifest;

@ScriptManifest(servers = {"RuneX"}, discord = "", description = "Search Object data using Interaction Strings", category = Category.UTILITY, name = "Object Interaction Tool", author = "unix", version = "1")
public class ObjectSearch extends Script {
    @Override
    public void onProcess() {
        ctx.sleep(1000);
    }

    @Override
    public boolean onExecute() {
        new GUI().setVisible(true);
        return true;
    }

    @Override
    public void onTerminate() {

    }
}
