
public class Queen extends Piece {
    public Queen(final Player p) {
        super(p, "Queen");
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
            case DIAGLEFTUP: {
                valid = board.isLeftDiagPathEmpty(this.getSquare(), toSquare);
                break;
            }
            case DIAGRIGHTDOWN: {
                valid = board.isRightDownDiagPathEmpty(this.getSquare(), toSquare);
                break;
            }
            case DIAGRIGHTUP: {
                valid = board.isRightDiagPathEmpty(this.getSquare(), toSquare);
                break;
            }
            case DIAGLEFTDOWN: {
                valid = board.isRightDownDiagPathEmpty(this.getSquare(), toSquare);
                break;
            }
            case INVALID: {
                valid = false;
                break;
            }
        }
        return valid && this.isAvailable(toSquare);
    }
}
