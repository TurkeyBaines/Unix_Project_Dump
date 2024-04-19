package org.unix.script.tasks;

import org.unix.script.manager.ScriptManager;
import simple.api.ClientContext;
import simple.api.coords.WorldArea;
import simple.api.coords.WorldPoint;
import simple.api.script.Script;

public class Condition {

    public static class Location extends Task {

        WorldPoint loc;
        boolean repeat;

        public Location(WorldPoint Location, boolean RepeatLastAction) {
            loc = Location;
            repeat = RepeatLastAction;
        }

        @Override
        public void run() {
            c.sleepCondition(() -> c.players.getLocal().getLocation() == loc, 2500);

            if (repeat && idle())
                ScriptManager.repeatPreviousTask();
            else
                ScriptManager.forceNextTask();

        }

        @Override
        public String getUIOutput() {
            return "[COND] <Loc> " + loc;
        }
    }

    public static class Area extends Task {

        WorldArea area;
        boolean repeat, insideArea;

        public Area(WorldArea Area, boolean InsideArea, boolean RepeatLastAction) {
            area = Area;
            insideArea = InsideArea;
            repeat = RepeatLastAction;
        }

        @Override
        public void run() {
            if (insideArea) {
                c.sleepCondition(() -> area.containsPoint(c.players.getLocal().getLocation()), 2500);
            } else {
                c.sleepCondition(() -> !area.containsPoint(c.players.getLocal().getLocation()), 2500);
            }

            if (repeat && idle())
                ScriptManager.repeatPreviousTask();
            else
                ScriptManager.forceNextTask();

        }

        @Override
        public String getUIOutput() {
            return "[COND] <Area> " + area;
        }
    }

    public static class Inventory extends Task {

        int method;
        String itemName;
        int itemQuantity;
        boolean repeat;

        public Inventory(int Method, boolean RepeatLastTask) {
            method = Method;
            repeat = RepeatLastTask;
        }

        public Inventory(String ItemName, boolean RepeatLastTask) {
            method = 2;
            itemName = ItemName;
            repeat = RepeatLastTask;
        }

        public Inventory(String ItemName, int ItemQuantity, boolean RepeatLastTask) {
            method = 3;
            itemName = ItemName;
            itemQuantity = ItemQuantity;
            repeat = RepeatLastTask;
        }

        @Override
        public void run() {
            if (method == 0) {
                c.sleepCondition(() -> c.inventory.inventoryFull());
                if (repeat && idle()) {
                    ScriptManager.repeatPreviousTask();
                } else {
                    ScriptManager.forceNextTask();
                }
                return;
            }

            if (method == 1) {
                c.sleepCondition(() -> c.inventory.isEmpty());
                if (repeat && idle()) {
                    ScriptManager.repeatPreviousTask();
                } else {
                    ScriptManager.forceNextTask();
                }
                return;
            }

            if (method == 2) {
                c.sleepCondition(() -> c.inventory.populate().filter(itemName).isEmpty());
                if (repeat && idle()) {
                    ScriptManager.repeatPreviousTask();
                } else {
                    ScriptManager.forceNextTask();
                }
                return;
            }

            if (method == 3) {
                c.sleepCondition(() -> (!c.inventory.populate().filter(itemName).isEmpty() && c.inventory.populate().size() >= itemQuantity));
                if (repeat && idle()) {
                    ScriptManager.repeatPreviousTask();
                } else {
                    ScriptManager.forceNextTask();
                }
                return;
            }
        }

        @Override
        public String getUIOutput() {
            if (method == 0) {
                return "[COND] <Inv> Full";
            } else if (method == 1) {
                return "[COND] <Inv> Empty";
            } else if (method == 2) {
                return "[COND] <Inv> Item Missing: " + itemName;
            } else {
                return "[COND] <Inv> Item Quantity: " + itemQuantity + " x " + itemName;
            }
        }
    }

    public static class Interface extends Task {

        int primary;
        boolean open, repeat;

        public Interface(int Main, boolean Open, boolean RepeatLastAction) {
            primary = Main;
            open = Open;
            repeat = RepeatLastAction;
        }

        @Override
        public void run() {
            if (open) {
                c.sleepCondition(() -> c.widgets.getOpenInterfaceId() == primary);
                if (repeat) {
                    ScriptManager.repeatPreviousTask();
                } else {
                    ScriptManager.forceNextTask();
                }
            } else if (!open) {
                c.sleepCondition(() -> c.widgets.getOpenInterfaceId() != primary);
                if (repeat && idle()) {
                    ScriptManager.repeatPreviousTask();
                } else {
                    ScriptManager.forceNextTask();
                }

            }
        }

        @Override
        public String getUIOutput() {
            if (open) {
                return "[COND] <Wid> is Open | Repeat: " + repeat;
            } else {
                return "[COND] <Wid> is Closed | Repeat: " + repeat;
            }
        }
    }

    public static class Animation extends Task {

        int anim;
        boolean repeat;

        public Animation(int Anim, Boolean RepeatLast) {
            anim = Anim;
            repeat = RepeatLast;
        }

        @Override
        public void run() {
            if (c.players.getLocal().getAnimation() == anim) {
                ScriptManager.forceNextTask();
            } else {
                if (repeat && idle()) {
                    ScriptManager.repeatPreviousTask();
                }
            }
        }

        @Override
        public String getUIOutput() {
            return "[COND] <Anim> " + anim;
        }
    }

    public static class Combat extends Task {

        int method;
        boolean repeat;

        public Combat(int Method, boolean RepeatLast) {
            method = Method;
            repeat = RepeatLast;
        }

        @Override
        public void run() {
            if (method == 0) {
                if (c.players.getLocal().inCombat()) {
                    ScriptManager.forceNextTask();
                } else {
                    if (repeat && idle()) {
                        ScriptManager.repeatPreviousTask();
                    }
                }
            } else if (method == 1) {
                if (c.combat.healthPercent() <= 30) {
                    ScriptManager.forceNextTask();
                } else {
                    if (repeat && idle()) {
                        ScriptManager.repeatPreviousTask();
                    }
                }
            } else if (method == 2) {
                if (c.prayers.prayerPercent() <= 30) {
                    ScriptManager.forceNextTask();
                } else {
                    if (repeat && idle()) {
                        ScriptManager.repeatPreviousTask();
                    }
                }
            } else if (method == 3) {
                if (!c.players.getLocal().inCombat()) {
                    ScriptManager.forceNextTask();
                } else {
                    if (repeat && idle()) {
                        ScriptManager.repeatPreviousTask();
                    }
                }
            }
        }

        @Override
        public String getUIOutput() {
            if (method == 0)
                return "[COND] <Cmb> In Combat";
            if (method == 1)
                return "[COND] <Cmb> Low HP (30%)";
            if (method == 2)
                return "[COND] <Cmb> Low Prayer (30%)";
            if (method == 3)
                return "[COND] <Cmb> Out of Combat";
            return "";
        }
    }

    public static class ChatMessage extends Task {

        String chatMessage;
        boolean repeat;

        public ChatMessage(String Message, boolean RepeatLast) {
            chatMessage = Message;
            repeat = RepeatLast;
        }

        @Override
        public void run() {
            if (ScriptManager.LastChatMessageRecieved.toLowerCase().contains(chatMessage.toLowerCase())) {
                ScriptManager.LastChatMessageRecieved = "";
                ScriptManager.forceNextTask();
            } else {
                if (repeat && idle()) {
                    ScriptManager.repeatPreviousTask();
                }
            }
        }

        @Override
        public String getUIOutput() {
            return "[COND] <Chat> " + chatMessage;
        }
    }


    private static boolean idle() {

        if (ClientContext.instance().players.getLocal().getAnimation() == -1) {
            return true;
        }
        return false;
    }

}
