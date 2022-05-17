
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import java.awt.Color;

public class Board3D extends Board {
    public static final int LOWER = 0;
    public static final int MIDDLE = 1;
    public static final int UPPER = 2;
    private int row;
    private int col;
    Board[] board;

    @Override
    public void init(final Listen listener) {
        this.board = new Board[3];
        (this.board[0] = new CBoard(this.row, this.col))
                .setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));
        (this.board[1] = new CBoard(this.row, this.col))
                .setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));
        (this.board[2] = new CBoard(this.row, this.col))
                .setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));
        this.board[0].init(listener);
        this.board[1].init(listener);
        this.board[2].init(listener);
        this.curBoard = this.board[0];
        this.setLayout(new GridLayout(1, 3));
        this.add(this.board[0]);
        this.add(this.board[1]);
        this.add(this.board[2]);
    }

    public Board3D(final int row, final int col) {
        this.row = row;
        this.col = col;
    }

    public Board getCurBoard(final Square s) {
        Board result = null;
        if (s == this.board[0].getSquareAt(s.getRow(), s.getCol())) {
            result = this.board[0];
        } else if (s == this.board[1].getSquareAt(s.getRow(), s.getCol())) {
            result = this.board[1];
        } else if (s == this.board[2].getSquareAt(s.getRow(), s.getCol())) {
            result = this.board[2];
        }
        return this.curBoard = result;
    }

    @Override
    public Square getSquareAt(final int row, final int col) {
        return this.curBoard.getSquareAt(row, col);
    }

    @Override
    public int getNumberOfRows() {
        return this.curBoard.getNumberOfRows();
    }

    @Override
    public boolean isVertPathEmpty(final Square start, final Square end) {
        boolean result = false;
        final Board first = this.getCurBoard(start);
        final Board last = this.getCurBoard(end);
        final int diffx = end.getRow() - start.getRow();
        if (first == last) {
            result = first.isVertPathEmpty(start, end);
        } else if (end.getPiece() == null) {
            if (first != this.board[1] && last != this.board[1]) {
                final Square middle = this.board[1].getSquareAt(start.getRow() + diffx / Math.abs(diffx),
                        start.getCol());
                result = (middle.getPiece() == null);
            } else {
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean isHorzPathEmpty(final Square start, final Square end) {
        boolean result = false;
        final Board first = this.getCurBoard(start);
        final Board last = this.getCurBoard(end);
        final int diffy = end.getCol() - start.getCol();
        if (first == last) {
            result = first.isHorzPathEmpty(start, end);
        } else if (end.getPiece() == null) {
            if (first != this.board[1] && last != this.board[1]) {
                final Square middle = this.board[1].getSquareAt(start.getRow() + 1,
                        start.getCol() + diffy / Math.abs(diffy));
                result = (middle.getPiece() == null);
            } else {
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean isLeftDiagPathEmpty(final Square start, final Square end) {
        boolean result = false;
        final Board first = this.getCurBoard(start);
        final Board last = this.getCurBoard(end);
        if (first == last) {
            result = first.isLeftDiagPathEmpty(start, end);
        } else if (end.getPiece() == null) {
            if (first != this.board[1] && last != this.board[1]) {
                final Square middle = this.board[1].getSquareAt(start.getRow() - 1, start.getCol() + 1);
                result = (middle.getPiece() == null);
            } else {
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean isLeftDownDiagPathEmpty(final Square start, final Square end) {
        boolean result = false;
        final Board first = this.getCurBoard(start);
        final Board last = this.getCurBoard(end);
        if (first == last) {
            result = first.isLeftDownDiagPathEmpty(start, end);
        } else if (end.getPiece() == null) {
            if (first != this.board[1] && last != this.board[1]) {
                final Square middle = this.board[1].getSquareAt(start.getRow() + 1, start.getCol() - 1);
                result = (middle.getPiece() == null);
            } else {
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean isRightDownDiagPathEmpty(final Square start, final Square end) {
        boolean result = false;
        final Board first = this.getCurBoard(start);
        final Board last = this.getCurBoard(end);
        if (first == last) {
            result = first.isRightDownDiagPathEmpty(start, end);
        } else if (end.getPiece() == null) {
            if (first != this.board[1] && last != this.board[1]) {
                final Square middle = this.board[1].getSquareAt(start.getRow() + 1, start.getCol() + 1);
                result = (middle.getPiece() == null);
            } else {
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean isRightDiagPathEmpty(final Square start, final Square end) {
        boolean result = false;
        final Board first = this.getCurBoard(start);
        final Board last = this.getCurBoard(end);
        if (first == last) {
            result = first.isRightDiagPathEmpty(start, end);
        } else if (end.getPiece() == null) {
            if (first != this.board[1] && last != this.board[1]) {
                final Square middle = this.board[1].getSquareAt(start.getRow() - 1, start.getCol() + 1);
                result = (middle.getPiece() == null);
            } else {
                result = true;
            }
        }
        return result;
    }

    @Override
    public Valid moveDirection(final Square start, final Square end) {
        Valid direction = Valid.INVALID;
        final Board first = this.getCurBoard(start);
        final Board last = this.getCurBoard(end);
        int distance = 1;
        int fboard = 0;
        int eboard = 0;
        final int diffy = end.getRow() - start.getRow();
        final int diffx = end.getCol() - start.getCol();
        if (first != this.board[1] && last != this.board[1] && first != last) {
            distance = 2;
        }
        if (first == this.board[1]) {
            fboard = 1;
        }
        if (first == this.board[2]) {
            fboard = 2;
        }
        if (last == this.board[1]) {
            eboard = 1;
        }
        if (last == this.board[2]) {
            eboard = 2;
        }
        if (fboard > eboard) {
            distance *= -1;
        }
        if (first == last) {
            direction = first.moveDirection(start, end);
        } else {
            if (start.getRow() == end.getRow() && start.getCol() == end.getCol()) {
                if (distance > 0) {
                    direction = Valid.UP;
                } else {
                    direction = Valid.DOWN;
                }
            } else if (diffy > 0) {
                if (start.getCol() == end.getCol()) {
                    direction = Valid.DOWN;
                } else if (diffx < 0) {
                    direction = Valid.DIAGRIGHTDOWN;
                } else {
                    direction = Valid.DIAGLEFTDOWN;
                }
            } else if (diffy < 0) {
                if (start.getCol() == end.getCol()) {
                    direction = Valid.UP;
                } else if (diffx < 0) {
                    direction = Valid.DIAGRIGHTUP;
                } else {
                    direction = Valid.DIAGLEFTUP;
                }
            } else if (diffy == 0) {
                if (diffx < 0) {
                    direction = Valid.LEFT;
                } else {
                    direction = Valid.RIGHT;
                }
            }
            if (Math.abs(diffx) != 0 && Math.abs(diffy) != 0 && Math.abs(diffx) != Math.abs(diffy)) {
                direction = Valid.INVALID;
            }
            if (Math.abs(diffx) == Math.abs(diffy) && Math.abs(distance) != Math.abs(diffx)) {
                direction = Valid.INVALID;
            }
            if (Math.abs(distance) != Math.abs(diffx) && Math.abs(distance) != Math.abs(diffy)) {
                direction = Valid.INVALID;
            }
        }
        return direction;
    }

    @Override
    public int getNumberOfCols(final int row) {
        return this.curBoard.getNumberOfCols(row);
    }
}
