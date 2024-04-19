package org.unix.runex.agility.course;

import simple.api.coords.WorldArea;
import simple.api.coords.WorldPoint;

public class Varrock extends Course {

    public WorldPoint wall1 = new WorldPoint(1619, 6038, 3);
    public WorldPoint washing = new WorldPoint(1608, 6038, 3);
    public WorldPoint leap = new WorldPoint(1597, 6040, 1);
    public WorldPoint balance = new WorldPoint(1592, 6030, 3);
    public WorldPoint jump = new WorldPoint(1594, 6022, 3);
    public WorldPoint leap2 = new WorldPoint(1618, 6023, 3);
    public WorldPoint leap3 = new WorldPoint(1636, 6027, 3);
    public WorldPoint hurdle = new WorldPoint(1636, 6034, 3);
    public WorldPoint jump2 = new WorldPoint(1636, 6041, 0);

    @Override
    public String name() {
        return "Varrock";
    }

    @Override
    public int reqLevel() {
        return 30;
    }

    @Override
    public int upgradeLevel() {
        return 40;
    }

    @Override
    public WorldPoint tpLoc() {
        return new WorldPoint(1623, 6038, 0);
    }

    @Override
    public void runtime() {
        if (tpLoc().equals(loc)) {
            interactWithID(10586);
        } else if (wall1.equals(loc)) {
            interactWithID(10587);
        } else if (washing.equals(loc)) {
            interactWithID(10642);
        } else if (leap.equals(loc)) {
            interactWithID(10777);
        } else if (balance.equals(loc)) {
            interactWithID(10778);
        } else if (jump.equals(loc)) {
            interactWithID(10779);
        } else if (leap2.equals(loc)) {
            interactWithID(10780);
        } else if (leap3.equals(loc)) {
            interactWithID(10781);
        } else if (hurdle.equals(loc)) {
            interactWithID(10817);
        } else if (jump2.equals(loc)) {
            interactWithID(10586);
        }
    }

    @Override
    public String nextText() {
        return "canifis";
    }

    @Override
    public WorldArea[] worldArea() {
        return new WorldArea[] {
                new WorldArea(new WorldPoint(1646, 6053, 0), new WorldPoint(1580, 6010, 0)),
                new WorldArea(new WorldPoint(1646, 6053, 1), new WorldPoint(1580, 6010, 1)),
                new WorldArea(new WorldPoint(1646, 6053, 2), new WorldPoint(1580, 6010, 2)),
                new WorldArea(new WorldPoint(1646, 6053, 3), new WorldPoint(1580, 6010, 3))
        };
    }

    @Override
    public void travelTo() {
        c.menuActions.sendAction(315, 0, 0, 60376);
        c.menuActions.sendAction(315, 0, 0, 62025);
        c.sleep(2500);
    }
}
