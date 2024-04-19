package org.unix.slayer.setup;

import org.unix.slayer.uSlayer;
import simple.api.ClientContext;

public class SetupConfig {

    public SetupConfig(String name, ClientContext ClientContext, uSlayer Client) {
        presetName = name;
        client = Client;
        clientContext = ClientContext;
    }

    public SetupConfig(String name, int difficulty, String[] taskToSkip, String[] taskToKill, int meleePreset, int rangePreset, int magePreset, String[] loot, String[] food, String[] potions, int eatAt, int protection, int statBoost, int prayerPotion) {
        presetName = name;
        Difficulty = difficulty;
        ToSkip = taskToSkip;
        ToKill = taskToKill;
        MeleePreset = meleePreset;
        RangePreset = rangePreset;
        MagePreset = magePreset;
        ToLoot = loot;
        Food = food;
        Potions = potions;
        HPToEatAt = eatAt;
        ToProtect = protection;
        ToBoost = statBoost;
        ToPrayerPotion = prayerPotion;
    }

    public String presetName;

    public int Difficulty = -1; // -1=Unassigned | 0=Easy | 1=Medium | 2=Hard | 3=Brimstone | 4=Boss
    public String[] ToSkip;
    public String[] ToKill;

    public int MeleePreset = -1;
    public int RangePreset = -1;
    public int MagePreset = -1;

    public String[] ToLoot;
    public String[] Food;
    public String[] Potions;
    public int HPToEatAt = -1;

    public int ToProtect = -1;
    public int ToBoost = -1;
    public int ToPrayerPotion = -1;

    public ClientContext clientContext;
    public uSlayer client;

}
