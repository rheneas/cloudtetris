package cloudtetris.core.game;

import java.util.ArrayList;
import java.util.List;

public class TetrisBoard {

	public static final PieceType EMPTY_BLOCK = null;

	private BoardListener boardListener;
	private Board board;

	public TetrisBoard(int cols, int rows) {
		board = new Board(cols, rows);
	}

	public TetrisBoard(TetrisBoard tetrisBoard) {
		board = new Board(tetrisBoard.board);
		boardListener = null;
	}
	
	public void resetBoard() {
		board = new Board(board.getX(), board.getY());
	}

	public int getColumns() {
		return board.getX();
	}

	public int getRows() {
		return board.getY();
	}

	public PieceType getPieceAt(int x, int y) {
		return board.getPieceAt(x, y);
	}

	public void setPieceAt(int x, int y, PieceType value) {
		board.setPieceAt(x, y, value);
	}

	public void addPiece(TetrisPiece piece, boolean notify) {
		if (piece != null) {
			final Point centre = piece.getCentrePoint();
			final Point[] blocks = piece.getRelativePoints();

			for (int count = 0; count < 4; count++) {
				int x = centre.x + blocks[count].x;
				int y = centre.y + blocks[count].y;

				board.setPieceAt(x, y, piece.getType());
			}

			if (notify) {
				fireBoardEvent();
			}
		}
	}

	public void removePiece(TetrisPiece piece) {
		if (piece != null) {
			final Point centre = piece.getCentrePoint();
			final Point[] blocks = piece.getRelativePoints();

			for (int count = 0; count < 4; count++) {
				int x = centre.x + blocks[count].x;
				int y = centre.y + blocks[count].y;

				board.setPieceAt(x, y, EMPTY_BLOCK);
			}
		}
	}

	public void removeRow(int row) {
		for (int tempRow = row; tempRow > 0; tempRow--) {
			for (int tempCol = 0; tempCol < getColumns(); tempCol++) {
				board.setPieceAt(tempCol, tempRow, board.getPieceAt(tempCol, tempRow - 1));
			}
		}

		for (int tempCol = 0; tempCol < getColumns(); tempCol++) {
			board.setPieceAt(tempCol, 0, EMPTY_BLOCK);
		}

		fireBoardEvent();
	}

	public boolean willFit(TetrisPiece piece) {
		boolean result = true;

		if (piece != null) {
			final Point centre = piece.getCentrePoint();
			final Point[] blocks = piece.getRelativePoints();

			for (int count = 0; count < 4 && result; count++) {
				int x = centre.x + blocks[count].x;
				int y = centre.y + blocks[count].y;

				// Ensure it's within the boundaries
				if (x < 0 || x >= getColumns() || y < 0 || y >= getRows())
					result = false;

				if (result && board.getPieceAt(x, y) != EMPTY_BLOCK)
					result = false;
			}
		}

		return result;
	}

	private void fireBoardEvent() {
		if (boardListener != null) {
			boardListener.boardChange(this);
		}
	}

	public int removeCompletedLines() {
		int completedLines = 0;
		// First check for any complete lines.
		for (int row = getRows() - 1; row >= 0; row--) {
			if (isRowComplete(row)) {
				removeRow(row);

				// Set it so we check this row again since
				// the rows above have been moved down.
				row++;

				completedLines++;
			}
		}
		return completedLines;
	}

	public List<Integer> getRowsReadyForRemoval() {
		List<Integer> completedRows = new ArrayList<Integer>();
		for (int row = getRows() - 1; row >= 0; row--) {
			if (isRowComplete(row)) {
				completedRows.add(row);
			}
		}
		return completedRows;
	}

	public int getNumberOfRowsThatWillBeRemoved() {
		return getRowsReadyForRemoval().size();
	}
	
	private boolean isRowComplete(int row) {
		for (int cols = 0; cols < getColumns(); cols++) {
			if (getPieceAt(cols, row) == TetrisBoard.EMPTY_BLOCK) {
				return false;
			}
		}
		return true;
	}
}
