package org.unix.script.tasks;

import simple.api.queries.SimpleEntityQuery;
import simple.api.queries.SimpleItemQuery;
import simple.api.wrappers.SimpleGameObject;
import simple.api.wrappers.SimpleGroundItem;
import simple.api.wrappers.SimpleItem;
import simple.api.wrappers.SimpleSceneObject;

public class Interact_Item extends Task {

    int method; // 0 = PICKUP | 1 = DROP | 2 = CUSTOM | 3 = I2Obj | 4 = I2Itm

    String a;
    String b;

    public Interact_Item(int Method, String A, String B) {
        method = Method;
        a = A;
        b = B;
    }
    public Interact_Item(int Method, String A) {
        method = Method;
        a = A;
    }

    @Override
    public void run() {
        if (method == 0) {
            SimpleEntityQuery<SimpleGroundItem> seq = c.groundItems.populate().filter(a);
            if (seq.isEmpty()) {
                System.out.println("[INT_ITM] <PICK> " + a + " - Error: No Items Found");
                return;
            }
            SimpleGroundItem sgi = seq.nearest().next();
            sgi.interact("Pick Up");
            System.out.println("[INT_ITM] <PICK> " + a + " - Collecting Item...");
            c.sleep(1000);
        } else if (method == 1) {
            SimpleItemQuery<SimpleItem> seq = c.inventory.populate().filter(a);
            if (seq.isEmpty()) {
                System.out.println("[INT_ITM] <DROP> " + a + " - Error: No Items Found");
                return;
            }
            SimpleItem si = seq.next();
            si.interact("Drop");
            System.out.println("[INT_ITM] <PICK> " + a + " - Dropping Item...");
            c.sleep(1000);
        } else if (method == 2) {
            SimpleItemQuery<SimpleItem> seq = c.inventory.populate().filter(a);
            if (seq.isEmpty()) {
                System.out.println("[INT_ITM] <CUST> " + a + " - Error: No Items Found");
                return;
            }
            SimpleItem si = seq.next();
            si.interact(b);
            System.out.println("[INT_ITM] <CUST> " + a + " - Attempting action: " + b);
            c.sleep(1000);
        } else if (method == 3) {
            SimpleItemQuery<SimpleItem> seqA = c.inventory.populate().filter(a);
            SimpleItemQuery<SimpleItem> seqB = c.inventory.populate().filter(b);
            if (seqA.isEmpty() || seqB.isEmpty()) {
                System.out.println("[INT_ITM] <ITM> " + a + " - Error: No Items Found");
                return;
            }
            seqA.next().interact("Use");
            c.sleep(500);
            seqB.next().interact("Use-With");
            c.sleep(1000);
        } else if (method == 4) {
            SimpleItemQuery<SimpleItem> seqA = c.inventory.populate().filter(a);
            SimpleEntityQuery<SimpleSceneObject> seqB = c.objects.populate().filter(b);
            if (seqA.isEmpty() || seqB.isEmpty()) {
                System.out.println("[INT_ITM] <OBJ> " + a + " - Error: No Items Found");
                return;
            }
            seqA.next().interact("Use");
            c.sleep(500);
            seqB.next().interact("Use-With");
            c.sleep(1000);
        }
    }

    @Override
    public String getUIOutput() {

        if (method == 0) {
            return "[INT_ITM] <PICK> " + a;
        } else if (method == 1) {
            return "[INT_ITM] <DROP> " + a;
        } else if (method == 2) {
            return "[INT_ITM] <CUST> " + a + ", " + b;
        } else if (method == 3) {
            return "[INT_ITM] <ITM> " + a + ", " + b;
        } else if (method == 4) {
            return "[INT_ITM] <OBJ> " + a + ", " + b;
        }

        return "";
    }
}
