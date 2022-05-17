
public class Move3D implements Move {
    Board b;

    public Move3D(final Board b) {
        this.b = b;
    }

    @Override
    public boolean isValidPattern(final Pattern p) {
        return false;
    }

    @Override
    public boolean isPathClear(final Square start, final Square end, final Pattern p) {
        return false;
    }
}
