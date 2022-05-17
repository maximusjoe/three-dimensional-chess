
public abstract class Piece {
    private final String image;
    protected final Player owner;
    protected Square square;

    protected Piece(final Player p, final String img) {
        this.owner = p;
        this.image = img;
    }

    public String getImage() {
        return this.image;
    }

    protected boolean isAvailable(final Square toSquare) {
        return toSquare.getPiece() == null && toSquare.getPiece() != this;
    }

    protected int horizontaldistance(final Square toSquare) {
        final Square start = this.getSquare();
        final int xStart = start.getCol();
        final int xEnd = toSquare.getCol();
        return Math.abs(xStart - xEnd);
    }

    protected int verticaldistance(final Square toSquare) {
        final Square start = this.getSquare();
        final int yStart = start.getRow();
        final int yEnd = toSquare.getRow();
        return Math.abs(yStart - yEnd);
    }

    protected boolean isVerticalPath(final Square toSquare) {
        final Square start = this.getSquare();
        return start.getCol() - toSquare.getCol() == 0;
    }

    protected boolean isHorizontalPath(final Square toSquare) {
        final Square start = this.getSquare();
        return start.getRow() - toSquare.getRow() == 0;
    }

    protected boolean isDiagonalPath(final Square toSquare) {
        final Square start = this.getSquare();
        return Math.abs(start.getRow() - toSquare.getRow()) == Math.abs(start.getCol() - toSquare.getCol());
    }

    protected boolean isVerticalPathClear(final Square toSquare) {
        if (!this.isVerticalPath(toSquare)) {
            return false;
        }
        Square start = this.getSquare();
        int sy = start.getRow();
        final int ey = toSquare.getRow();
        if (sy < ey) {
            while (sy != ey) {
                start = start.belowNeighbor();
                sy = start.getCol();
                if (start.getPiece() != null) {
                    return false;
                }
            }
        } else {
            while (sy != ey) {
                start = start.aboveNeighbor();
                sy = start.getCol();
                if (start.getPiece() != null) {
                    return false;
                }
            }
        }
        return true;
    }

    protected boolean isHorizontalPathClear(final Square toSquare) {
        if (!this.isHorizontalPath(toSquare)) {
            return false;
        }
        Square start = this.getSquare();
        int sy = start.getRow();
        final int ey = toSquare.getRow();
        if (sy < ey) {
            while (sy != ey) {
                start = start.rightNeighbor();
                sy = start.getRow();
                if (start.getPiece() != null) {
                    return false;
                }
            }
        } else {
            while (sy != ey) {
                start = start.leftNeighbor();
                sy = start.getRow();
                if (start.getPiece() != null) {
                    return false;
                }
            }
        }
        return true;
    }

    public Player getOwner() {
        return this.owner;
    }

    public void setSquare(final Square s) {
        this.square = s;
    }

    public Square getSquare() {
        return this.square;
    }

    public abstract boolean isValidMove(final Square p0, final Board p1);
}
