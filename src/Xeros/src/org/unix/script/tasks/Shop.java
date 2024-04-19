package org.unix.script.tasks;

public class Shop extends Task {

    int type, id, slot, quantity;
    String name;

    public Shop(String Name, int Quantity) {
        type = 0; // Sell
        name = Name;
        quantity = Quantity;
    }
    public Shop(String Name, int Slot, int Quantity) {
        type = 1; // Buy
        name = Name;
        quantity = Quantity;
    }
    public Shop() {
        type = 2; // Close
    }

    @Override
    public void run() {
        if (type == 0) {
            c.shop.sell(name, quantity);
            c.sleep(500);
        } else if (type == 1) {
            c.shop.buy(name, quantity);
            c.sleep(500);
        } else {
            c.shop.closeShop();
        }
    }

    @Override
    public String getUIOutput() {
        if (type == 0) {
            return "[SHOP] <Sell> " + name + ", " + quantity;
        } else if (type == 1) {
            return "[SHOP] <Buy> " + name + ", " + quantity;
        } else if (type == 2) {
            return "[SHOP] <Close>";
        }
        return "";
    }
}
