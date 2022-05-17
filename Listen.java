import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class Listen extends MouseAdapter {
    private final Chess game;
    private Square startSquare;

    public Listen(final Chess g) {
        this.game = g;
    }

    @Override
    public void mousePressed(final MouseEvent evt) {
        final Square square = (Square) evt.getSource();
        if (this.startSquare == null) {
            final Piece startPiece = square.getPiece();
            if (startPiece != null && startPiece.getOwner() == this.game.getCurrentPlayer()) {
                (this.startSquare = square).setActive(true);
                this.startSquare.repaint();
            }
        } else {
            final Piece endPiece = square.getPiece();
            final Piece startPiece2 = this.startSquare.getPiece();
            if (endPiece != null && startPiece2.getOwner() == endPiece.getOwner()) {
                this.startSquare.setActive(false);
                this.startSquare.repaint();
                (this.startSquare = square).setActive(true);
                this.startSquare.repaint();
            } else if (startPiece2.isValidMove(square, this.game.getBoard())) {
                this.game.move(this.startSquare, square);
                this.startSquare.setActive(false);
                this.startSquare.repaint();
                this.startSquare = null;
                this.game.switchPlayers();
            }
        }
    }
}
