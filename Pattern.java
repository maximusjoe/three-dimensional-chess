
public class Pattern {
    private int x;
    private int y;
    private int z;

    public boolean isEqual(final Object o) {
        final Pattern p = (Pattern) o;
        return this.x == p.x && this.y == p.y && this.z == p.z;
    }
}
