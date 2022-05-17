
public class Rook extends Piece {
    public Rook(final Player p) {
        super(p, "Rook");
    }

    @Override
    public boolean isValidMove(final Square toSquare, final Board board) {
        boolean valid = false;
        final Valid d = board.moveDirection(this.getSquare(), toSquare);
        switch (d) {
            case UP:
            case DOWN: {
                valid = board.isVertPathEmpty(this.getSquare(), toSquare);
                break;
            }
            case LEFT:
            case RIGHT: {
                valid = board.isHorzPathEmpty(this.getSquare(), toSquare);
                break;
            }
            case DIAGLEFTDOWN:
            case DIAGLEFTUP:
            case DIAGRIGHTDOWN:
            case DIAGRIGHTUP:
            case INVALID: {
                valid = false;
                break;
            }
        }
        return valid && this.isAvailable(toSquare);
    }
}
