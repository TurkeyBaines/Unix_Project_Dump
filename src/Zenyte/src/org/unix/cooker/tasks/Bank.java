package org.unix.cooker.tasks;

import org.unix.cooker.Config;
import org.unix.cooker.uCooker;
import simple.hooks.filters.SimpleBank;

import static org.unix.Database.*;
import static org.unix.Methods.*;

public class Bank extends Task {
    @Override
    void run() {

        if ((c.inventory.populate().filter(Config.RAW_ID).size() == 28) && (!c.bank.bankOpen())) {
            uCooker.ScriptManager.setTask("cook");
            return;
        }

        if (p.isAnimating()) {
            c.sleep(1000);
            return;
        }

        if (c.bank.bankOpen()) {
            if (!c.inventory.populate().filter(Config.COOK_ID).isEmpty()) {
                c.bank.depositInventory();
                c.sleep(1000);
            }

            if (c.inventory.populate().isEmpty()) {
                if (!c.bank.withdraw(food.RAW_SHARK, SimpleBank.Amount.ALL)) {
                    c.stopScript();
                    System.out.println("we have run out of raw fish!");
                }
                c.sleepCondition(() -> !c.inventory.populate().isEmpty(), 500);
            }

            if (c.inventory.populate().filter(Config.RAW_ID).size() == 28) {
                c.bank.closeBank();
                c.sleepCondition(() -> !c.bank.bankOpen(), 1000);
            }
        } else {
            interactWithObject(objects.BANK_BOOTH);
            c.sleepCondition(() -> c.bank.bankOpen(), 5000);
        }

    }
}
