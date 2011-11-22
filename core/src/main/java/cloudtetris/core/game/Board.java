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

	public int getX() {
		return maxX;
	}

	public int getY() {
		return maxY;
	}

	public PieceType getPieceAt(int x, int y) {
		return matrix[x][y];
	}

	public Board setPieceAt(int x, int y, PieceType value) {
		matrix[x][y] = value;
		return this;
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
