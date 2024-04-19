package org.unix.runex.agility.course;

import simple.api.coords.WorldArea;
import simple.api.coords.WorldPoint;

public class Alkharid extends Course {

    public WorldPoint wall = new WorldPoint(1417, 6072, 3);
    public WorldPoint rope1 = new WorldPoint(1416, 6051, 3);
    public WorldPoint cable = new WorldPoint(1427, 6046, 3);
    public WorldPoint zip = new WorldPoint(1459, 6043, 1);
    public WorldPoint hop = new WorldPoint(1462, 6054, 2);
    public WorldPoint beam = new WorldPoint(1460, 6060, 3);
    public WorldPoint rope2 = new WorldPoint(1446, 6066, 3);
    public WorldPoint ledge = new WorldPoint(1442, 6073, 0);

    @Override
    public String name() {
        return "Al Kharid";
    }

    @Override
    public int reqLevel() {
        return 20;
    }

    @Override
    public int upgradeLevel() {
        return 30;
    }

    @Override
    public WorldPoint tpLoc() {
        return new WorldPoint(1417, 6077, 0);
    }

    @Override
    public void runtime() {
        if (tpLoc().equals(loc)) {
            interactWithID(10093);
        } else if (wall.equals(loc)) {
            interactWithID(10284);
        } else if (rope1.equals(loc)) {
            interactWithID(10355);
        } else if (cable.equals(loc)) {
            interactWithID(10356);
        } else if (zip.equals(loc)) {
            interactWithID(10357);
        } else if (hop.equals(loc)) {
            interactWithID(10094);
        } else if (beam.equals(loc)) {
            interactWithID(10583);
        } else if (rope2.equals(loc)) {
            interactWithID(10352);
        } else if (ledge.equals(loc)) {
            travelTo();
        }
    }

    @Override
    public String nextText() {
        return "varrock";
    }

    @Override
    public WorldArea[] worldArea() {
        return new WorldArea[] {
                new WorldArea(new WorldPoint(1411, 6082, 0), new WorldPoint(1465, 6034, 0)),
                new WorldArea(new WorldPoint(1411, 6082, 1), new WorldPoint(1465, 6034, 1)),
                new WorldArea(new WorldPoint(1411, 6082, 2), new WorldPoint(1465, 6034, 2)),
                new WorldArea(new WorldPoint(1411, 6082, 3), new WorldPoint(1465, 6034, 3))
        };
    }

    @Override
    public void travelTo() {
        c.menuActions.sendAction(315, 0, 0, 60376);
        c.menuActions.sendAction(315, 0, 0, 62020);
        c.sleep(2500);
    }
}
