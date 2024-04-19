package org.unix.thief.uMasterFarmer.tasks;

import org.unix.Task;
import org.unix.thief.uMasterFarmer.Config;
import org.unix.thief.uMasterFarmer.uMasterFarmer;
import simple.hooks.filters.SimpleBank;

import static org.unix.Database.*;
import static org.unix.Methods.*;

public class Bank extends Task {
    @Override
    public void run() {
        if (areas.DRAYNOR_BANK.get().containsPoint(loc)) {
            if (widgets.BANK_INTERFACE.get() != null) {
                if (c.inventory.populate().isEmpty()) {
                    c.bank.withdraw(Config.SELECTED_FOOD, SimpleBank.Amount.ONE);
                    c.sleepCondition(() -> c.inventory.populate().filter(Config.SELECTED_FOOD).size() == Config.AMT_TO_WITHDRAW, 1000);
                    return;
                }

                if (c.inventory.populate().size() == 1 && c.inventory.populate().filter(Config.SELECTED_FOOD).size() == 1) {
                    c.bank.closeBank();
                    c.sleepCondition(() -> widgets.BANK_INTERFACE.get() == null, 1000);
                    return;
                }

                c.bank.depositInventory();
                c.sleepCondition(() -> c.inventory.populate().isEmpty(), 1000);
                return;
            }

            if (c.combat.healthPercent() < 75) {
                c.inventory.populate().filter(Config.SELECTED_FOOD).next().click(0);
                c.sleep(1000);
                return;
            }

            if (c.inventory.populate().filter(Config.SELECTED_FOOD).size() < 5 || c.inventory.populate().size() == 28) {
                interactWithObject(objects.BANK_BOOTH);
                c.sleepCondition(() -> widgets.BANK_INTERFACE.get() != null, 1000);
                return;
            }



            uMasterFarmer.sc.setTask("steal");
        }

        if (c.inventory.populate().filter(Config.SELECTED_FOOD).size() == Config.AMT_TO_WITHDRAW) {
            if (c.combat.healthPercent() < 100) {
                while (c.combat.healthPercent() < 100) {
                    c.inventory.populate().filter(Config.SELECTED_FOOD).next().click("Eat");
                    c.sleep(2000);
                }
            }
        }

    }
}
