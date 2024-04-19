package org.unix.shit;

import org.unix.Database;
import simple.hooks.filters.SimpleShop;
import simple.hooks.scripts.Category;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.simplebot.ChatMessage;
import simple.hooks.wrappers.SimpleWidget;
import simple.robot.script.Script;

import java.awt.*;

@ScriptManifest(discord = "", version = "1", servers = {"Zenyte"}, name = "Buy Runes", category = Category.OTHER, author = "unix", description = "part of the shit collection..")
public class BuyRunes extends Script {
    @Override
    public void onExecute() {
    }

    @Override
    public void onChatMessage(ChatMessage chatMessage) {

    }

    @Override
    public void onProcess() {
        if (ctx.widgets.getWidget(300, 16) != null) {
            ctx.shop.buy(Database.runes.DEATH_RUNE, SimpleShop.Amount.FIFTY);
        }
    }

    @Override
    public void onTerminate() {

    }

    @Override
    public void paint(Graphics graphics) {

    }
}
