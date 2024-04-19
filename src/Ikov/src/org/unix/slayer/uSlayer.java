package org.unix.slayer;

import org.unix.slayer.paint.Paint;
import org.unix.slayer.setup.SetupManager;
import org.unix.slayer.tasks.TaskHandler;
import simple.api.events.ChatMessageEvent;
import simple.api.listeners.SimpleMessageListener;
import simple.api.script.Category;
import simple.api.script.Script;
import simple.api.script.ScriptManifest;
import simple.api.script.interfaces.SimplePaintable;

import java.awt.*;

@ScriptManifest(servers = {"RuneX"}, discord = "", description = "Slayer script for RuneX, Major rewrite v2", category = Category.SLAYER, name = "uSlayer v2", author = "unix", version = "2A")
public class uSlayer extends Script implements SimplePaintable, SimpleMessageListener {

    public TaskHandler taskHandler;

    public static boolean setupComplete = false;
    public boolean setupLaunched = false;
    public static boolean STOP = false;
    public Paint paintHandler;

    @Override
    public void onProcess() {
        if (STOP) { ctx.stopScript(); }
        taskHandler.runtime();
    }

    @Override
    public boolean onExecute() {
        Constants.USER_DIR = this.getStorageDirectory().getAbsolutePath();
        paintHandler = new Paint(this);
        if (!setupLaunched) {
            taskHandler = new TaskHandler();
            new SetupManager(ctx, this);
            setupLaunched = true;
        }
        return setupComplete;
    }

    @Override
    public void onTerminate() {

    }

    @Override
    public void onPaint(Graphics2D graphics2D) {
        paintHandler.onDraw(graphics2D);
    }

    @Override
    public void onChatMessage(ChatMessageEvent chatMessageEvent) {
        taskHandler.handleChatEvent(chatMessageEvent);
    }
}
