package org.unix;

import net.runelite.api.coords.WorldPoint;
import simple.hooks.wrappers.SimpleWidget;
import simple.robot.api.ClientContext;
import simple.robot.utils.WorldArea;

public class Database {

    private static ClientContext c = ClientContext.instance();

    public static class npcs {
        public static final int TELEPORT_WIZARD = 16048;

        public static final int TORAG = 1676;
        public static final int AHRIM = 1672;
        public static final int KARIL = 1675;
        public static final int DHAROK = 1673;
        public static final int GUTHAN = 1674;
        public static final int VERAC = 1677;
    }

    public static class objects {

        public static final int FEROX_BANK_CHEST = 26711;

        // BARROWS OBJECTS
        public static final int BARROWS_CHEST = 20973;
        public static final int DHAROK_SARCOPHAGUS = 20720;
        public static final int DHAROK_STAIRS = 20668;
        public static final int GUTHAN_SARCOPHAGUS = 20722;
        public static final int GUTHAN_STAIRS = 20669;
        public static final int VERAC_SARCOPHAGUS = 20772;
        public static final int VERAC_STAIRS = 20672;
        public static final int AHRIM_SARCOPHAGUS = 20770;
        public static final int AHRIM_STAIRS = 20667;
        public static final int KARIL_SARCOPHAGUS = 20771;
        public static final int KARIL_STAIRS = 20670;
        public static final int TORAG_SARCOPHAGUS = 20721;
        public static final int TORAG_STAIRS = 20671;

    }

    public static class items {
        public static final int SPADE = 952;
    }

    public static class potions {
        public static final int[] PRAYER_POTION = {-1,      143,141, 139,2434};
    }

    public static class food {
        public static final int MONKFISH = 7946;
    }

    public static class runes {
        public static final int FIRE_RUNE = 554;
        public static final int WATER_RUNE = 555;
        public static final int AIR_RUNE = 556;
        public static final int EARTH_RUNE = 557;
        public static final int MIND_RUNE = 558;
        public static final int BODY_RUNE = 559;
        public static final int DEATH_RUNE = 560;
        public static final int NATURE_RUNE = 561;
        public static final int CHAOS_RUNE = 562;
        public static final int LAW_RUNE = 563;
        public static final int COSMIC_RUNE = 564;
        public static final int BLOOD_RUNE = 565;
        public static final int SOUL_RUNE = 566;
        public static final int STEAM_RUNE = 4694;
        public static final int MIST_RUNE = 4695;
        public static final int DUST_RUNE = 4696;
        public static final int SMOKE_RUNE = 4697;
        public static final int MUD_RUNE = 4698;
        public static final int LAVA_RUNE = 4699;
    }

    public enum widgets {

        TELEPORT_MAIN(1028, 21),
        TELEPORT_FAVORITES(1028, 286),
        TELEPORT_FAV_1(1028, 289),
        TELEPORT_FAV_2(1028, 292),
        TELEPORT_FAV_3(1028, 295),
        TELEPORT_TOP_ENTRY(1028, 272),

        BARROWS_POTENTIAL(24, 10);

        int mast, sub;

        widgets(int Master, int Sub) {
            mast = Master;
            sub = Sub;
        }

        public SimpleWidget get() {
            try {
                return c.widgets.getWidget(mast, sub);
            } catch (NullPointerException e) {
                return null;
            }
        }

    }

    public enum areas {

        FEROX_BANK(new WorldArea(new WorldPoint(3137, 3640, 0), new WorldPoint(3123, 3625, 0))),
        BARROWS(new WorldArea(new WorldPoint(3584,3310, 0), new WorldPoint(3546, 3270, 0))),
        BARROWS_DHAROK(new WorldArea(new WorldPoint(3560,9721, 3), new WorldPoint(3548, 9710, 3))),
        BARROWS_DHAROK_DIG(new WorldArea(new WorldPoint(3577, 3300, 0), new WorldPoint(3573, 3297, 0))),
        BARROWS_VERAC(new WorldArea(new WorldPoint(3579, 9711, 3), new WorldPoint(3566, 9702, 3))),
        BARROWS_VERAC_DIG(new WorldArea(new WorldPoint(3559, 3299, 0), new WorldPoint(3555, 3295, 0))),
        BARROWS_TORAG(new WorldArea(new WorldPoint(3576,9694, 3), new WorldPoint(3563, 9683, 3))),
        BARROWS_TORAG_DIG(new WorldArea(new WorldPoint(3555, 3284, 0), new WorldPoint(3551, 3281, 0))),
        BARROWS_AHRIM(new WorldArea(new WorldPoint(3562,9705, 3), new WorldPoint(3550, 9694, 3))),
        BARROWS_AHRIM_DIG(new WorldArea(new WorldPoint(3563, 3290, 0), new WorldPoint(3566, 3286, 0))),
        BARROWS_GUTHAN(new WorldArea(new WorldPoint(3547, 9710, 3), new WorldPoint(3533, 9699, 3))),
        BARROWS_GUTHAN_DIG(new WorldArea(new WorldPoint(3579, 3284, 0), new WorldPoint(3575, 3280, 0))),
        BARROWS_KARIL(new WorldArea(new WorldPoint(3558,9689, 3), new WorldPoint(3545, 9678, 3))),
        BARROWS_KARIL_DIG(new WorldArea(new WorldPoint(3567, 3277, 0), new WorldPoint(3564, 3274, 0))),
        BARROWS_LOOT(new WorldArea(new WorldPoint(3558, 9703, 0), new WorldPoint(3543, 9689, 0)));


        WorldArea wa;

        areas(WorldArea WorldArea) {
            wa = WorldArea;
        }

        public WorldArea get() {
            return wa;
        }
    }
}
