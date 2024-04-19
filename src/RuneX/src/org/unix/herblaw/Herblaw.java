package org.unix.herblaw;

import simple.api.ClientContext;
import simple.api.script.Category;
import simple.api.script.Script;
import simple.api.script.ScriptManifest;
import simple.api.wrappers.SimpleItem;

@ScriptManifest(category = Category.HERBLORE, description = "Some Shit Herblaw Script", version = "1", servers = {"RuneX"}, discord = "", author = "unix", name = "uHerbFucker")
public class Herblaw extends Script {

    int badID = 199;
    int goodID = 249;

    int vial = 227;
    int herb = 3000;
    int unf = 3004;
    int sec = 223;
    int round = 9;
    int comp = 3026;

    @Override
    public void onProcess() {
        ClientContext c = ctx;
        /*if (c.inventory.populate().filter(goodID).size() == 28) {
            c.bank.openBank();
            c.sleep(250);
            c.menuActions.sendAction(315, 1161, 54, 56047);
            c.sleep(50);
            c.menuActions.sendAction(315, 1161, 54, 56023);
            c.sleep(50);
            c.bank.closeBank();
        } else if (!c.inventory.populate().filter(badID).isEmpty()) {
            /*c.inventory.populate().filter(badID).next().interact(74);
            c.sleep(100);
            for (int i = 0; i < 28; i++) {
                c.menuActions.sendAction(74, badID, i, 3214);
                c.sleep(25);
            }
        }*/// <---- to clean herbs

        if (c.inventory.populate().filter(comp).size() == round) {
            bank();
        } else if (c.inventory.populate().filter(unf).size() == round) {
            SimpleItem pot = c.inventory.populate().filter(unf).next();
            SimpleItem use = c.inventory.populate().filter(sec).next();
            if (pot == null || use == null) {
                bank();
                return;
            }
            c.menuActions.sendAction(447, unf, pot.getSlot(), 3214);
            c.sleep(100);
            c.menuActions.sendAction(870, sec, use.getSlot(), 3214);
            c.sleep(250);
            c.menuActions.sendAction(315, 172310528, 151, 55313);
            c.sleep(250);
        } else if (c.inventory.populate().filter(vial).size() == round) {
            SimpleItem vil = c.inventory.next();
            SimpleItem erb = c.inventory.populate().filter(herb).next();
            if (vil == null || erb == null) {
                bank();
                return;
            }
            c.menuActions.sendAction(447, vial, vil.getSlot(), 3214);
            c.sleep(100);
            c.menuActions.sendAction(870, herb, erb.getSlot(), 3214);
            c.sleep(250);
            c.menuActions.sendAction(315, 705, 54, 55313);
            c.sleep(250);
        }


    }

    @Override
    public boolean onExecute() {
        return true;
    }

    @Override
    public void onTerminate() {

    }

    public void bank() {
        ctx.bank.openBank();
        ctx.sleep(350);
        ctx.menuActions.sendAction(315, 1161, 54, 56047);
        ctx.sleep(100);
        ctx.menuActions.sendAction(315, 1161, 54, 56023);
        ctx.sleep(100);
        ctx.bank.closeBank();
    }
}
