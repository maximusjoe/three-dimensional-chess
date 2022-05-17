import java.awt.Color;
import java.awt.GridLayout;

public class CBoard extends Board {
    private final Square[][] squares;

    Color black = new Color(184, 140, 100);
    Color white = new Color(248, 220, 160);

    public CBoard(final int rows, final int cols) {
        this.squares = new Square[rows][cols];
        this.curBoard = this;
    }

    @Override
    public void init(final Listen listener) {
        this.setLayout(new GridLayout(this.squares.length, this.squares[0].length));
        for (int row = 0; row < this.squares.length; ++row) {
            for (int col = 0; col < this.squares[row].length; ++col) {
                if ((row + col) % 2 == 0) {
                    this.squares[col][row] = new Square(row, col, black, this);
                } else {
                    this.squares[col][row] = new Square(row, col, white, this);
                }
                this.add(this.squares[col][row]);
                this.squares[col][row].addMouseListener(listener);
            }
        }
    }

    @Override
    public Square getSquareAt(final int row, final int col) {
        return this.squares[col][row];
    }

    @Override
    public int getNumberOfRows() {
        return this.squares.length;
    }

    @Override
    public boolean isVertPathEmpty(final Square start, final Square end) {
        boolean result = true;
        final int startRow = start.getRow();
        final int endRow = end.getRow();
        Square cur = start;
        Square finish = end;
        if (startRow < endRow) {
            cur = end;
            finish = start;
        }
        while (cur != finish) {
            cur = cur.aboveNeighbor();
            final Piece p = cur.getPiece();
            if (p != null && cur != finish) {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean isHorzPathEmpty(final Square start, final Square end) {
        boolean result = true;
        final int startCol = start.getCol();
        final int endCol = end.getCol();
        Square cur = start;
        Square finish = end;
        if (startCol < endCol) {
            cur = end;
            finish = start;
        }
        while (cur != finish) {
            cur = cur.leftNeighbor();
            final Piece p = cur.getPiece();
            if (p != null && cur != finish) {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean isLeftDiagPathEmpty(final Square start, final Square end) {
        boolean result = true;
        final int startCol = start.getCol();
        final int endCol = end.getCol();
        Square cur = start;
        Square finish = end;
        if (startCol < endCol) {
            cur = end;
            finish = start;
        }
        while (cur != finish) {
            cur = cur.leftNeighbor();
            cur = cur.aboveNeighbor();
            final Piece p = cur.getPiece();
            if (p != null && cur != finish) {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean isLeftDownDiagPathEmpty(final Square start, final Square end) {
        boolean result = true;
        final int startCol = start.getCol();
        final int endCol = end.getCol();
        Square cur = start;
        Square finish = end;
        if (startCol < endCol) {
            cur = end;
            finish = start;
        }
        while (cur != finish) {
            cur = cur.leftNeighbor();
            cur = cur.belowNeighbor();
            final Piece p = cur.getPiece();
            if (p != null && cur != finish) {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean isRightDiagPathEmpty(final Square start, final Square end) {
        boolean result = true;
        final int startCol = start.getCol();
        final int endCol = end.getCol();
        Square cur = start;
        Square finish = end;
        if (startCol > endCol) {
            cur = end;
            finish = start;
        }
        while (cur != finish) {
            cur = cur.rightNeighbor();
            cur = cur.aboveNeighbor();
            final Piece p = cur.getPiece();
            if (p != null && cur != finish) {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean isRightDownDiagPathEmpty(final Square start, final Square end) {
        boolean result = true;
        final int startCol = start.getCol();
        final int endCol = end.getCol();
        Square cur = start;
        final Square finish = end;
        if (startCol > endCol) {
            this.isRightUpDiagPathEmpty(start, end);
        } else {
            while (cur != finish) {
                cur = cur.rightNeighbor();
                cur = cur.belowNeighbor();
                final Piece p = cur.getPiece();
                if (p != null && cur != finish) {
                    result = false;
                }
            }
        }
        return result;
    }

    private boolean isRightUpDiagPathEmpty(final Square start, final Square end) {
        boolean result = true;
        Square cur = end;
        final Square finish = start;
        while (cur != finish) {
            cur = cur.rightNeighbor();
            cur = cur.aboveNeighbor();
            final Piece p = cur.getPiece();
            if (p != null && cur != finish) {
                result = false;
            }
        }
        return result;
    }

    @Override
    public Valid moveDirection(final Square start, final Square end) {
        final int horz = Math.abs(start.getRow() - end.getRow());
        final int vert = Math.abs(start.getCol() - end.getCol());
        Valid result = Valid.INVALID;
        if (start.getRow() == end.getRow()) {
            if (start.getCol() > end.getCol()) {
                result = Valid.LEFT;
            } else {
                result = Valid.RIGHT;
            }
        } else if (start.getCol() == end.getCol()) {
            if (start.getRow() > end.getRow()) {
                result = Valid.UP;
            } else {
                result = Valid.DOWN;
            }
        } else if (horz == vert) {
            if (start.getRow() > end.getRow()) {
                if (start.getCol() > end.getCol()) {
                    result = Valid.DIAGLEFTUP;
                } else {
                    result = Valid.DIAGRIGHTUP;
                }
            } else if (start.getCol() > end.getCol()) {
                result = Valid.DIAGLEFTDOWN;
            } else {
                result = Valid.DIAGRIGHTDOWN;
            }
        }
        return result;
    }

    @Override
    public int getNumberOfCols(final int row) {
        return this.squares[row].length;
    }
}
