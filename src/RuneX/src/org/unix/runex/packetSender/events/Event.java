package org.unix.packetSender.events;

import simple.api.ClientContext;

public abstract class Event {
    public ClientContext c = ClientContext.instance();
    public abstract void run(String info);



    public void execute(String info) {
        run(info);
        System.out.println(this.getClass().getName() + " | Executed!");
    }

}
