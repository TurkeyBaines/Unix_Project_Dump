package org.unix.script.tasks;

public class Sleep extends Task {

    int sleep;

    public Sleep(int Time) {
        sleep = Time;
    }

    @Override
    public void run() {
        c.sleep(sleep);
    }

    @Override
    public String getUIOutput() {
        return "[SLEEP] " + sleep;
    }
}
