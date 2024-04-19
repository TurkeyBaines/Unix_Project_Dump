package org.unix.packetSender.events;

import org.unix.packetSender.events.item.Bury;
import org.unix.packetSender.events.item.Clean;
import org.unix.packetSender.events.item.Usewith;
import org.unix.packetSender.events.spell.*;

import java.util.HashMap;

public class EventHandler {

    public EventHandler() {
    }

    public void pushEvent(int a, int b, String c) {
        switch (a) {
            case 0:
                switch (b) {
                    case 0: new LowAlchamy().run(c); break; // LOW ALCH
                    case 1: new HighAlchamy().run(c); break; // HIGH ALCH
                    case 2: new Vengeance().run(c); break; // VENG
                    case 3: new IceBarrage().run(c); break; // ICE BARRAGE
                    case 4: new BloodBarrage().run(c); break; // BLOOD BARRAGE
                    case 5: new Superheat().run(c); break; // SUPERHEAT
                }
                break; // MAGIC
            case 1:
                switch (b) {
                    case 0: new Bury().run(c); break; //BURY
                    case 1: new Clean().run(c); break; // CLEAN
                    case 2: new Usewith().run(c); break; // USE-WITH
                }
                break; // FOOD
            case 2: break; // ITEM
        }
    }

}
