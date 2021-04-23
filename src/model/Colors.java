package model;

public enum Colors {
    RED("c1272d"), ORANGE("#f15a24"), CYAN("#29abe2"), DARK_BLUE("#00008e"),
    YELLOW("#fbd850"), GREEN("#39b54a"), PINK("#dd3358"), PURPLE("#93278f"),
    LIME("#c9e636"), BLACK("#232323"), WHITE("#dfdfdf");

    String hexValue;

    Colors(String hexValue) {
        this.hexValue = hexValue;
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
            case 9:
                return BLACK.hexValue;
            case 10:
                return WHITE.hexValue;
            default:
                return "empty";
        }
    }
}
