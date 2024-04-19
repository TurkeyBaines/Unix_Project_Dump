package org.unix.script.manager;

import simple.api.coords.WorldArea;
import simple.api.coords.WorldPoint;

public class Vars {

    public static String SCRIPT_NAME;
    public static String AUTHOR;

    public static class Area {
        WorldPoint a;
        WorldPoint b;
        String n;

        Area(String Name, WorldPoint A, WorldPoint B) {
            n = Name;
            a = A;
            b = B;
        }

        public String getName() {
            return n;
        }
        public WorldPoint getA() {
            return a;
        }
        public WorldPoint getB() {
            return b;
        }

        public WorldArea getArea() { return new WorldArea(a, b);}
    }


}
