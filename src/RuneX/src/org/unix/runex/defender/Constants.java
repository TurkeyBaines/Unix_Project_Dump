package org.unix.runex.defender;

import simple.api.coords.WorldArea;
import simple.api.coords.WorldPoint;

public class Constants {

    public static boolean JUST_COLLECT_TOKENS = false;

    public static final WorldArea HOME_AREA = new WorldArea(
            new WorldPoint(3075,3481,0),
            new WorldPoint(3098, 3514, 0)
    );

    public static final WorldArea[] GUILD_AREA = new WorldArea[] {
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

    public static final WorldArea ARMOUR_ROOM = new WorldArea(
            new WorldPoint(2862, 3546, 0),
            new WorldPoint(2848, 3533, 0)
    );

    public static final int[][] ARMOUR_IDS = new int[][] {
            new int[] {1155, 1117, 1075},
            new int[] {1153, 1115, 1067},
            new int[] {1157, 1119, 1069},
            new int[] {1159, 1121, 1071},
            new int[] {1161, 1123, 1073},
            new int[] {1163, 1127, 1079}
    };

    public static final int[] DEFENDER_IDS = new int[] {
        8845, 8846, 8847, 8848, 8849, 8850
    };

    public static final int TOKEN_ID = 8851;

    public static final String[] ARMOUR_NAMES = new String[] {"Bronze", "Iron", "Steel", "Mithril", "Adamant", "Rune"};
    public static int SELECTED_ARMOUR_INDEX = 4;

}
