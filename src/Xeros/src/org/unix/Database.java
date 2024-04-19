package org.unix;

import simple.api.coords.WorldArea;
import simple.api.coords.WorldPoint;

public class Database {

    public static class areas {

        public static final WorldArea home = new WorldArea(
                new WorldPoint(3075, 3481, 0),
                new WorldPoint(3098, 3514, 0)
        );

        public static final WorldArea[] warriors_guild = new WorldArea[]{
                new WorldArea(
                        new WorldPoint(2880, 3560, 0),
                        new WorldPoint(2835, 3530, 0)),
                new WorldArea(
                        new WorldPoint(2880, 3560, 1),
                        new WorldPoint(2835, 3530, 1)),
                new WorldArea(
                        new WorldPoint(2880, 3560, 2),
                        new WorldPoint(2835, 3530, 2))
        };

        public static final WorldArea warriors_guild_armour_room = new WorldArea(
                new WorldPoint(2862, 3546, 0),
                new WorldPoint(2848, 3533, 0)
        );

    }

    public static class npcs {
        public static final int[] zulrah = {2043, 2042, 2044, 2047}; //Range, Mage, Melee, Jad
        public static final int snakeling = 2039;
        public static final int nurse = 373;
    }

    public static class items {
        public static class potions {
            public static final int vial = 229;
            public static final int[] anti_venom = {/*Extended*/ 22383, 22385, 22387, 22389, /*Normal*/ 12908, 12909, 12910, 12911};
            public static final int[] prayer_potion = {2434, 139, 141, 143};
        }

        public static class food {
            public static final int monkfish = 7946;
        }
    }

    public static class objects {

    }

}
