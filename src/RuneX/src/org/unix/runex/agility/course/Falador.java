package org.unix.runex.agility.course;

import simple.api.coords.WorldArea;
import simple.api.coords.WorldPoint;

public class Falador extends Course {

    public WorldPoint wall1 = new WorldPoint(1948, 6031, 3);
    public WorldPoint rope1 = new WorldPoint(1959, 6031, 3);
    public WorldPoint handhold = new WorldPoint(1962, 6045, 3);
    public WorldPoint gap1 = new WorldPoint(1959, 6049, 3);
    public WorldPoint gap2 = new WorldPoint(1953, 6049, 3);
    public WorldPoint rope2 = new WorldPoint(1939, 6043, 3);
    public WorldPoint rope3 = new WorldPoint(1933, 6041, 3);
    public WorldPoint gap3 = new WorldPoint(1929, 6036, 3);
    public WorldPoint ledge1 = new WorldPoint(1926, 6034, 3);
    public WorldPoint ledge2 = new WorldPoint(1924, 6030, 3);
    public WorldPoint ledge3 = new WorldPoint(1925, 6021, 3);
    public WorldPoint ledge4 = new WorldPoint(1931, 6021, 3);
    public WorldPoint ledge5 = new WorldPoint(1941, 6021, 0);

    @Override
    public String name() {
        return "Falador";
    }

    @Override
    public int reqLevel() {
        return 50;
    }

    @Override
    public int upgradeLevel() {
        return 60;
    }

    @Override
    public WorldPoint tpLoc() {
        return new WorldPoint(1948, 6028, 0);
    }

    @Override
    public void runtime() {
        if (tpLoc().equals(loc)) {
            interactWithID(10833);
        } else if (wall1.equals(loc)) {
            interactWithID(10834);
        } else if (rope1.equals(loc)) {
            interactWithID(10836);
        } else if (handhold.equals(loc)) {
            interactWithID(11161);
        } else if (gap1.equals(loc)) {
            interactWithID(11360);
        } else if (gap2.equals(loc)) {
            interactWithID(11361);
        } else if (rope2.equals(loc)) {
            interactWithID(11364);
        } else if (rope3.equals(loc)) {
            interactWithID(11365);
        } else if (gap3.equals(loc)) {
            interactWithID(11366);
        } else if (ledge1.equals(loc)) {
            interactWithID(11367);
        } else if (ledge2.equals(loc)) {
            interactWithID(11368);
        } else if (ledge3.equals(loc)) {
            interactWithID(11370);
        } else if (ledge4.equals(loc)) {
            interactWithID(11371);
        } else if (ledge5.distanceTo(loc) < 3) {
            interactWithID(10833);
        }
    }

    @Override
    public String nextText() {
        return "seers";
    }

    @Override
    public WorldArea[] worldArea() {
        return new WorldArea[] {
                new WorldArea(new WorldPoint(1920, 6018, 0), new WorldPoint(1965, 6057, 0)),
                new WorldArea(new WorldPoint(1920, 6018, 1), new WorldPoint(1965, 6057, 1)),
                new WorldArea(new WorldPoint(1920, 6018, 2), new WorldPoint(1965, 6057, 2)),
                new WorldArea(new WorldPoint(1920, 6018, 3), new WorldPoint(1965, 6057, 3))
        };
    }

    @Override
    public void travelTo() {
        c.menuActions.sendAction(315, 0, 0, 60376);
        c.menuActions.sendAction(315, 0, 0, 62035);
        c.sleep(2500);
    }
}
