package model;

public enum Colors {
    RED("#c1272d","Red",'*'), ORANGE("#f15a24", "Orange",'!'), CYAN("#29abe2", "Cyan",'O'),
    DARK_BLUE("#00008e", "Dark blue",'X'), YELLOW("#fbd850", "Yellow",'%'),
    GREEN("#39b54a","Green",'$'), PINK("#dd3358","Pink",'#'), PURPLE("#93278f","Purple",'+'),
    LIME("#c9e636","Lime",'&');

    String hexValue;
    String name;
    char symbol;

    Colors(String hexValue, String name, char symbol) {
        this.hexValue = hexValue;
        this.name = name;
        this.symbol = symbol;
    }

    public static String getHexValue(String name) {
        switch (name) {
            case "Red":
                return RED.hexValue;
            case "Orange":
                return ORANGE.hexValue;
            case "Cyan":
                return CYAN.hexValue;
            case "Dark blue":
                return DARK_BLUE.hexValue;
            case "Yellow":
                return YELLOW.hexValue;
            case "Green":
                return GREEN.hexValue;
            case "Pink":
                return PINK.hexValue;
            case "Purple":
                return PURPLE.hexValue;
            case "Lime":
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

    public static String getHexWithChar(char winner) {
        switch (winner) {
            case '*':
                return RED.hexValue;
            case '!':
                return ORANGE.hexValue;
            case 'O':
                return CYAN.hexValue;
            case 'X':
                return DARK_BLUE.hexValue;
            case '%':
                return YELLOW.hexValue;
            case '$':
                return GREEN.hexValue;
            case '#':
                return PINK.hexValue;
            case '+':
                return PURPLE.hexValue;
            case '&':
                return LIME.hexValue;
            default:
                return "#00000000";
        }
    }
}
