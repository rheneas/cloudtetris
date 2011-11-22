package cloudtetris.core.game;

import static cloudtetris.core.game.Direction.*;

public class GameController {

    private final TetrisBoard board;

    public GameController(TetrisBoard tetrisBoard) {
        this.board = tetrisBoard;
    }

    public boolean move(TetrisPiece piece, Direction direction) {
        boolean result = true;

        if (direction == FALL) {
            boolean loop = true;

            while (loop) {
                board.removePiece(piece);
                piece.drop();

                if (board.willFit(piece)) {
                    board.addPiece(piece);
                } else {
                    piece.unDrop();
                    board.addPiece(piece);
                    loop = false;
                    result = false;
                }
            }
        } else {
            board.removePiece(piece);

            piece.newMove(direction);

            if (board.willFit(piece)) {
                board.addPiece(piece);
            } else {
                piece.newUndoMove(direction);
                board.addPiece(piece);
                result = false;
            }
        }

        return result;
    }
}
