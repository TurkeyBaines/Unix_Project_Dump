package org.unix.runex.agility.course;

import simple.api.coords.WorldArea;
import simple.api.coords.WorldPoint;

public class Seers extends Course {

    public WorldPoint wall1 = new WorldPoint(2089, 6050, 3);
    public WorldPoint jump1 = new WorldPoint(2073, 6054, 2);
    public WorldPoint rope1 = new WorldPoint(2070, 6041, 2);
    public WorldPoint jump2 = new WorldPoint(2072, 6032, 3);
    public WorldPoint jump3 = new WorldPoint(2060, 6025, 2);
    public WorldPoint jump4 = new WorldPoint(2064, 6023, 0);

    @Override
    public String name() {
        return "Seers Village";
    }

    @Override
    public int reqLevel() {
        return 60;
    }

    @Override
    public int upgradeLevel() {
        return 70;
    }

    @Override
    public WorldPoint tpLoc() {
        return new WorldPoint(2089, 6048, 0);
    }

    @Override
    public void runtime() {
        if (tpLoc().equals(loc)) {
            interactWithID(11373);
        } else if (wall1.equals(loc)) {
            interactWithID(11374);
        } else if (jump1.equals(loc)) {
            interactWithID(11378);
        } else if (rope1.equals(loc)) {
            interactWithID(11375);
        } else if (jump2.equals(loc)) {
            interactWithID(11376);
        } else if (jump3.equals(loc)) {
            interactWithID(11377);
        } else if (jump4.equals(loc)) {
            travelTo();
        }
    }

    @Override
    public String nextText() {
        return "pollnivneach";
    }

    @Override
    public WorldArea[] worldArea() {
        return new WorldArea[]{
                new WorldArea(new WorldPoint(2047, 6019, 0), new WorldPoint(2093, 6061, 0)),
                new WorldArea(new WorldPoint(2047, 6019, 1), new WorldPoint(2093, 6061, 1)),
                new WorldArea(new WorldPoint(2047, 6019, 2), new WorldPoint(2093, 6061, 2)),
                new WorldArea(new WorldPoint(2047, 6019, 3), new WorldPoint(2093, 6061, 3))
        };
    }

    @Override
    public void travelTo() {
        c.menuActions.sendAction(315, 0, 0, 60376);
        c.menuActions.sendAction(315, 0, 0, 62040);
        c.sleep(2500);
    }
}
