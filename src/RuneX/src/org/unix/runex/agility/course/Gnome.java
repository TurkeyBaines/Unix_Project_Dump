package org.unix.runex.agility.course;

import org.unix.runex.agility.course.Course;
import simple.api.coords.WorldArea;
import simple.api.coords.WorldPoint;
import simple.api.queries.SimpleEntityQuery;
import simple.api.wrappers.SimpleSceneObject;

public class Gnome extends Course {

    public WorldPoint log = new WorldPoint(2474, 3429, 0);
    public WorldArea net1 = new WorldArea(new WorldPoint(2468, 3426, 1), new WorldPoint(2478, 3420, 1));
    public WorldPoint tree1 = new WorldPoint(2473, 3420, 2);
    public WorldPoint rope = new WorldPoint(2483, 3420, 2);
    public WorldPoint tree2 = new WorldPoint(2485, 3419, 0);
    public WorldArea net2 = new WorldArea(new WorldPoint(2482, 3426, 0), new WorldPoint(2489, 3428, 0));
    public WorldPoint pipe = new WorldPoint(2484, 3437, 0);

    @Override
    public String name() {
        return "Gnome";
    }

    @Override
    public int reqLevel() {
        return 0;
    }

    @Override
    public int upgradeLevel() {
        return 10;
    }

    @Override
    public WorldPoint tpLoc() {
        return new WorldPoint(2474, 3437, 0);
    }

    @Override
    public void runtime() {
        loc = c.players.getLocal().getLocation();

        if (tpLoc().equals(loc)) {
            interactWithID(2295);
        } else if (log.equals(loc)) {
            interactWithID(2285);
        } else if (net1.containsPoint(loc)) {
            interactWithID(2313);
        } else if (tree1.equals(loc)) {
            interactWithID(2312);
        } else if (rope.equals(loc)) {
            interactWithID(2314);
        } else if (tree2.equals(loc)) {
            interactWithID(2286);
        } else if (net2.containsPoint(loc)) {
            interactWithID(154);
        } else if (pipe.equals(loc)) {
            interactWithID(2295);
        }
    }

    @Override
    public String nextText() {
        return "draynor";
    }

    @Override
    public WorldArea[] worldArea() {
        return new WorldArea[] {
                new WorldArea(
                        new WorldPoint(2492, 3443, 0),
                        new WorldPoint(2466, 3411, 0)
                ),
                new WorldArea(
                        new WorldPoint(2492, 3443, 1),
                        new WorldPoint(2466, 3411, 1)
                ),
                new WorldArea(
                        new WorldPoint(2492, 3443, 2),
                        new WorldPoint(2466, 3411, 2)
                )
        };
    }

    @Override
    public void travelTo() {
        c.menuActions.sendAction(315, 0, 0, 60376);
        c.menuActions.sendAction(315, 0, 0, 62000);
        c.sleep(5000);
    }
}
