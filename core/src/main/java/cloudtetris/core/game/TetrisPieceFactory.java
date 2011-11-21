package cloudtetris.core.game;

import java.util.*;

public class TetrisPieceFactory {

	public static final int NUMBER_OF_PIECES_TO_GENERATE = 3;
	public static RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
	private List<TetrisPiece> nextPieces = new ArrayList<TetrisPiece>();
	
	public TetrisPieceFactory(TetrisBoard board) {
		for (int i = 0; i < NUMBER_OF_PIECES_TO_GENERATE; i++) {
			nextPieces.add(newPiece(board));				
		}
	}
	
	public TetrisPiece getPiece(TetrisBoard board) {
		TetrisPiece tetrisPiece =  nextPieces.remove(0);
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
		board.addPiece(tetrisPiece, false);
	}
	
	private TetrisPiece newPiece(TetrisBoard board) {
		TetrisPiece tetrisPiece = new TetrisPiece(PieceType.getPiece(randomNumberGenerator.getRandom()), board);

		tetrisPiece.setCentrePoint(new Point(board.getColumns() / 2, 1));
		return tetrisPiece;
	}

}
