package org.unix.fuckaround;

import simple.api.script.Category;
import simple.api.script.Script;
import simple.api.script.ScriptManifest;

@ScriptManifest(category = Category.UTILITY, description = "a", version = "1", servers = {"RuneX"}, discord = "", author = "unix", name = "A")
public class A extends Script {
    @Override
    public void onProcess() {
        System.out.println(ctx.prayers.prayerBook());
        ctx.sleep(5000);
    }

    @Override
    public boolean onExecute() {
        return true;
    }

    @Override
    public void onTerminate() {

    }
}
