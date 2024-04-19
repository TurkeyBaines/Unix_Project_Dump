package org.unix;

import simple.hooks.queries.SimpleEntityQuery;
import simple.hooks.queries.SimpleItemQuery;
import simple.hooks.wrappers.SimpleGroundItem;
import simple.hooks.wrappers.SimpleItem;
import simple.hooks.wrappers.SimpleNpc;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.api.ClientContext;

public class Methods {

    /*
            Author: unix
            Discord: x_unix
     */

    private static ClientContext c = ClientContext.instance();

    public static void teleport(String s) {

        if (c.widgets.getWidget(1028, 101).visibleOnScreen()) {

        }

        switch (s) {
            case "home":
                break;

            case "essence_mine":
                break;

        }
    }

    /*
        Usage:
                type:
                     0 = Objects
                     1 = NPCs
                     2 = Items
                     3 = GroundItems

                id:
                    The ID of the entity your interacting with

                interaction:
                    The text of the interaction, i.e. "Pick up", "Steal", "Talk-to"
                        - this is CaSE-sENsItIvE!
     */
    public static void intEntity(int type, int id, String interaction) {
        if (type == 0) {
            SimpleEntityQuery<SimpleObject> soq = c.objects.populate().filter(id);
            if (soq.isEmpty()) { return; }
            SimpleObject sObj;
            if ((sObj = soq.nearest().next()) == null) { return; }
            if (!sObj.visibleOnScreen()) {
                sObj.turnTo();
                c.sleepCondition(() -> sObj.visibleOnScreen(), 2500);
            }
            sObj.click(interaction);
            c.sleep(1000);
        } else if (type == 1) {
            SimpleEntityQuery<SimpleNpc> snq = c.npcs.populate().filter(id);
            if (snq.isEmpty()) { return; }
            SimpleNpc sNpc;
            if ((sNpc = snq.nearest().next()) == null) { return; }
            if (!sNpc.visibleOnScreen()) {
                sNpc.turnTo();
                c.sleepCondition(() -> sNpc.visibleOnScreen(), 2500);
            }
            sNpc.click(interaction);
            c.sleep(1000);
        } else if (type == 2) {
            SimpleItemQuery<SimpleItem> siq = c.inventory.populate().filter(id);
            if (siq.isEmpty()) { return; }
            SimpleItem sItem;
            if ((sItem = siq.next()) == null) { return; }
            sItem.click(interaction);
            c.sleep(1000);
        } else if (type == 3) {
            SimpleEntityQuery<SimpleGroundItem> sgiq = c.groundItems.populate();
            if (sgiq.isEmpty()) { return; }
            SimpleGroundItem sGItm;
            if ((sGItm = sgiq.nearest().next()) == null) { return; }
            if (!sGItm.visibleOnScreen()) {
                sGItm.turnTo();
                c.sleepCondition(() -> sGItm.visibleOnScreen(), 2500);
            }
            sGItm.click(interaction);
            c.sleep(1000);
        }
    }

}
