package org.unix.runex.agility.course;

import simple.api.coords.WorldArea;
import simple.api.coords.WorldPoint;

public class Canifis extends Course {

    public WorldPoint willow = new WorldPoint(1778, 6052, 2);
    public WorldPoint oak = new WorldPoint(1775, 6064, 2);
    public WorldPoint jump = new WorldPoint(1764, 6064, 2);
    public WorldPoint jump2 = new WorldPoint(1751, 6057, 3);
    public WorldPoint jump3 = new WorldPoint(1755, 6045,2);
    public WorldPoint vault = new WorldPoint(1761, 6036, 3);
    public WorldPoint jump4 = new WorldPoint(1782,6035, 2);
    public WorldPoint jump5 = new WorldPoint(1779, 6045, 0);

    @Override
    public String name() {
        return "Canifis";
    }

    @Override
    public int reqLevel() {
        return 40;
    }

    @Override
    public int upgradeLevel() {
        return 50;
    }

    @Override
    public WorldPoint tpLoc() {
        return new WorldPoint(1777, 6047, 0);
    }

    @Override
    public void runtime() {
        if (tpLoc().equals(loc)) {
            interactWithID(10819);
        } else if (willow.equals(loc)) {
            interactWithID(10820);
        } else if (oak.equals(loc)) {
            interactWithID(10821);
        } else if (jump.equals(loc)) {
            interactWithID(10828);
        } else if (jump2.equals(loc)) {
            interactWithID(10822);
        } else if (jump3.equals(loc)) {
            interactWithID(10831);
        } else if (vault.equals(loc)) {
            interactWithID(10823);
        } else if (jump4.equals(loc)) {
            interactWithID(10832);
        } else if (jump5.equals(loc)) {
            interactWithID(10819);
        }
    }

    @Override
    public String nextText() {
        return "falador";
    }

    @Override
    public WorldArea[] worldArea() {
        return new WorldArea[]{
                new WorldArea(new WorldPoint(1785, 6030, 0), new WorldPoint(1742, 6071, 0)),
                new WorldArea(new WorldPoint(1785, 6030, 1), new WorldPoint(1742, 6071, 1)),
                new WorldArea(new WorldPoint(1785, 6030, 2), new WorldPoint(1742, 6071, 2)),
                new WorldArea(new WorldPoint(1785, 6030, 3), new WorldPoint(1742, 6071, 3)),
        };
    }

    @Override
    public void travelTo() {
        c.menuActions.sendAction(315, 0, 0, 60376);
        c.menuActions.sendAction(315, 0, 0, 62030);
        c.sleep(2500);
    }
}
