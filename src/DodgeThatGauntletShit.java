package com.test;

import net.runelite.api.coords.WorldPoint;
import org.data.Methods;
import simple.hooks.scripts.Category;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.simplebot.ChatMessage;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.api.ClientContext;
import simple.robot.script.Script;
import simple.robot.utils.WorldArea;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

@ScriptManifest(servers = {"OldschoolRSPS"}, discord = "", version = "1", name = "DodgeThatGauntletShit", author = "unix", category = Category.FAVORITE, description = "")
public class DodgeThatGauntletShit extends Script {
    ClientContext c = ClientContext.instance();

    WorldArea combatArea;
    ArrayList<WorldPoint> availableTiles;

    @Override
    public void onChatMessage(ChatMessage chatMessage) {

    }

    @Override
    public void onExecute() {
        combatArea = new WorldArea
                (
                        WorldPoint.fromScene(c.getClient(), 50, 58, 1),
                        WorldPoint.fromScene(c.getClient(), 62, 70, 1)
                );
        availableTiles = new ArrayList<>();
        availableTiles.addAll(Arrays.asList(combatArea.getWorldPoints()));
    }

    @Override
    public void onProcess() {
        if (!c.objects.populate().filter(36150).isEmpty()) {
            for (SimpleObject badTile : c.objects.populate().filter(36150)) {
                if (availableTiles.contains(badTile.getLocation())) {
                    availableTiles.remove(badTile.getLocation());
                }
            }

            if (!availableTiles.contains(c.players.getLocal().getLocation())) {
                Methods.walk(availableTiles.get(new Random().nextInt(availableTiles.size()-1)));
            }
        } else {
            availableTiles.removeAll(availableTiles);
            availableTiles.addAll(Arrays.asList(combatArea.getWorldPoints()));
        }
    }

    @Override
    public void onTerminate() {

    }

    @Override
    public void paint(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;

        if (combatArea != null) {
            for (WorldPoint wp : combatArea.getWorldPoints()) {
                c.paint.drawTileMatrix(g2d, wp, Color.RED);
            }
        }
    }
}
