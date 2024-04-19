package org.unix.runex.agility.course;

import simple.api.coords.WorldArea;
import simple.api.coords.WorldPoint;

public class Pollnivneach extends Course {

    public WorldPoint b = new WorldPoint(2327, 6036, 1);
    public WorldPoint c2 = new WorldPoint(2328, 6045, 1);
    public WorldPoint d = new WorldPoint(2336, 6050, 1);
    public WorldPoint e = new WorldPoint(2342, 6048, 1);
    public WorldPoint f = new WorldPoint(2344, 6054, 1);
    public WorldPoint g = new WorldPoint(2341, 6055, 2);
    public WorldPoint h = new WorldPoint(2334, 6063, 2);
    public WorldPoint i = new WorldPoint(2335, 6072, 2);
    public WorldPoint j = new WorldPoint(2338, 6070, 0);

    @Override
    public String name() {
        return "Pollnivneach";
    }

    @Override
    public int reqLevel() {
        return 70;
    }

    @Override
    public int upgradeLevel() {
        return 80;
    }

    @Override
    public WorldPoint tpLoc() {
        return new WorldPoint(2327, 6033, 0);
    }

    @Override
    public void runtime() {
        if (tpLoc().equals(loc)) {
            interactWithID(11380);
        } else if (b.equals(loc)) {
            interactWithID(11381);
        } else if (c2.equals(loc)) {
            interactWithID(11382);
        } else if (d.equals(loc)) {
            interactWithID(11383);
        } else if (e.equals(loc)) {
            interactWithID(11384);
        } else if (f.equals(loc)) {
            interactWithID(11385);
        } else if (g.equals(loc)) {
            interactWithID(11386);
        } else if (h.equals(loc)) {
            interactWithID(11389);
        } else if (i.equals(loc)) {
            interactWithID(11390);
        } else if (j.equals(loc)) {
            travelTo();
        }
    }

    @Override
    public String nextText() {
        return "rellekka";
    }

    @Override
    public WorldArea[] worldArea() {
        return new WorldArea[]{
                new WorldArea(new WorldPoint(2350, 6078, 0), new WorldPoint(2320, 6029, 0)),
                new WorldArea(new WorldPoint(2350, 6078, 1), new WorldPoint(2320, 6029, 1)),
                new WorldArea(new WorldPoint(2350, 6078, 2), new WorldPoint(2320, 6029, 2)),
                new WorldArea(new WorldPoint(2350, 6078, 3), new WorldPoint(2320, 6029, 3))
        };
    }

    @Override
    public void travelTo() {
        c.menuActions.sendAction(315, 0, 0, 60376);
        c.menuActions.sendAction(315, 0, 0, 62045);
        c.sleep(2500);
    }
}
