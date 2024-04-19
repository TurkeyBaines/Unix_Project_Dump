package org.unix;

import net.runelite.api.coords.WorldPoint;
import simple.hooks.interfaces.SimpleLocatable;
import simple.hooks.wrappers.SimpleNpc;
import simple.hooks.wrappers.SimpleObject;
import simple.hooks.wrappers.SimplePlayer;
import simple.hooks.wrappers.SimpleWidget;
import simple.robot.api.ClientContext;
import simple.robot.utils.WorldArea;

public class Database {

    private static ClientContext c = ClientContext.instance();

    public static class npcs {


        public static final int[] MAN = {3078, 3079, 3080};
        public static final int[] WOMAN = {3083, 3084, 3085};
        public static final int[] GUARD = {3245};
        public static final int KNIGHT_OF_ARDOUGNE = 3108;
        public static final int PALADIN = 3105;
        public static final int HERO = 3106;
        public static final int MASTER_FARMER = 3257;

        public static final int TORAG = 1676;
        public static final int AHRIM = 1672;
        public static final int KARIL = 1675;
        public static final int DHAROK = 1673;
        public static final int GUTHAN = 1674;
        public static final int VERAC = 1677;
    }

    public static class objects {

        public static final int FEROX_BANK_CHEST = 26711;
        public static final int[] BANK_BOOTH = { 6943, 6944 };
        public static final int TELEPORTER = 35000;

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


        // THIEVING OBJECTS
        public static final int CAKE_STALL = 11730;
        public static final int SILK_STALL = 11729;

        public static final int FISHING_NET_BAIT = 1518;
        public static final int[] FISHING_CAGE_HARPOON = {1510, 1519};
        public static final int[] FISHING_NET_HARPOON = {1511, 1520};
        public static final int[] FISHING_MINNOW = {7730, 7731, 7732, 7733};
        public static final int FISHING_FLY = 1526;

        public static final int BARBARIAN_FIRE = 26185;
        public static final int RANGE = 26181;
    }

    public static class items {
        public static final int SPADE = 952;
        public static final int COINS = 995;
        public static final int MARK_OF_GRACE = 11849;
        public static final int[] COIN_POUCH = {22521, 22527, 22531};
        public static final int[] DROP_THIEVING = { 950/*SILK*/ };

        public static final int[] ROGUES_OUTFIT = { 5553, 5554, 5555, 5556, 5557 };
        public static final int FLY_FISHING_ROD = 309;
        public static final int FEATHERS = 314;
        public static final int LOBSTER_POT = 301;
        public static final int HARPOON = 311;
    }

    public static class potions {
        public static final int[] PRAYER_POTION = {-1,      143,141, 139,2434};
    }

    public static class food {
        public static final int MINNOW = 21356;
        public static final int RAW_SHRIMP = 317;
        public static final int SHRIMP = 315;
        public static final int RAW_ANCHOVIES = 321;
        public static final int ANCHOVIES = 319;
        public static final int RAW_TROUT = 335;
        public static final int TROUT = 333;
        public static final int RAW_SALMON = 331;
        public static final int SALMON = 329;
        public static final int TUNA = 361;
        public static final int MONKFISH = 7946;
        public static final int RAW_LOBSTER = 377;
        public static final int LOBSTER = 379;
        public static final int RAW_SHARK = 383;
        public static final int SHARK = 385;
        public static final int[] BURNT_FOOD = {323, 324, 343, 344, 357, 358, 367, 368, 369, 370, 375, 376, 381, 382, 387, 388, 393, 394, 399};
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

        BANK_INTERFACE(12, 1),

        LEVEL_UP_MESSAGE(233, 1),

        COOK_INTERFACE(270, 14),

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
        HOME(new WorldArea(new WorldPoint(3111, 3513, 0), new WorldPoint(3080, 3487, 0))),

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
        BARROWS_LOOT(new WorldArea(new WorldPoint(3558, 9703, 0), new WorldPoint(3543, 9689, 0))),

        LUMBRIDGE_THIEF_SPOT(new WorldArea(new WorldPoint(8128, 5696, 0), new WorldPoint(3231, 3199, 0))),
        ARDOUGNE_MARKET(new WorldArea(new WorldPoint(2676, 3320, 0), new WorldPoint(2649, 3290, 0))),

        FISHING_GUILD_BANK(new WorldArea(new WorldPoint(2591, 3422, 0), new WorldPoint(2585, 3415, 0))),
        FISHING_GUILD_SPOT(new WorldArea(new WorldPoint(2606, 3427, 0), new WorldPoint(2594, 3418, 0))),
        FISHING_GUILD_REDUNDANT(new WorldArea(new WorldPoint(2594, 3419, 0), new WorldPoint(2590, 3415, 0))),

        GNOME_AGILITY_COURSE(new WorldArea(new WorldPoint(2490, 3439, 0), new WorldPoint(2468, 3414, 2))),
        /*
        DRAYNOR_AGILITY_COURSE(new WorldArea(new WorldPoint(), new WorldPoint())),
        VARROCK_AGILITY_COURSE(new WorldArea(new WorldPoint(), new WorldPoint())),
        CANIFIS_AGILITY_COURSE(new WorldArea(new WorldPoint(), new WorldPoint())),
        FALADOR_AGILITY_COURSE(new WorldArea(new WorldPoint(), new WorldPoint())),
        SEERS_AGILITY_COURSE(new WorldArea(new WorldPoint(), new WorldPoint())),
        POLNIEVICH_AGILITY_COURSE(new WorldArea(new WorldPoint(), new WorldPoint())),
        RELEKKA_AGILITY_COURSE(new WorldArea(new WorldPoint(), new WorldPoint())),
        ARDOUGNE_AGILITY_COURSE(new WorldArea(new WorldPoint(), new WorldPoint())),
        */

        DRAYNOR_BANK(new WorldArea(new WorldPoint(3095, 3246, 0), new WorldPoint(3092, 3242, 0)));




        WorldArea wa;

        areas(WorldArea WorldArea) {
            wa = WorldArea;
        }

        public WorldArea get() {
            return wa;
        }

        public boolean within(boolean IgnorePlane) {
            return wa.within(IgnorePlane);
        }

        public boolean contains(WorldPoint loc) {
            return wa.containsPoint(loc);
        }
        public boolean contains(SimpleLocatable sl) {
            return wa.containsPoint(sl);
        }
        public WorldPoint rnd() {
            return wa.randomTile();
        }
        public WorldPoint[] tiles() {
            return wa.getWorldPoints();
        }
    }

    public static class seeds {
        public static class herb {
            public static final int GUAM_SEED = 5291;
            public static final int MARRENTILL_SEED = 5292;
            public static final int TARROMIN_SEED = 5293;
            public static final int HARRALANDER_SEED = 5294;
            public static final int RANARR_SEED = 5295;
            public static final int TOADFLAX_SEED = 5296;
            public static final int IRIT_SEED = 5297;
            public static final int AVANTOE_SEED = 5298;
            public static final int KWUARM_SEED = 5299;
            public static final int SNAPDRAGON_SEED = 5300;
            public static final int CADANTINE_SEED = 5301;
            public static final int LANTADYME_SEED = 5302;
            public static final int DWARF_WEED_SEED = 5303;
            public static final int TORSTOL_SEED = 5303;

        }

        public static class hop {
            public static final int BARLEY_SEED = 5305;
            public static final int HAMMERSTONE_SEED = 5307;
            public static final int ASGARNIAN_SEED = 5308;
            public static final int JUTE_SEED = 5306;
            public static final int YANILLIAN_SEED = 5309;
            public static final int KRANDORIAN_SEED = 5310;
            public static final int WILDBLOOD_SEED = 5311;
        }

        public static class flower {
            public static final int MARIGOLD_SEED = 5096;
            public static final int ROSEMARY_SEED = 5097;
            public static final int NASTURTIUM_SEED = 5098;
            public static final int WOAD_SEED = 5099;
            public static final int LIMPWURT_SEED = 5100;
            public static final int WHITE_LILY_SEED = 14589;
        }

        public static class allotment {
            public static final int POTATO_SEED = 5318;
            public static final int ONION_SEED = 5319;
            public static final int CABBAGE_SEED = 5324;
            public static final int TOMATO_SEED = 5322;
            public static final int SWEETCORN_SEED = 5320;
            public static final int STRAWBERRY_SEED = 5323;
            public static final int WATERMELON_SEED = 5321;
            public static final int SNAPE_GRASS_SEED = 28262;
        }

        public static class tree {
            public static final int ACORN_SEED = 5312;
            public static final int WILLOW_SEED = 5313;
            public static final int MAPLE_SEED = 5314;
            public static final int YEW_SEED = 5315;
            public static final int MAGIC_SEED = 5316;
            public static final int SPIRIT_SEED = 5317;
        }

        public static class fruit_tree {
            public static final int APPLE_TREE_SEED = 5283;
            public static final int BANANA_TREE_SEED = 5284;
            public static final int ORANGE_TREE_SEED = 5285;
            public static final int CURRY_TREE_SEED = 5286;
            public static final int PINEAPPLE_TREE_SEED = 5287;
            public static final int PAPAYA_TREE_SEED = 5288;
            public static final int PALM_TREE_SEED = 5289;
            public static final int DRAGONFRUIT_TREE_SEED = 48763;
        }
    }

    public enum tiles {

        ARDOUGNE_SILK_STALL(new WorldPoint(2662, 3316, 0)),
        ARDOUGNE_CAKE_STALL(new WorldPoint(2669, 3310, 0)),
        DRAYNOR_THIEVING_SPOT(new WorldPoint(3087, 3249, 0));

        WorldPoint worldPoint;

        tiles(WorldPoint wp) {
            worldPoint = wp;
        }

        public WorldPoint get() {return worldPoint;}
    }

    public static class graphics {
        public static final int STUNNED = 80;
    }

    public static class animations {
        public static final int FISHING = 621;
    }
}
