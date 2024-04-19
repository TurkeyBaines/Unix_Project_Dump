package org.unix.slayer.tasks.normal;

import org.unix.slayer.tasks.Task;
import org.unix.slayer.tasks.TaskHandler;
import simple.W;
import simple.api.coords.WorldArea;
import simple.api.coords.WorldPoint;
import simple.api.queries.SimpleEntityQuery;
import simple.api.wrappers.SimpleSceneObject;

public class UndeadDruid extends Task {
    public UndeadDruid(TaskHandler taskHandler) {
        super(taskHandler);
    }

    WorldPoint tpLoc = new WorldPoint(1958, 10165, 0);
    WorldPoint[] path = new WorldPoint[]{
            new WorldPoint(1952, 10165, 0),
            new WorldPoint(1938, 10158, 0)
    };

    @Override
    public void travel() {
        if (tpLoc.equals(loc)) {
            SimpleEntityQuery<SimpleSceneObject> sso = c.objects.populate().filter(34843);
            if (!sso.isEmpty()) {
                sso.next().interact(502);
                c.sleep(250);
            }
        }
    }

    @Override
    public int getId() {
        return 22145;
    }

    @Override
    public String getName() {
        return "Undead Druid";
    }

    @Override
    public int getStyle() {
        return 2;
    }

    @Override
    public boolean useProtPray() {
        return true;
    }

    @Override
    public WorldArea getBounds() {
        return new WorldArea(
                new WorldPoint(1945, 10167, 0),
                new WorldPoint(1930, 10150, 0)
        );
    }

    @Override
    public int forcedStyle() {
        return -1;
    }

    @Override
    public void tpToTask() {
        c.menuActions.sendAction(315, 0, 0, 13061);
        c.sleep(250);
        c.menuActions.sendAction(315, 0, 0, 45668);
        c.sleep(2500);
    }
}
