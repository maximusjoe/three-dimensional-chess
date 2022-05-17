
public class Pawn extends Piece {
    boolean firstMove;
    int maxDistance;
    private String colour;

    public Pawn(final Player p, final String colour) {
        super(p, "Pawn");
        this.firstMove = true;
        this.maxDistance = 2;
        this.colour = colour;
    }

    @Override
    public boolean isValidMove(final Square toSquare, final Board board) {
        boolean valid = true;
        final boolean tempFirstMove = this.firstMove;
        Valid d = Valid.INVALID;
        if (this.horizontaldistance(toSquare) != 0) {
            valid = false;
        }
        final int distance = this.verticaldistance(toSquare);
        if (distance > this.maxDistance || distance == 0) {
            valid = false;
        }
        d = board.moveDirection(this.getSquare(), toSquare);
        if (d == Valid.INVALID) {
            valid = false;
        }
        if (this.colour == "WhitePawn") {
            if (d == Valid.UP) {
                valid = false;
            }
        } else if (d == Valid.DOWN) {
            valid = false;
        }
        valid = (valid && board.isVertPathEmpty(this.getSquare(), toSquare));
        if (tempFirstMove && !valid) {
            this.firstMove = true;
        }
        if (tempFirstMove && !this.isAvailable(toSquare)) {
            this.firstMove = true;
        }
        if (valid && this.isAvailable(toSquare)) {
            this.firstMove = false;
            --this.maxDistance;
        }
        return valid && this.isAvailable(toSquare);
    }
}
