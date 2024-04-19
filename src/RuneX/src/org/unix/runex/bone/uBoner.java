package org.unix.bone;

import simple.api.script.Category;
import simple.api.script.Script;
import simple.api.script.ScriptManifest;

@ScriptManifest(servers = {"RuneX"}, discord = "", description = "", category = Category.OTHER, name = "uBoner", author = "unix", version = "a_0.1")


public class uBoner extends Script {
    @Override
    public boolean onExecute() {
        return true;
    }

    @Override
    public void onTerminate() {

    }

    @Override
    public void onProcess() {
        if (ctx.bank.bankOpen()) {
            if (ctx.inventory.populate().size() == 28) {
                ctx.bank.closeBank();
                ctx.sleep(1000);
            } else {
                ctx.menuActions.sendAction(431, 20675, 74, 5382);
                ctx.sleep(250);
            }
        } else {
            if (ctx.inventory.isEmpty()) {
                ctx.bank.openBank();
                ctx.sleep(1000);
            } else {
                ctx.menuActions.sendAction(74, 20675, ctx.inventory.filter(20675).next().getSlot(), 3214);
                ctx.sleep(1200);
            }
        }

    }
}
