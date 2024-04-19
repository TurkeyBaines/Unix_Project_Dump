package org.unix.script.tasks;

import org.unix.script.manager.ScriptManager;
import simple.api.coords.WorldArea;
import simple.api.coords.WorldPoint;

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

            if (repeat)
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

            if (repeat)
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
                if (repeat) {
                    ScriptManager.repeatPreviousTask();
                } else {
                    ScriptManager.forceNextTask();
                }
                return;
            }

            if (method == 1) {
                c.sleepCondition(() -> c.inventory.isEmpty());
                if (repeat) {
                    ScriptManager.repeatPreviousTask();
                } else {
                    ScriptManager.forceNextTask();
                }
                return;
            }

            if (method == 2) {
                c.sleepCondition(() -> c.inventory.populate().filter(itemName).isEmpty());
                if (repeat) {
                    ScriptManager.repeatPreviousTask();
                } else {
                    ScriptManager.forceNextTask();
                }
                return;
            }

            if (method == 3) {
                c.sleepCondition(() -> (!c.inventory.populate().filter(itemName).isEmpty() && c.inventory.populate().size() >= itemQuantity));
                if (repeat) {
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

        int primary, chatbox;
        boolean open, repeat;

        public Interface(int Main, int Chat, boolean Open, boolean RepeatLastAction) {
            primary = Main;
            chatbox = Chat;
            open = Open;
        }

        @Override
        public void run() {
            if (open) {
                if (primary != -1) {
                    c.sleepCondition(() -> c.widgets.getOpenInterfaceId() == primary);
                    if (repeat) {
                        ScriptManager.repeatPreviousTask();
                    } else {
                        ScriptManager.forceNextTask();
                    }
                } else {
                    c.sleepCondition(() -> c.widgets.getBackDialogId() == chatbox);
                    if (repeat) {
                        ScriptManager.repeatPreviousTask();
                    } else {
                        ScriptManager.forceNextTask();
                    }
                }
            } else if (!open) {
                if (primary != -1) {
                    c.sleepCondition(() -> c.widgets.getOpenInterfaceId() != primary);
                    if (repeat) {
                        ScriptManager.repeatPreviousTask();
                    } else {
                        ScriptManager.forceNextTask();
                    }
                } else {
                    c.sleepCondition(() -> c.widgets.getBackDialogId() != chatbox);
                    if (repeat) {
                        ScriptManager.repeatPreviousTask();
                    } else {
                        ScriptManager.forceNextTask();
                    }
                }
            }
        }

        @Override
        public String getUIOutput() {
            return null;
        }
    }

    public class Animation extends Task {
        @Override
        public void run() {

        }

        @Override
        public String getUIOutput() {
            return null;
        }
    }

    public class Combat extends Task {
        @Override
        public void run() {

        }

        @Override
        public String getUIOutput() {
            return null;
        }
    }

    public class ChatMessage extends Task {
        @Override
        public void run() {

        }

        @Override
        public String getUIOutput() {
            return null;
        }
    }

}
