package org.unix.runex.defender.tasks;

import org.unix.runex.defender.Constants;
import simple.api.queries.SimpleEntityQuery;
import simple.api.wrappers.SimpleGroundItem;

public class GetTokens extends Task {

    public GetTokens(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public void run() {

        if (!c.inventory.populate().filter(Constants.TOKEN_ID).isEmpty()) {
            if (c.inventory.next().getQuantity() > 10000) {
                tm.currentTask = tm.getTask("cyclops");
                c.sleep(250);
                return;
            }
        }

        if (!c.combat.autoRetaliate()) { c.combat.toggleAutoRetaliate(true); }
        if (!Constants.ARMOUR_ROOM.containsPoint(loc)) {
            c.menuActions.sendAction(315, 0, 0, 1540);
            c.sleep(250);
            c.menuActions.sendAction(315, 0, 0, 18522);
            c.sleep(250);
            c.menuActions.sendAction(315, 0, 0, 63745);
            c.sleep(2500);
            return;
        }

        SimpleEntityQuery<SimpleGroundItem> fm = c.groundItems.populate().filter(Constants.ARMOUR_IDS[Constants.SELECTED_ARMOUR_INDEX]);
        if (!fm.isEmpty()) {
            fm.next().interact("Take");
            return;
        }

        SimpleEntityQuery<SimpleGroundItem> tk = c.groundItems.populate().filter(Constants.TOKEN_ID);
        if (!tk.isEmpty()) {
            tk.next().interact("Take");
            return;
        }

        if (c.players.getLocal().inCombat() && c.players.getLocal().getInteracting() != null) {
            c.sleep(5000);
            return;
        }

        if (c.inventory.populate().filter(Constants.ARMOUR_IDS[Constants.SELECTED_ARMOUR_INDEX]).size() > 2) {
            int[] a = Constants.ARMOUR_IDS[Constants.SELECTED_ARMOUR_INDEX];
            int slot = c.inventory.populate().filter(a[0]).next().getSlot();
            c.menuActions.sendAction(447, a[0], slot, 3214);
            c.sleep(250);
            c.objects.populate().filter(15621).next().interact(62);
            c.sleep(5000);
        }
    }

}
