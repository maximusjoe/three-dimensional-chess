public class Bishop extends Piece {
    public Bishop(final Player p) {
        super(p, "Bishop");
    }

    @Override
    public boolean isValidMove(final Square toSquare, final Board board) {
        boolean valid = false;
        final Valid d = board.moveDirection(this.getSquare(), toSquare);
        switch (d) {
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
                valid = board.isLeftDownDiagPathEmpty(this.getSquare(), toSquare);
                break;
            }
            default: {
                valid = false;
                break;
            }
        }
        return valid && this.isAvailable(toSquare);
    }
}
