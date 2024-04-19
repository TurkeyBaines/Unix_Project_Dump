package org.unix.barbarian.tasks;

import org.unix.Database;
import org.unix.barbarian.uBarbarian;
import simple.hooks.simplebot.Keyboard;
import simple.hooks.wrappers.SimpleItem;

import static org.unix.Database.*;
import static org.unix.Methods.*;

public class Fish extends Task {

    @Override
    void run() {

        if (!c.inventory.populate().filter(food.SALMON).isEmpty() || !c.inventory.populate().filter(food.SALMON).isEmpty()) {
            c.keyboard.pressKey(16);
            for (SimpleItem i : c.inventory.populate()) {
                if (i.getName().toLowerCase().equals("trout") || i.getName().toLowerCase().equals("salmon")) {
                    i.click(0);
                    c.sleep(25);
                }
            }
            c.keyboard.releaseKey(16);
            return;
        }

        if (!c.inventory.populate().filter(Database.food.BURNT_FOOD).isEmpty()) {
            c.keyboard.pressKey(16);
            for (SimpleItem i : c.inventory.populate().filter(Database.food.BURNT_FOOD)) {
                i.click(0);
            }
            c.keyboard.releaseKey(16);
            return;
        }

        if (c.inventory.populate().size() == 28) {
            System.out.println("Our inventory is full.. we are swapping to the cooking task");
            uBarbarian.ScriptController.setTask("cook");
            return;
        }

        if (c.inventory.populate().filter(items.FLY_FISHING_ROD).isEmpty()) {
            System.out.println("We don't have a fishing rod! stopping...");
            c.stopScript();
            return;
        }

        if (c.inventory.populate().filter(items.FEATHERS).isEmpty()) {
            System.out.println("We don't have any more feathers! stopping...");
            c.sendLogout();
            c.stopScript();
            return;
        }

        if (widgets.LEVEL_UP_MESSAGE.get() != null) {
            System.out.println("Dealing with the level up message!");
            if (widgets.LEVEL_UP_MESSAGE.get().visibleOnScreen()) {
                c.keyboard.sendKeys(" ");
                c.sleepCondition(() -> widgets.LEVEL_UP_MESSAGE.get() == null, 500);
                return;
            }
        }


        if (anim == -1) {
            System.out.println("Lets get fishing... Casting our Rod!");
            interactWithNPC("Lure", objects.FISHING_FLY);
            c.sleep(1000);
        }
    }
}
