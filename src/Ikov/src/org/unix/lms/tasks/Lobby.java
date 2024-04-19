package org.unix.lms.tasks;

import org.unix.lms.Constants;
import simple.api.wrappers.SimpleNpc;

public class Lobby extends Task {
    @Override
    public boolean execute() {
        System.out.println("A: " + Constants.Lobby.GENERAL_AREA.containsPoint(loc) + " | B: " + Constants.Lobby.LOBBY.containsPoint(loc));
        return Constants.Lobby.GENERAL_AREA.containsPoint(loc) || Constants.Lobby.LOBBY.containsPoint(loc);
    }

    @Override
    public void run() {
        if (Constants.Lobby.GENERAL_AREA.containsPoint(loc)) {
            if (c.dialogue.canContinue()) {
                c.dialogue.clickContinue();
                c.sleep(250);
                return;
            }
            // I SHOULD CHECK MY PRAYERS AND SHIT HERE
            SimpleNpc lisa = c.npcs.populate().filter(Constants.Lobby.LISA).nearest().next();
            if (lisa == null)
                return;
            lisa.interact(225);
            c.sleep(2500);
        } else if (Constants.Lobby.LOBBY.containsPoint(loc)) {
            if (c.widgets.populate().filter(50358).next().getText().equals("Game In Progress")) {
                System.out.println("We waiting for the next game!");
            } else if (c.widgets.populate().filter(50358).next().getText().equals("Game In Progress")) {
                System.out.println("Next Game: Casual");
                System.out.println(c.widgets.populate().filter(50359).next().getText());
                System.out.println(c.widgets.populate().filter(50360).next().getText());
            }
        }
    }
}
