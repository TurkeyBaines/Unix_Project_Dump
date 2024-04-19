package org.unix.winter;

import simple.api.coords.WorldArea;
import simple.api.coords.WorldPoint;

public class Constants {

    public static class AREAS {
        public static final WorldArea IN_BANK = new WorldArea(new WorldPoint(1637, 3942, 0), new WorldPoint(1641, 3946, 0));
        public static final WorldArea IN_WAITING = new WorldArea(new WorldPoint(1638, 3964, 0), new WorldPoint(1624, 3937, 0));
        public static final WorldPoint SPAWN_LOBBY = new WorldPoint(1630, 3975, 0);
        public WorldArea NE_LOC = new WorldArea(new WorldPoint(1649, 4020, 0), new WorldPoint(1633, 4015, 0));
    }

    public static class PATHS {
        public static WorldPoint[] PATH_TO_NE = new WorldPoint[] {
                new WorldPoint(1633, 3982, 0),
                new WorldPoint(1639, 3992, 0),
                new WorldPoint(1645, 4002, 0),
                new WorldPoint(1648, 4007, 0),
                new WorldPoint(1642, 4018, 0)
        };
    }

    public static class OBJECTS {
        public static final int BRUMA_HERBS = 29315;
        public static final int BRUMA_ROOTS = 29311;
        public static final int BLIZARD = 26690;
        public static final int[] BRAZIER = {21312, 21313, 21314};
    }

    public static class ITEMS {
        public static final int[] POTION = {50699, 50700, 50701, 50702};
        public static final int LOGS = 50695;
        public static final int KINGLING = 50696;
        public static final int CRATE = 50703;
    }

    public static class NPCS {
        public static final int[] PYRO = {27371, 27372};
    }

    public static class ANIM {
        public static final int PLAYER_WC = 877;
        public static final int PLAYER_FLETCH = 1248;
    }

    public static class WIDGETS {
        public static final int RETURNING_IN = 51413;
        public static final int ENERGY_REMAINING = 51412;
    }

}
