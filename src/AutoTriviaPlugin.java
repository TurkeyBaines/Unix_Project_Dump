package org.scripts.trivia;

import simple.hooks.simplebot.ChatMessage;
import simple.robot.api.ClientContext;
import java.util.HashMap;
import java.util.Map;

public class AutoTriviaPlugin {
    private ClientContext c = ClientContext.instance();
    private HashMap<String, String> triviaData;
    private final int minWait, maxWait;
    public AutoTriviaPlugin(int minWaitTime, int maxWaitTime) {
        triviaData = new HashMap<>();
        minWait = minWaitTime;
        maxWait = maxWaitTime;
        addTriviaQuestions();
    }

    public void checkChatMessage(ChatMessage chatMessage) {
        if (chatMessage.getMessage().contains("[trivia]") && chatMessage.getMessage().contains("[answer with ::answer]")) {
            for (Map.Entry<String, String> entry : triviaData.entrySet()) {
                if (chatMessage.getMessage().contains(entry.getKey())) {
                    c.sleep(minWait, maxWait);
                    c.keyboard.sendKeys("::answer " + entry.getValue());
                    break;
                }
            }
        }
    }

    private void addTriviaQuestions() {
        triviaData.put("on rs3, vorkath is controlled by which mahjarrat?", "zemouregal");
        triviaData.put("what is the home world of the elder gods, zaros, seren and the mahjarrat?", "freneskae");
        triviaData.put("on rs3 as of june 2024, what is the most common skillcape?", "constitution");
        triviaData.put("finish this quest title, desert treasure ii:", "the fallen empire");
        triviaData.put("in which area would you find the fortis colosseum", "varlamore");
        triviaData.put("what secondary do you need to create a prayer potion?", "snape grass");
        triviaData.put("name one of the available classes in runescape classic", "adventurer");
        triviaData.put("what is the name of the race that created elvarg and vorkath?", "dragonkin");
        triviaData.put("what skill was teased in osrs' 2014 april fools event?", "sailing");
        triviaData.put("where is evil bob from?", "scaperune");
        triviaData.put("what was the original name of runescape?", "deviousmud");
        triviaData.put("name the world where the main vampires come from", "vampyrium");
        triviaData.put("on osrs, what is the skill called in which you craft runes?", "runecraft");
        triviaData.put("on osrs as of june 2024, what is the least common skillcape?", "runecraft");
        triviaData.put("on osrs as of june 2024, what is the most common skillcape?", "strength");
        triviaData.put("name one of the bosses osrs took from rs2/rs3", "nex");
        triviaData.put("what is half of 99?", "92");
        triviaData.put("on osrs, who achieved blood torva first?", "synq");
        triviaData.put("how much granite dust do you require to make a full granite armour set?", "57");
        triviaData.put("who is the god-hero of the fremennik?", "v");
        triviaData.put("what is the name of the npc who runs the afk store?", "seashell seamus");
        triviaData.put("what are the official names of the two lizards wrapped around the minimap?", "atlas");
        triviaData.put("what was the stat that caused you to lose experience gains in rsc?", "fatigue");
        triviaData.put("who put an end to the god wars, banishing all major gods from gielinor with his edicts", "guthix");
        triviaData.put("what is the maximum quantity of an item a player can hold in their inventory?", "2147m");
        triviaData.put("what is the planet in which runescape is set?", "gielinor");
        triviaData.put("on rs3 as of june 2024, what is the least common skillcape?", "necromancy cape");
        triviaData.put("which god created the runic altars and is to blame for runecrafting?", "guthix");
        triviaData.put("name the world where elves come from", "tarddiad");
        triviaData.put("name the world where armadyl and the aviansies come from", "abbinah");
        triviaData.put("how many teeth do terrorbirds have?", "zero");
        triviaData.put("who is the owner of august?", "winter");
        triviaData.put("name one of the games in the burthorpe games room", "draughts");
        triviaData.put("name one of the bosses in the heart of gielinor (gwd2) on rs3", "gregorovic");
        triviaData.put("in runescape, what is the name of the kingdom that you can manage?", "miscellania");
        triviaData.put("name one of the eight mining companies in the dwarven consortium held in keldagrim", "purple pewter");
        triviaData.put("name one of the custom game modes in august", "pvmer");
        triviaData.put("what is the capital city of kandarin?", "ardougne");
        triviaData.put("finish this quest title, monkey madness ii:", "the renegade returns");
        triviaData.put("in runescape, which god is the god of cabbages", "brassica");
        triviaData.put("name one of the bosses rs3 took from osrs", "vorkath");
        triviaData.put("which barrows brother is in the middle hill?", "ahrim");
        triviaData.put("what was the first new piece of content added to osrs?", "nmz");
        triviaData.put("what secondary do you need to create a saradomin brew?", "crushed nest");
        triviaData.put("on rs3, what replaced the duel arena in january 2022?", "hets oasis");
        triviaData.put("what was hitpoints originally known as in rsc?", "hits");
        triviaData.put("prifddinas, the capital city of tirannwn, is a welsh word meaning what?", "capital city");
        triviaData.put("according to the lore, trolls get their names by the first thing that they...", "eat");
        triviaData.put("what is the maximum total level you can achieve in august (excluding skills that don't work)", "2400");
        triviaData.put("what is the name of the organization of secret spy penguins during the cold war quest?", "kgp");
        triviaData.put("according to the lore, who is skotizo's sister?", "zalcano");
        triviaData.put("how many legs does vorkath have?", "3");
        triviaData.put("name the world where all types of goblins come from", "yu'biusk");
        triviaData.put("the old school runescape servers are from a backup from what date?", "august 10 2007");
        triviaData.put("who was rank #1 on the high scores on august v1?", "stimulant");
        triviaData.put("what primary ingredient is required when crafting infinity?", "infinity cloth");
        triviaData.put("what secondary do you need to create an antifire potion?", "dragon scale dust");
        triviaData.put("name any one of the main bosses in august's custom olympian raids", "apollo");
        triviaData.put("what is the island on which vorkath is located?", "ungael");
        triviaData.put("name one of the elven clans that reside in prifddinas", "meilyr");
        triviaData.put("name any one of the knights of the round table in camelot", "king arthur");
        triviaData.put("name any of the bosses found inside of the tombs of amascut", "akkha");
        triviaData.put("who was the first person to complete the inferno on osrs?", "woox");
        triviaData.put("which npc holds both a 2h sword and a dragon square shield?", "vannaka");
        triviaData.put("the magic skill was initially divided in two, name one of the skills", "good magic");
        triviaData.put("according to the lore, how did the wyverns die out?", "climate change");
        triviaData.put("which elven clan chose to stay behind on their home world tarddiad?", "cywir");
        triviaData.put("how many thieving stalls are in the home area?", "18");
        triviaData.put("which god killed bandos at the beginning of the sixth age?", "armadyl");
        triviaData.put("what is considered a delicacy by dragons?", "runite");
        triviaData.put("in runescape, what was the first ever discontinued holiday item?", "pumpkin");
        triviaData.put("which god brought the elves to gielinor?", "guthix");
        triviaData.put("arceuus, the city in zeah is named after which pokémon?", "arceus");
        triviaData.put("name one of the bosses in the elder god wars dungeon (gwd3) on rs3", "kerapac");
        triviaData.put("what primary ingredient is required when crafting rangers gear?", "rangers fabric");
        triviaData.put("in what age did the god wars begin?", "third age");
        triviaData.put("when did runescape 3 release?", "july 22 2013");
        triviaData.put("name one of august's world bosses", "tarn razorlor");
        triviaData.put("the closest poll in osrs history passed by only 10 votes, what piece of content did it add?", "misthalin mystery");
        triviaData.put("if you used the senntisten teleport, where would you end up?", "digsite");
        triviaData.put("which halloween event, later added as a f2p quest, is loosely based on the movie scream?", "misthalin mystery");
        triviaData.put("when did runescape 2 release?", "march 29 2004");
        triviaData.put("when did the falador massacre take place?", "june 5th");
        triviaData.put("what secondary ingredient is required when making dragon items?", "luminite flux");
        triviaData.put("what is tekton's combat level?", "none");
        triviaData.put("how many inventory spaces do you have?", "28");
        triviaData.put("who is the slayer master who replaces nieve after her untimely demise?", "steve");
        triviaData.put("how many votes did the osrs release poll have rounded to the nearest ten thousand?", "450000");
        triviaData.put("unscramble this anagram 'nergilio'", "Gielinor");
        triviaData.put("unscramble this anagram 'pnuscreea'", "runescape");
        triviaData.put("the prayer skill was initially split into two in rsc, name one of the two options", "praygood");
        triviaData.put("according to the lore, where did the nightmare come from?", "ashihama");
        triviaData.put("finish this quest title, dragon slayer ii:", "the legacy of elvarg");
        triviaData.put("who is the slayer master that resides on anachronia on rs3?", "laniakea");
        triviaData.put("name one of the proposed skills that failed a poll in osrs", "artisan");
        triviaData.put("when did old school runescape release?", "february 22 2013");
        triviaData.put("who is the king of the monkeys, residing in ape atoll?", "awowogei");
        triviaData.put("on osrs, the first theatre of blood completion belongs to 5 players, name one of them", "b0aty");
        triviaData.put("on osrs, who completed the fortis colosseum first?", "port khazard");
        triviaData.put("what is the capital city of asgarnia?", "falador");
        triviaData.put("what kind of pie is thurgo's favorite?", "redberry");
        triviaData.put("what is the highest tier of gemstone armour currently available?", "dragonstone");
        triviaData.put("civitas illa fortis, the capital city of varlamore, is latin for what?", "the mighty city");
        triviaData.put("what is the capital city of morytania?", "darkmeyer");
        triviaData.put("who brought the humans from their home world to gielinor?", "guthix");
        triviaData.put("name the world where demons come from", "infernus");
    }

    public void cleanup() {
        triviaData.clear();
        triviaData = null;
    }

}
