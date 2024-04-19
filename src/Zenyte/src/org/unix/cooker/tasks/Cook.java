package org.unix.cooker.tasks;

import org.unix.Database;
import org.unix.cooker.Config;
import org.unix.cooker.uCooker;
import simple.hooks.queries.SimpleEntityQuery;
import simple.hooks.wrappers.SimpleObject;

import static org.unix.Database.*;
import static org.unix.Methods.*;

public class Cook extends Task {

    @Override
    void run() {

        if (c.inventory.populate().filter(Config.RAW_ID).isEmpty()) {
            uCooker.ScriptManager.setTask("bank");
            return;
        }

        if (p.isAnimating()) {
            c.sleep(1000);
            return;
        }



        if (widgets.COOK_INTERFACE.get() != null) {
            if (widgets.COOK_INTERFACE.get().getName().contains(Config.INTERACTION)) {
                widgets.COOK_INTERFACE.get().click(0);
                c.sleepCondition(() -> p.isAnimating(), 250);
                return;
            }
        }

        interactWithObject(objects.RANGE);
        c.sleepCondition(() -> widgets.COOK_INTERFACE.get() != null, 5000);

    }
}
