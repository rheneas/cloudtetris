package cloudtetris.core.game;

public class PreviewBoards {

	private static final int DEFAULT_ROWS = 5;
	private static final int DEFAULT_COLS = 5;
	private TetrisBoard[] boards;
	
	public PreviewBoards(int numberOfTilesToPreview) {
		boards = new TetrisBoard[numberOfTilesToPreview];
		
		for (int i = 0; i < numberOfTilesToPreview; i++) {
			boards[i] = new TetrisBoard(DEFAULT_COLS, DEFAULT_ROWS);
		}
	}
	
	public void resetBoard() {
		for (TetrisBoard board : boards) {
			board.resetBoard();
		}
	}
	
	public TetrisBoard[] getBoards() {
		return boards;
	}
	
}
