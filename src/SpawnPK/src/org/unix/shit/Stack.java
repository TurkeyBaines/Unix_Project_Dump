package org.unix.shit;

import simple.api.script.Category;
import simple.api.script.Script;
import simple.api.script.ScriptManifest;

@ScriptManifest(author = "xinu", name = "ShitStack", category = Category.UTILITY, version = "1", description = "123", discord = "", servers = {"Ikov"})
public class Stack extends Script {
    @Override
    public boolean onExecute() {
        try {
            System.out.println(ctx.npcs.populate().filter(27152).next().getOverheadIcon());
        } catch (NullPointerException e) { }
        return false;
    }

    @Override
    public void onProcess() {

    }

    @Override
    public void onTerminate() {

    }
}
