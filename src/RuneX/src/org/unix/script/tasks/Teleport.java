package org.unix.script.tasks;

import org.unix.Methods;

public class Teleport extends Task{

    String a, b;

    public Teleport(String A, String B) {
        a = A;
        b = B;
    }

    @Override
    public void run() {
        Methods.teleport(a, b);
    }

    @Override
    public String getUIOutput() {
        return "[TELE]" + a + ", " + b;
    }
}
