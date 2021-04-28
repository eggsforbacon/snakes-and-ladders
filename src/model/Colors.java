package model;

public enum Colors {
    RED("c1272d","Red"), ORANGE("#f15a24", "Orange"), CYAN("#29abe2", "Cyan"),
    DARK_BLUE("#00008e", "Dark blue"), YELLOW("#fbd850", "Yellow"),
    GREEN("#39b54a","Green"), PINK("#dd3358","Pink"), PURPLE("#93278f","Purple"),
    LIME("#c9e636","Lime");

    String hexValue;
    String name;

    Colors(String hexValue, String name) {
        this.hexValue = hexValue;
        this.name = name;
    }

    public static String getHexValue(int index) {
        switch (index) {
            case 0:
                return RED.hexValue;
            case 1:
                return ORANGE.hexValue;
            case 2:
                return CYAN.hexValue;
            case 3:
                return DARK_BLUE.hexValue;
            case 4:
                return YELLOW.hexValue;
            case 5:
                return GREEN.hexValue;
            case 6:
                return PINK.hexValue;
            case 7:
                return PURPLE.hexValue;
            case 8:
                return LIME.hexValue;
            default:
                return "empty";
        }
    }

    public static String getName(int index) {
        switch (index) {
            case 0:
                return RED.name;
            case 1:
                return ORANGE.name;
            case 2:
                return CYAN.name;
            case 3:
                return DARK_BLUE.name;
            case 4:
                return YELLOW.name;
            case 5:
                return GREEN.name;
            case 6:
                return PINK.name;
            case 7:
                return PURPLE.name;
            case 8:
                return LIME.name;
            default:
                return "empty";
        }
    }
}
