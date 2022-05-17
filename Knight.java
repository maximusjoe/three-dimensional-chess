public class Knight extends Piece {
    public Knight(final Player p) {
        super(p, "Knight");
    }

    @Override
    public boolean isValidMove(final Square toSquare, final Board board) {
        boolean valid = false;
        if (this.horizontaldistance(toSquare) == 2 && this.verticaldistance(toSquare) == 1) {
            valid = true;
        } else if (this.horizontaldistance(toSquare) == 1 && this.verticaldistance(toSquare) == 2) {
            valid = true;
        }
        return valid && this.isAvailable(toSquare);
    }
}
