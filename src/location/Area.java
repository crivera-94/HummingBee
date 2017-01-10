package location;

public enum Area {
    north,
    east,
    south,
    west;

    public static Area stringToArea(String area){
        switch (area){
            case "north": return north;
            case "south": return south;
            case "east": return east;
            case "west": return west;
            default: return null;
        }
    }
}
