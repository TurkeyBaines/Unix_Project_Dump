package org.unix.runex.shitcrack;

import simple.api.script.Category;
import simple.api.script.Script;
import simple.api.script.ScriptManifest;

@ScriptManifest(category = Category.UTILITY, description = "ABC easy as 123", version = "987", servers = {"RuneX"}, discord = "", author = "me myself and pie", name = "that swing thing")
public class ABC extends Script {
    @Override
    public void onProcess() {

    }

    @Override
    public boolean onExecute() {
        System.out.println("a: " + this.getStorageDirectory());
        return false;
    }

    @Override
    public void onTerminate() {

    }
}
