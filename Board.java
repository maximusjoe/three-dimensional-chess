import javax.swing.JPanel;

public abstract class Board extends JPanel {
    protected Board curBoard;

    public Board getCurBoard() {
        return this.curBoard;
    }

    public void setBoard(final Board b) {
        this.curBoard = b;
    }

    public abstract void init(final Listen p0);

    public abstract Square getSquareAt(final int p0, final int p1);

    public abstract int getNumberOfRows();

    public abstract boolean isVertPathEmpty(final Square p0, final Square p1);

    public abstract boolean isHorzPathEmpty(final Square p0, final Square p1);

    public abstract boolean isLeftDiagPathEmpty(final Square p0, final Square p1);

    public abstract boolean isLeftDownDiagPathEmpty(final Square p0, final Square p1);

    public abstract boolean isRightDiagPathEmpty(final Square p0, final Square p1);

    public abstract boolean isRightDownDiagPathEmpty(final Square p0, final Square p1);

    public abstract Valid moveDirection(final Square p0, final Square p1);

    public abstract int getNumberOfCols(final int p0);
}
