package org.unix.runex.agility.course;

import simple.api.coords.WorldArea;
import simple.api.coords.WorldPoint;

public class Draynor extends Course {

    public WorldPoint wall1 = new WorldPoint(1310, 6095, 3);
    public WorldPoint rope1 = new WorldPoint(1298, 6093, 3);
    public WorldPoint rope2 = new WorldPoint(1300, 6082, 3);
    public WorldPoint wall2 = new WorldPoint(1296, 6077, 3);
    public WorldPoint wall3 = new WorldPoint(1296, 6071, 3);
    public WorldPoint gap = new WorldPoint(1304, 6072, 3);
    public WorldPoint crate = new WorldPoint(1311, 6077, 0);

    @Override
    public String name() {
        return "Draynor";
    }

    @Override
    public int reqLevel() {
        return 10;
    }

    @Override
    public int upgradeLevel() {
        return 20;
    }

    @Override
    public WorldPoint tpLoc() {
        return new WorldPoint(1313, 6095, 0);
    }

    @Override
    public void runtime() {
        if (tpLoc().equals(loc)) {
            interactWithID(10073);
        } else if (wall1.equals(loc)) {
            interactWithID(10074);
        } else if (rope1.equals(loc)) {
            interactWithID(10075);
        } else if (rope2.equals(loc)) {
            interactWithID(10077);
        } else if (wall2.equals(loc)) {
            interactWithID(10084);
        } else if (wall3.equals(loc)) {
            interactWithID(10085);
        } else if (gap.equals(loc)) {
            interactWithID(10086);
        } else if (crate.equals(loc)) {
            interactWithID(10073);
        }
    }

    @Override
    public String nextText() {
        return "alkharid";
    }

    @Override
    public WorldArea[] worldArea() {
        return new WorldArea[]{
                new WorldArea(new WorldPoint(1317, 6065, 0), new WorldPoint(1290, 6098, 0)),
                new WorldArea(new WorldPoint(1317, 6065, 3), new WorldPoint(1290, 6098, 3))
        };
    }

    @Override
    public void travelTo() {
        c.menuActions.sendAction(315, 0, 0, 60376);
        c.menuActions.sendAction(315, 0, 0, 62015);
        c.sleep(2500);
    }
}
