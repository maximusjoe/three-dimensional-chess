
public enum Valid {
    UP("UP", 0),
    DOWN("DOWN", 1),
    LEFT("LEFT", 2),
    RIGHT("RIGHT", 3),
    DIAGRIGHTUP("DIAGRIGHTUP", 4),
    DIAGLEFTUP("DIAGLEFTUP", 5),
    DIAGRIGHTDOWN("DIAGRIGHTDOWN", 6),
    DIAGLEFTDOWN("DIAGLEFTDOWN", 7),
    INVALID("INVALID", 8);

    private Valid(final String name, final int ordinal) {
    }
}
