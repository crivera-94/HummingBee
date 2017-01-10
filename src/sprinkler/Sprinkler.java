package sprinkler;

import location.Area;

public class Sprinkler {
    // used to make unique ids
    private static int northCount = 0;
    private static int eastCount = 0;
    private static int southCount = 0;
    private static int westCount = 0;

    private String id;
    private int wateringRate;
    private Area location;

    public Sprinkler(Area location, int rate) {
        this.location = location;
        this.wateringRate = rate;
        switch (location) {
            case north: id = Integer.toString(northCount++) + "N";
            case east: id = Integer.toString(eastCount++) + "E";
            case south: id = Integer.toString(southCount++) + "S";
            case west: id = Integer.toString(westCount++) + "W";
        }
    }

    public String getId() { return this.id; }

    public int getWateringRate() {
        return wateringRate;
    }

}
