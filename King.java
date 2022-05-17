
public class King extends Piece {
    public King(final Player p) {
        super(p, "King");
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
            case DIAGLEFTUP:
            case DIAGRIGHTDOWN: {
                valid = board.isLeftDiagPathEmpty(this.getSquare(), toSquare);
                break;
            }
            case DIAGRIGHTUP:
            case DIAGLEFTDOWN: {
                valid = board.isRightDiagPathEmpty(this.getSquare(), toSquare);
                break;
            }
            case INVALID: {
                valid = false;
                break;
            }
        }
        if (!(this.verticaldistance(toSquare) == 1 || this.horizontaldistance(toSquare) == 1)) {
            valid = (this.verticaldistance(toSquare) == 0 && this.horizontaldistance(toSquare) == 0 && valid);
        }
        return valid && this.isAvailable(toSquare);
    }
}
