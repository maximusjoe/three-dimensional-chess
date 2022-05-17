
public interface Move {
    boolean isValidPattern(final Pattern p0);

    boolean isPathClear(final Square p0, final Square p1, final Pattern p2);
}
