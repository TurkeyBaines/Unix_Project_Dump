package org.unix.runex.agility.course;

import simple.api.coords.WorldArea;
import simple.api.coords.WorldPoint;

public class Rellekka extends Course {

    public WorldPoint climb = new WorldPoint(2433, 6044, 3);
    public WorldPoint leap = new WorldPoint(2430, 6036, 3);
    public WorldPoint rope1 = new WorldPoint(2434, 6022, 3);
    public WorldPoint rope2 = new WorldPoint(2447, 6021, 3);
    public WorldPoint hurdle = new WorldPoint(2452, 6025, 3);
    public WorldPoint rope3 = new WorldPoint(2462, 6038, 3);
    public WorldPoint fish = new WorldPoint(2461, 6044, 0);

    @Override
    public String name() {
        return "Rellekka";
    }

    @Override
    public int reqLevel() {
        return 80;
    }

    @Override
    public int upgradeLevel() {
        return 90;
    }

    @Override
    public WorldPoint tpLoc() {
        return new WorldPoint(2433, 6046, 0);
    }

    @Override
    public void runtime() {
        if (tpLoc().equals(loc)) {
            interactWithID(11391);
        } else if (climb.equals(loc)) {
            interactWithID(11392);
        } else if (leap.equals(loc)) {
            interactWithID(11393);
        } else if (rope1.equals(loc)) {
            interactWithID(11395);
        } else if (rope2.equals(loc)) {
            interactWithID(11396);
        } else if (hurdle.equals(loc)) {
            interactWithID(11397);
        } else if (rope3.equals(loc)) {
            interactWithID(11404);
        } else if (fish.equals(loc)) {
            travelTo();
        }
    }

    @Override
    public String nextText() {
        return "ardougne";
    }

    @Override
    public WorldArea[] worldArea() {
        return new WorldArea[]{
                new WorldArea(new WorldPoint(2472, 6016, 0), new WorldPoint(2420, 6050, 0)),
                new WorldArea(new WorldPoint(2472, 6016, 1), new WorldPoint(2420, 6050, 1)),
                new WorldArea(new WorldPoint(2472, 6016, 2), new WorldPoint(2420, 6050, 2)),
                new WorldArea(new WorldPoint(2472, 6016, 3), new WorldPoint(2420, 6050, 3)),
        };
    }

    @Override
    public void travelTo() {
        c.menuActions.sendAction(315, 0, 0, 60376);
        c.menuActions.sendAction(315, 0, 0, 62050);
        c.sleep(2500);
    }
}
