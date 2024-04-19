package org.unix.agility.course;

import net.runelite.api.coords.WorldPoint;
import org.unix.Database;
import org.unix.Task;
import simple.robot.utils.WorldArea;

import static org.unix.Database.*;
import static org.unix.Methods.*;

public class Gnome extends Task {

    WorldArea tpLoc = new WorldArea(new WorldPoint(2463, 3382, 0), new WorldPoint(2457, 3377, 0));
    WorldPoint[] pathToArea = new WorldPoint[]{
            new WorldPoint(2461, 3385, 0),
            new WorldPoint(2461, 3401, 0),
            new WorldPoint(2460, 3418, 0),
            new WorldPoint(2462, 3434, 0),
            new WorldPoint(2474, 3436, 0)
    };

    @Override
    public void run() {

        if (areas.GNOME_AGILITY_COURSE.within(true)) {

        } else if (areas.HOME.get().containsPoint(loc)) {
            teleportTo("Skilling", "Tree Gnome Stronghold");
        }

    }
}
