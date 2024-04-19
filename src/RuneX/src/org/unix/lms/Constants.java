package org.unix.lms;

import simple.api.coords.WorldArea;
import simple.api.coords.WorldPoint;

public class Constants {

    // Lobby
    public static class Lobby {
        public static final int ALTAR = 31923;
        public static final int LISA = 27317;

        public static final WorldArea LOBBY = new WorldArea(new WorldPoint(3415, 3185, 0), new WorldPoint(3410, 3181, 0));
        public static final WorldArea GENERAL_AREA = new WorldArea(new WorldPoint(3411, 3186, 0), new WorldPoint(3399, 3171, 0));


    }

    public static class GAME {
        public static final WorldArea ARENA = new WorldArea(new WorldPoint(3520, 5893), new WorldPoint(3390, 5759));

        public static final int HUMAN = 300;

        public static enum SPEC_WEAPONS {
            DRAGON_DAGGER(25);

            int percent;
            SPEC_WEAPONS(int Percent) {
                percent = Percent;
            }
            public int getPercent() {
                return percent;
            }
        }
    }



}
