package org.unix.packetSender;

import org.unix.packetSender.events.EventHandler;
import org.unix.packetSender.gui.GUI;
import simple.api.script.Category;
import simple.api.script.Script;
import simple.api.script.ScriptManifest;

import javax.swing.*;

@ScriptManifest(servers = {"RuneX"}, discord = "", description = "Sends some useful packets, be careful though! Some of the features are extremely incriminating!", category = Category.OTHER, name = "uCheater", author = "unix", version = "1")
public class uCheater extends Script {

    EventHandler eh;
    GUI gui;

    @Override
    public boolean onExecute() {
        eh = new EventHandler();
        gui = new GUI(this);
        gui.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        gui.setVisible(true);
        return true;
    }

    @Override
    public void onTerminate() {
        gui.dispose();
    }

    @Override
    public void onProcess() {

    }

    public void sendAction(int master, int sub, String box) {
        eh.pushEvent(master, sub, box);
    }
}
