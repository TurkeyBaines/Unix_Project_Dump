package org.unix.runex.agility.course;

import simple.api.coords.WorldArea;
import simple.api.coords.WorldPoint;

public class Ardougne extends Course {

    public WorldPoint beams = new WorldPoint(2735, 6051, 3);
    public WorldPoint gap1 = new WorldPoint(2729, 6070, 3);
    public WorldPoint plank = new WorldPoint(2720, 6070, 3);
    public WorldPoint gap2 = new WorldPoint(2717, 6066, 3);
    public WorldPoint gap3 = new WorldPoint(2715, 6060, 3);
    public WorldPoint roof = new WorldPoint(2720, 6049, 3);
    public WorldPoint gap4 = new WorldPoint(2721, 6048, 0);

    @Override
    public String name() {
        return "Ardougne";
    }

    @Override
    public int reqLevel() {
        return 90;
    }

    @Override
    public int upgradeLevel() {
        return 99;
    }

    @Override
    public WorldPoint tpLoc() {
        return new WorldPoint(2738, 6049, 0);
    }

    @Override
    public void runtime() {
        if (tpLoc().equals(loc)) {
            interactWithID(11405);
        } else if (beams.equals(loc)) {
            interactWithID(11406);
        } else if (gap1.equals(loc)) {
            interactWithID(11631);
        } else if (plank.equals(loc)) {
            interactWithID(11429);
        } else if (gap2.equals(loc)) {
            interactWithID(11430);
        } else if (gap3.equals(loc)) {
            interactWithID(11633);
        } else if (roof.equals(loc)) {
            interactWithID(11630);
        } else if (gap4.equals(loc)) {
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
                new WorldArea(new WorldPoint(2743, 6040, 0), new WorldPoint(2705, 6078, 0)),
                new WorldArea(new WorldPoint(2743, 6040, 1), new WorldPoint(2705, 6078, 1)),
                new WorldArea(new WorldPoint(2743, 6040, 2), new WorldPoint(2705, 6078, 2)),
                new WorldArea(new WorldPoint(2743, 6040, 3), new WorldPoint(2705, 6078, 3))
        };
    }

    @Override
    public void travelTo() {
        c.menuActions.sendAction(315, 0, 0, 60376);
        c.menuActions.sendAction(315, 0, 0, 62055);
        c.sleep(2500);
    }
}
