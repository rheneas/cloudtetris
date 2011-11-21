package cloudtetris.core.game;

import static cloudtetris.core.game.Direction.*;

public class TetrisPiece {

	private PieceType type;
	private int rotation;
	private int maxRotate;
	private Point centrePoint = new Point();
	private Point[] blocks = new Point[4];

	private TetrisBoard board;

	public TetrisPiece(PieceType type, TetrisBoard board) {
		this.type = type;
		this.board = board;
		initializeBlocks();
	}
	
	public TetrisPiece(TetrisPiece piece) {
		this.type = piece.type;
		this.board = piece.board;
		initializeBlocks();
	}
	
	public boolean move(Direction direction) {
		boolean result = true;

		if (direction == FALL) {
			boolean loop = true;

			while (loop) {
				board.removePiece(this);
				centrePoint.y++; // Drop

				if (board.willFit(this)) {
					board.addPiece(this, false);
				} else {
					centrePoint.y--; // Undrop
					board.addPiece(this, true);
					loop = false;
					result = false;
				}
			}
		} else {
			board.removePiece(this);

			doMove(direction);

			if (board.willFit(this)) {
				board.addPiece(this, true);
			} else {
				undoMove(direction);
				board.addPiece(this, true);
				result = false;
			}
		}

		return result;
	}

	private void doMove(Direction direction) {
		if (direction == LEFT) {
			centrePoint.x--;
		} else if (direction == RIGHT) {
			centrePoint.x++;
		} else if (direction == DOWN) {
			centrePoint.y++;
		} else if (direction == ROTATE) {
			rotateClockwise();
		}
	}

	private void undoMove(Direction direction) {
		if (direction == LEFT) {
			centrePoint.x++;
		} else if (direction == RIGHT) {
			centrePoint.x--;
		} else if (direction == DOWN) {
			centrePoint.y--;
		} else if (direction == ROTATE) {
			rotateAntiClockwise();
		}
	}

	public Point getCentrePoint() {
		return centrePoint;
	}

	public void setCentrePoint(Point point) {
		centrePoint = point;
	}

	public Point[] getRelativePoints() {
		return blocks;
	}

	public PieceType getType() {
		return type;
	}

	private void initializeBlocks() {
		switch (type) {
		case I_PIECE:
			blocks[0] = new Point(0, 0);
			blocks[1] = new Point(-1, 0);
			blocks[2] = new Point(1, 0);
			blocks[3] = new Point(2, 0);
			maxRotate = 2;
			break;

		case L_PIECE:
			blocks[0] = new Point(0, 0);
			blocks[1] = new Point(-1, 0);
			blocks[2] = new Point(-1, 1);
			blocks[3] = new Point(1, 0);
			maxRotate = 4;
			break;

		case J_PIECE:
			blocks[0] = new Point(0, 0);
			blocks[1] = new Point(-1, 0);
			blocks[2] = new Point(1, 0);
			blocks[3] = new Point(1, 1);
			maxRotate = 4;
			break;

		case Z_PIECE:
			blocks[0] = new Point(0, 0);
			blocks[1] = new Point(-1, 0);
			blocks[2] = new Point(0, 1);
			blocks[3] = new Point(1, 1);
			maxRotate = 2;
			break;

		case S_PIECE:
			blocks[0] = new Point(0, 0);
			blocks[1] = new Point(1, 0);
			blocks[2] = new Point(0, 1);
			blocks[3] = new Point(-1, 1);
			maxRotate = 2;
			break;

		case O_PIECE:
			blocks[0] = new Point(0, 0);
			blocks[1] = new Point(0, 1);
			blocks[2] = new Point(-1, 0);
			blocks[3] = new Point(-1, 1);
			maxRotate = 1;
			break;

		case T_PIECE:
			blocks[0] = new Point(0, 0);
			blocks[1] = new Point(-1, 0);
			blocks[2] = new Point(1, 0);
			blocks[3] = new Point(0, 1);
			maxRotate = 4;
			break;
		}
	}

	private void rotateClockwise() {
		if (maxRotate > 1) {
			rotation++;

			if (maxRotate == 2 && rotation == 2) {
				// Rotate Anti-Clockwise
				rotateClockwiseNow();
				rotateClockwiseNow();
				rotateClockwiseNow();
			} else {
				rotateClockwiseNow();
			}
		}

		rotation = rotation % maxRotate;
	}

	private void rotateAntiClockwise() {
		rotateClockwise();
		rotateClockwise();
		rotateClockwise();
	}

	private void rotateClockwiseNow() {
		for (int count = 0; count < 4; count++) {
			final int temp = blocks[count].x;

			blocks[count].x = -blocks[count].y;
			blocks[count].y = temp;
		}
	}

	public int getFurthestLeft() {
		Integer furthestLeft = null;
		for (Point point : getRelativePoints()) {
			int relativeX = (int)point.getX() + (int)centrePoint.getX();
			if (furthestLeft == null || (relativeX < furthestLeft)) {
				furthestLeft = relativeX;
			}
		}
		return furthestLeft;
	}

	public int getFurthestRight() {
		Integer furthestRight = null;
		for (Point point : getRelativePoints()) {
			int relativeX = (int)point.getX() + (int)centrePoint.getX();
			if (furthestRight == null || (relativeX > furthestRight)) {
				furthestRight = relativeX;
			}
		}
		return furthestRight;
	}

	public Direction moveHorizontallyUntil(int x) {
		boolean didItMove = false;
		if (shouldMoveLeft(x)) {
			do {
				didItMove = move(Direction.LEFT);
			} while (shouldMoveLeft(x) && didItMove);
			return Direction.LEFT;
		} else if (shouldMoveRight(x)) {
			do {
				didItMove = move(Direction.RIGHT);
			} while (shouldMoveRight(x) && didItMove);
			return Direction.RIGHT;
		}
		return null;
	}
	
	private boolean shouldMoveLeft(int x) {
		return x < getFurthestLeft();
	}
	
	private boolean shouldMoveRight(int x) {
		return x > getFurthestRight();
	}
	
	public int getMaxRotations() {
		return maxRotate;
	}
}
