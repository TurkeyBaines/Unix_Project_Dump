package org.unix.runex.zitt;

import org.unix.runex.zitt.task.AbyssalDemons;
import org.unix.runex.zitt.task.Jellys;
import simple.api.coords.WorldArea;
import simple.api.coords.WorldPoint;

public class Constants {

    public static final int SLAYER_MASTER = 1596;

    public static final int[] TASK_INFO_WIDGET = {4882, 2};

    public static final int ATTACK = 412;
    public static final int CLICK = 502;

    public static final WorldArea HOME_AREA = new WorldArea(new WorldPoint(3076,3481,0), new WorldPoint(3098, 3499, 0));

    public static final WorldArea JELLY_AREA = new Jellys().getFightingArea();
    public static final WorldPoint JELLY_NEW_TASK = new WorldPoint(1691, 10011, 0);

    public static final WorldArea DEMON_AREA = new AbyssalDemons().getFightingArea();
    public static final WorldPoint DEMO_NEW_TASK = new WorldPoint(1691, 10011, 0);
}
