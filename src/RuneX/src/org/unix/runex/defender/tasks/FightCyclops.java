package org.unix.runex.defender.tasks;

import org.unix.runex.defender.Constants;
import simple.api.actions.SimpleNpcActions;
import simple.api.coords.WorldArea;
import simple.api.coords.WorldPoint;
import simple.api.queries.SimpleEntityQuery;
import simple.api.wrappers.SimpleGroundItem;
import simple.api.wrappers.SimpleNpc;
import simple.api.wrappers.SimpleSceneObject;

public class FightCyclops extends Task {

    final int CYCLOPS_ID = 4291;
    boolean refresh_needed = false;

    WorldArea lobby = new WorldArea(
            new WorldPoint(2846, 3542, 2),
            new WorldPoint(2836, 3575, 2)
    );

    public FightCyclops(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public void run() {
        if (Constants.GUILD_AREA[0].containsPoint(loc)) {
            //WE'RE ON THE FIRST FLOOR
            if (Constants.ARMOUR_ROOM.containsPoint(loc)) {
                SimpleEntityQuery<SimpleSceneObject> gi = c.objects.populate().filter(15641);
                if (!gi.isEmpty()) {
                    gi.next().interact(502);
                    c.sleep(2500);
                }
            } else {
                SimpleEntityQuery<SimpleSceneObject> gi = c.objects.populate().filter(1738);
                if (!gi.isEmpty()) {
                    gi.next().interact(502);
                    c.sleep(2500);
                }
            }
        }

        if (Constants.GUILD_AREA[2].containsPoint(loc)) {
            //WE'RE UPSTAIRS
            if (lobby.containsPoint(loc)) {
                refresh_needed = false;
                if (!c.inventory.populate().filter(Constants.TOKEN_ID).isEmpty()) {
                    if (c.inventory.next().getQuantity() > 9) {
                        c.objects.populate().filter(15644).nextNearest().interact(502);
                        c.sleep(2500);
                    } else {
                        tm.currentTask = tm.getTask("tokens");
                    }
                } else {
                    tm.currentTask = tm.getTask("tokens");
                }
            } else {
                // LOOT
                SimpleEntityQuery<SimpleGroundItem> gi = c.groundItems.populate().filter(Constants.DEFENDER_IDS);
                if (!gi.isEmpty()) {
                    refresh_needed = true;
                    gi.next().interact("Take");
                    return;
                }

                if (refresh_needed) {
                    c.objects.populate().filter(15644).nextNearest().interact(502);
                    c.sleep(5000);
                    return;
                }

                if (c.players.getLocal().inCombat()) {
                    c.sleep(250);
                    return;
                }

                SimpleEntityQuery<SimpleNpc> fm = c.npcs.populate().filter((n) -> n.getId() == CYCLOPS_ID && n.getInteracting() == null && !n.isDead() && !n.isAnimating());
                SimpleNpc npc = fm.nearest().next();
                if (npc == null || npc.isDead() || npc.getInteracting() != null) {
                    npc = fm.nextNearest();
                }
                npc.interact(SimpleNpcActions.ATTACK);
                c.sleep(2500);
            }
        }
    }
}
