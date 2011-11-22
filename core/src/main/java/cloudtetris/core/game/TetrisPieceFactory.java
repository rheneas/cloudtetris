package cloudtetris.core.game;

import java.util.ArrayList;
import java.util.List;

public class TetrisPieceFactory {

    public static final int NUMBER_OF_PIECES_TO_GENERATE = 3;
    private RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
    private List<TetrisPiece> nextPieces = new ArrayList<TetrisPiece>();

    public TetrisPiece getPiece(TetrisBoard board) {
        if (nextPieces.isEmpty()) {
            for (int i = 0; i < NUMBER_OF_PIECES_TO_GENERATE; i++) {
                nextPieces.add(newPiece(board));
            }
        }

        TetrisPiece tetrisPiece = nextPieces.remove(0);
        nextPieces.add(newPiece(board));
        return tetrisPiece;
    }

    public void setNextPieces(TetrisBoard[] boards) {
        for (int i = 0; i < boards.length; i++) {
            previewNextPiece(boards[i], i);
        }
    }

    private void previewNextPiece(TetrisBoard board, int position) {
        TetrisPiece tetrisPiece = new TetrisPiece(nextPieces.get(position));
        tetrisPiece.setCentrePoint(new Point(board.getColumns() / 2, 1));
        board.addPiece(tetrisPiece);
    }

    private TetrisPiece newPiece(TetrisBoard board) {
        TetrisPiece tetrisPiece = new TetrisPiece(PieceType.getPiece(randomNumberGenerator.getRandom()), board);

        tetrisPiece.setCentrePoint(new Point(board.getColumns() / 2, 1));
        return tetrisPiece;
    }

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }
}
