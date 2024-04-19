package org.unix.barbarian.tasks;

import org.unix.Database;
import org.unix.barbarian.uBarbarian;

import static org.unix.Database.*;
import static org.unix.Methods.*;

public class Cook extends Task {

    public int[] raw_fish = {Database.food.RAW_TROUT, Database.food.RAW_SALMON };

    @Override
    void run() {

        if (c.inventory.populate().filter(raw_fish).isEmpty()) {
            uBarbarian.ScriptController.setTask("fish");
            return;
        }

        if (Database.widgets.COOK_INTERFACE.get() != null) {
            if (Database.widgets.COOK_INTERFACE.get().visibleOnScreen()) {
                Database.widgets.COOK_INTERFACE.get().click(0);
                c.sleep(15000);
            }
        }

        if (!p.isAnimating()) {
            if (!c.inventory.populate().filter(food.RAW_TROUT).isEmpty()) {
                if (p.isAnimating()) { return; }
                c.inventory.populate().filter(food.RAW_TROUT).next().click(0);
                if (p.isAnimating()) { return; }
                c.sleep(250);
                interactWithObject(objects.BARBARIAN_FIRE);
                c.sleep(500);
            } else if (!c.inventory.populate().filter(food.RAW_SALMON).isEmpty()) {
                c.inventory.populate().filter(food.RAW_SALMON).next().click(0);
                if (p.isAnimating()) { return; }
                c.sleep(250);
                interactWithObject(objects.BARBARIAN_FIRE);
                c.sleep(500);
            }
        } else {
            c.sleepCondition(() -> !p.isAnimating(), 500);
        }

    }
}
