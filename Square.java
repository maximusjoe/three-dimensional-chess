import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Canvas;

public class Square extends Canvas {
    private final int row;
    private final int col;
    private Piece piece;
    private boolean isActive;
    private Board board;

    public Square(final int r, final int c, final Color colour, final Board b) {
        this.row = r;
        this.col = c;
        this.setBackground(colour);
        this.setFont(new Font("", Font.PLAIN, 15));
        this.board = b;
    }

    public void setActive(final boolean f) {
        this.isActive = f;
    }

    public Square leftNeighbor() {
        if (this.col > 0) {
            return this.board.getSquareAt(this.row, this.col - 1);
        }
        return null;
    }

    public Square rightNeighbor() {
        if (this.col + 1 < this.board.getNumberOfCols(this.row)) {
            return this.board.getSquareAt(this.row, this.col + 1);
        }
        return null;
    }

    public Square aboveNeighbor() {
        if (this.row > 0) {
            return this.board.getSquareAt(this.row - 1, this.col);
        }
        return null;
    }

    public Square belowNeighbor() {
        if (this.row + 1 < this.board.getNumberOfRows()) {
            return this.board.getSquareAt(this.row + 1, this.col);
        }
        return null;
    }

    @Override
    public void paint(final Graphics g) {
        super.paint(g);
        if (this.piece != null) {
            g.setColor(this.piece.getOwner().colour);
            g.drawString(this.piece.getImage(), (int) (this.getWidth() / 6.5), (int) (this.getHeight() / 1.7));
        }
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public void setPiece(final Piece p) {
        if (this.piece != null) {
            this.piece.setSquare(null);
        }
        this.piece = p;
        if (this.piece != null) {
            this.piece.setSquare(this);
        }
    }

    public Piece getPiece() {
        return this.piece;
    }
}
