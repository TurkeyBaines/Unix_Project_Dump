package org.unix.slayer.setup;

import org.unix.slayer.Constants;
import org.unix.slayer.uSlayer;
import org.unix.slayer.setup.A.A_Presets;
import simple.api.ClientContext;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SetupManager {

    private static ClientContext ctx;
    private static uSlayer client;
    public static boolean READY = false;

    public boolean guiOpen = false;

    private static List<SetupConfig> total_configs = new ArrayList<>();

    public static SetupConfig currentConfig;

    public SetupManager(ClientContext CTX, uSlayer Client) {
        if (!guiOpen) {
            ctx = CTX;
            client = Client;
            checkExisting();
            new A_Presets().setVisible(true);
            guiOpen = true;
        }
    }

    public void checkExisting() {

        String n = "";
        int d = -1;
        String[] tS = {};
        String[] tK = {};
        int mP = -1;
        int rP = -1;
        int maP = -1;
        String[] tL = {};
        String[] f = {};
        String[] p = {};
        int hpEA = -1;
        int tP = -1;
        int tB = -1;
        int tPP = -1;

        File dir = new File(Constants.USER_DIR + "/presets/");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String[] presets = dir.list();
        System.out.println(presets.length);
        for (String s : presets) {
            try {
                n = s;
                String line;
                File curr = new File(dir + "/" + s + "/");
                BufferedReader reader = new BufferedReader(new FileReader(curr + "/tasks.cfg"));
                boolean toSkip = false;
                List<String> Kill = new ArrayList<>();
                List<String> Skip = new ArrayList<>();
                while ((line = reader.readLine()) != null) {
                    if (line.contains("Difficulty:")) { d = Integer.parseInt(line.split(":")[1]); }
                    else if (line.equalsIgnoreCase("-- [KILL] --")) {}
                    else if (line.equalsIgnoreCase("-- [SKIP] --")) { toSkip = true; }
                    else if (!toSkip) {
                        Kill.add(line);
                    } else {
                        Skip.add(line);
                    }
                }
                String[] skip = new String[Skip.size()];
                String[] kill = new String[Kill.size()];
                for (int i=0;i<Skip.size();i++) {
                    skip[i] = Skip.get(i);
                }
                for (int i=0;i< Kill.size();i++) {
                    kill[i] = Kill.get(i);
                }
                tS = skip;
                tK = kill;
                reader.close();

                reader = new BufferedReader(new FileReader(curr + "/equipment.cfg"));
                while ((line = reader.readLine()) != null) {
                    if (line.contains("Melee-")) {
                        mP = Integer.parseInt(line.split(":")[1]);
                    }
                    if (line.contains("Range-")) {
                        rP = Integer.parseInt(line.split(":")[1]);
                    }
                    if (line.contains("Mage-")) {
                        maP = Integer.parseInt(line.split(":")[1]);
                    }
                }
                reader.close();

                reader = new BufferedReader(new FileReader(curr + "/loot.cfg"));
                List<String> Loot = new ArrayList<>();
                while ((line = reader.readLine()) != null) {
                    Loot.add(line);
                }
                if (!Loot.isEmpty()) {
                    String[] loot = new String[Loot.size()];
                    for (int i=0;i<Loot.size();i++) {
                        loot[i] = Loot.get(i);
                    }
                    tL = loot;
                }
                reader.close();

                reader = new BufferedReader(new FileReader(curr + "/consumables.cfg"));
                List<String> Food = new ArrayList();
                while ((line = reader.readLine()) != null) {
                    System.out.println("L:" + line);
                    if (line.contains("Heal:")) {
                        hpEA = Integer.parseInt(line.split(":")[1]);
                    } else {
                        Food.add(line);
                    }
                }
                if (!Food.isEmpty()) {
                    String[] food = new String[Food.size()];
                    for (int i = 0; i < Food.size(); i++) {
                        food[i] = Food.get(i);
                    }
                    f = food;
                }

                reader.close();

                reader = new BufferedReader(new FileReader(curr + "/prayer.cfg"));
                while ((line = reader.readLine()) != null) {
                    if (line.contains("Protect")) {
                        tP = Integer.parseInt(line.split(":")[1]);
                    } else if (line.contains("Boost:")) {
                        tB = Integer.parseInt(line.split(":")[1]);
                    } else if (line.contains("Potion:")) {
                        tPP = Integer.parseInt(line.split(":")[1]);
                    }
                }
                reader.close();

                total_configs.add(new SetupConfig(n, d, tS, tK, mP, rP, maP, tL, f, p, hpEA, tP, tB, tPP));
                System.out.println("Data: |"+n+
                        "|"+d+","+tS.length+","+tK.length+
                        "|"+mP+","+rP+","+maP+"|"+tL.length+
                        ","+f.length+","+p.length+
                        ","+hpEA+"|"+tP+","+tB+","+tPP+"|");

            } catch (FileNotFoundException fileNotFoundException) {fileNotFoundException.printStackTrace();} catch (IOException ioException) { ioException.printStackTrace();}
        }
        System.out.println("total_preset:" + total_configs.size());
    }

    public static void createNewConfig(String name) {
        total_configs.add(new SetupConfig(name, ctx, client));
    }

    public static List<SetupConfig> getTotalList() {
        return total_configs;
    }

    public static void finished() {
        client.taskHandler.difficulty = currentConfig.Difficulty;
        Constants.MELEE_PRESET = currentConfig.MeleePreset;
        Constants.RANGE_PRESET = currentConfig.RangePreset;
        Constants.MAGE_PRESET = currentConfig.MagePreset;
        Constants.LOOT_IDS = new int[currentConfig.ToLoot.length];
        Constants.FOOD_IDS = new int[currentConfig.Food.length];
        for (int i=0;i<currentConfig.ToLoot.length;i++) {
            Constants.LOOT_IDS[i] = Integer.parseInt(currentConfig.ToLoot[i]);
        }
        Constants.LOOT_ENABLE = Constants.LOOT_IDS.length > 0 ? true : false;
        for (int i=0;i<currentConfig.Food.length;i++) {
            Constants.FOOD_IDS[i] = Integer.parseInt(currentConfig.Food[i]);
        }
        Constants.EATING = Constants.FOOD_IDS.length > 0 ? true : false;
        Constants.PRAY_POTION = (currentConfig.ToPrayerPotion == 1);
        Constants.PRAY_PROTECT = (currentConfig.ToProtect == 1);
        Constants.PRAY_BOOST = (currentConfig.ToBoost == 1);
        Constants.EAT_AT = currentConfig.HPToEatAt;
        Constants.TASKS_KILL = currentConfig.ToKill;
        Constants.TASKS_SKIP = currentConfig.ToSkip;

        uSlayer.setupComplete = true;
    }
}
