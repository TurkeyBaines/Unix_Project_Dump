package org.unix.fuckaround;

import simple.api.script.Category;
import simple.api.script.Script;
import simple.api.script.ScriptManifest;

@ScriptManifest(author = "unix", name = "E", category = Category.OTHER, version = "1", description = "", discord = "", servers = {"RuneX"})
public class E extends Script {

    @Override
    public boolean onExecute() {
        return true;
    }

    @Override
    public void onProcess() {
        if (ctx.bank.bankOpen()) { // check if the bank is open


            if (ctx.inventory.populate().isEmpty()) { // check if your inventory is empty
                ctx.bank.closeBank(); // if it is, then close the bank
                ctx.sleep(2500); // sleep for 2.5 seconds


            } else { // if the inventory is NOT empty!
                 ctx.bank.depositInventory(); // deposit our inventory
                 ctx.sleep(2500);
            }


        } else { // if the bank is NOT open!

            ctx.bank.openBank(); // we should open the bank
            ctx.sleep(2500);
        }
    }

    @Override
    public void onTerminate() {

    }

}
