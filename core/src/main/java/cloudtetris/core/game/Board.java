package cloudtetris.core.game;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private final PieceType[][] matrix;
	private final int maxX;
	private final int maxY;
	
	public Board(int x, int y) {
		matrix = new PieceType[x][y];
		this.maxX = x;
		this.maxY = y;
	}

	public Board(Board board) {
		this(board.maxX, board.maxY);

		for (int currentX = 0; currentX < maxX; currentX++) {
			for (int currentY = 0; currentY < maxY; currentY++) {
				setPieceAt(currentX, currentY, board.getPieceAt(currentX, currentY));
			}
		}
	}

	public int getX() {
		return maxX;
	}

	public int getY() {
		return maxY;
	}

	public int getCols() {
		return maxX;
	}

	public int getRows() {
		return maxY;
	}

	public PieceType getPieceAt(int x, int y) {
		return matrix[x][y];
	}

	public Board setPieceAt(int x, int y, PieceType value) {
		matrix[x][y] = value;
		return this;
	}
	
	public boolean hasHolesAbove(int x, int y) {
		List<Boolean> currentColumn = new ArrayList<Boolean>(); 
		
		for (int currentY = y; currentY >= 0; currentY--) {
			currentColumn.add(getPieceAt(x, currentY) != null);
		}

		if (allEmpty(currentColumn)) {
			return false;
		}
		
		int firstOccurenceOfAHole = currentColumn.indexOf(false);
		if (firstOccurenceOfAHole == -1) {
			return false;
		}
		
		List<Boolean> columnAfterFirstHole = currentColumn.subList(firstOccurenceOfAHole, currentColumn.size());
		int firstOccurenceOfAPiece = columnAfterFirstHole.indexOf(true);
		
		return (firstOccurenceOfAPiece > 0);
		
	}
	
	public double getAverageHeight() {
		double totalHeight = 0;
		for (int currentX = 0; currentX < getX(); currentX++) {
			totalHeight += getColumnHeight(currentX);
		}
		
		return totalHeight/getCols();
	}
	
	public int getColumnHeight(int x) {
		int height = 0;
		for (int currentY = (getY() - 1); currentY >= 0; currentY--) {
			if (getPieceAt(x, currentY) != null) {
				height++;
			}
		}
		
		return height;
	}
	
	private boolean allEmpty(List<Boolean> column) {
		for (Boolean hasPiece : column) {
			if (hasPiece) {
				return false;
			}
		}
		
		return true;
	}
	
	public int getMaxHeight() {
		int maxHeight = 0;
		for (int currentX = 0; currentX < getX(); currentX++) {
			int currentHeight = getColumnHeight(currentX);
			if (currentHeight > maxHeight) {
				maxHeight = currentHeight;
			}
		}
		
		return maxHeight;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (int y = getY() - 1; y >= 0; y--) {
			for (int x = 0; x < getX(); x++) {
				sb.append(getPieceAt(x, y) == null ? " " : "X");				
			}
			sb.append("\n");
		}

		return sb.toString();
	}
}
