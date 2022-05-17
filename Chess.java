import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Chess extends JFrame {
    private static final String WHITE_PAWN_KEY;
    private static final String BLACK_PAWN_KEY;
    private final Board board;
    private final Player whitePlayer;
    private final Player blackPlayer;
    private Player currentPlayer;

    static {
        WHITE_PAWN_KEY = "WhitePawn";
        BLACK_PAWN_KEY = "BlackPawn";
    }

    public static void main(final String[] argv) {
        final Chess game = new Chess();
        game.init();
        centre(game);
        game.setVisible(true);
    }

    public Chess() {
        super("3D Chess");
        this.board = new Board3D(8, 8);
        this.whitePlayer = new Player(Color.WHITE);
        this.blackPlayer = new Player(Color.BLACK);
    }

    private void init() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent evt) {
                Chess.this.shutdown(0);
            }
        });
        this.board.init(new Listen(this));
        this.initPieces();
        this.currentPlayer = this.whitePlayer;
        this.getContentPane().setLayout(new GridLayout(1, 1));
        this.getContentPane().add(this.board);
    }

    private void initPieces() {
        this.setPiece(0, 0, new Rook(this.blackPlayer));
        this.setPiece(0, 2, new Bishop(this.blackPlayer));
        this.setPiece(0, 1, new Knight(this.blackPlayer));
        this.setPiece(0, 3, new King(this.blackPlayer));
        this.setPiece(0, 4, new Queen(this.blackPlayer));
        this.setPiece(0, 6, new Knight(this.blackPlayer));
        this.setPiece(0, 5, new Bishop(this.blackPlayer));
        this.setPiece(0, 7, new Rook(this.blackPlayer));
        for (int col = 0; col < this.board.getNumberOfCols(1); ++col) {
            this.setPiece(1, col, new Pawn(this.blackPlayer, Chess.WHITE_PAWN_KEY));
        }
        for (int col = 0; col < this.board.getNumberOfCols(6); ++col) {
            this.setPiece(6, col, new Pawn(this.whitePlayer, Chess.BLACK_PAWN_KEY));
        }
        this.setPiece(7, 0, new Rook(this.whitePlayer));
        this.setPiece(7, 2, new Bishop(this.whitePlayer));
        this.setPiece(7, 1, new Knight(this.whitePlayer));
        this.setPiece(7, 3, new King(this.whitePlayer));
        this.setPiece(7, 4, new Queen(this.whitePlayer));
        this.setPiece(7, 6, new Knight(this.whitePlayer));
        this.setPiece(7, 5, new Bishop(this.whitePlayer));
        this.setPiece(7, 7, new Rook(this.whitePlayer));
    }

    private void setPiece(final int row, final int col, final Piece piece) {
        this.board.getCurBoard().getSquareAt(row, col).setPiece(piece);
    }

    private void shutdown(final int exitCode) {
        System.exit(exitCode);
    }

    private static void centre(Chess game) {
        game.setSize(1500, 600);
        game.setLocationRelativeTo(null);
        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void move(final Square fromSquare, final Square toSquare) {
        final Piece piece = fromSquare.getPiece();
        fromSquare.setPiece(null);
        toSquare.setPiece(piece);
        fromSquare.repaint();
        toSquare.repaint();
    }

    public Board getBoard() {
        return this.board;
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public void switchPlayers() {
        if (this.currentPlayer == this.blackPlayer) {
            this.currentPlayer = this.whitePlayer;
        } else {
            this.currentPlayer = this.blackPlayer;
        }
    }
}
