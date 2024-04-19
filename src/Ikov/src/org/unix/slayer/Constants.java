package org.unix.slayer;

import simple.api.ClientContext;
import simple.api.coords.WorldArea;
import simple.api.coords.WorldPoint;

public class Constants {

    public static String USER_DIR;

    public static final int SLAYER_MASTER = 1596;

    public static final int[] SELECT_TASK_ACTION = {315,1731,361,54113};

    public static final int[] TASK_INFO_WIDGET = {4882, 2};
    public static final int TASK_DIFFICULTY = 54100;

    public static final int ATTACK = 412;
    public static final int CLICK = 502;

    public static final WorldArea SLAYER_TOWER_0 = new WorldArea(new WorldPoint(3407, 3534, 0), new WorldPoint(3450,3577,0));
    public static final WorldArea SLAYER_TOWER_1 = new WorldArea(new WorldPoint(3407, 3534, 1), new WorldPoint(3450,3577,1));
    public static final WorldArea SLAYER_TOWER_2 = new WorldArea(new WorldPoint(3407, 3534, 2), new WorldPoint(3450,3577,2));

    public static final WorldArea HOME_AREA = new WorldArea(
            new WorldPoint(3075,3481,0),
            new WorldPoint(3098, 3514, 0)
    );

    // SETUP MANAGER - TASKS
    public static int TASKS_DIFFICULTY;
    public static String[] TASKS_KILL = {};
    public static String[] TASKS_SKIP = {};

    // SETUP MANAGER - EQUIPMENT
    public static int MELEE_PRESET = -1;
    public static int RANGE_PRESET = -1;
    public static int MAGE_PRESET = -1;
    public static final int DRAGON_PRESET = 5;

    // SETUP MANAGER - LOOT
    public static boolean LOOT_ENABLE = false;
    public static int[] LOOT_IDS = {};

    // SETUP MANAGER - CONSUMABLES
    public static boolean EATING = false;
    public static int EAT_AT = -1;
    public static int[] FOOD_IDS = {};
    public static boolean STAT_BOOST = false;
    public static int[] POTION_IDS = {};

    // SETUP MANAGER - PRAYER
    public static boolean PRAY_PROTECT = false;
    public static boolean PRAY_BOOST = false;
    public static boolean PRAY_POTION = false;

    public static final void TELE_HOME() {
        ClientContext.instance().menuActions.sendAction(315, 526, 513, 12856);
    }

}
