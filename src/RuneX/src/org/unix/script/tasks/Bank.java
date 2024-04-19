package org.unix.script.tasks;

import org.unix.Methods;

public class Bank extends Task{

    int method;

    int preset;

    public Bank(int Method) {
        method = Method;
    }

    public Bank(int Method, int Preset) {
        method = Method;
        preset = Preset;
    }

    @Override
    public void run() {
        if (method == 0) {
            c.bank.openBank();
            c.sleep(500);
        } else if (method == 1) {
            c.bank.closeBank();
            c.sleep(500);
        } else if (method == 2) {
            c.bank.depositInventory();
            c.sleep(500);
        } else if (method == 3) {
            Methods.grabBankPreset(preset);
            c.sleep(500);
        }
    }

    @Override
    public String getUIOutput() {
        if (method == 0) {
            return "[BANK] Open";
        } else if (method == 1) {
            return "[BANK] Close";
        } else if (method == 2) {
            return "[BANK] Inventory";
        } else if (method == 3) {
            return "[BANK] <PRE> " + preset;
        }
        return "";
    }
}
