package cloudtetris.core.game;

import java.util.Arrays;
import java.util.List;

public enum PieceType {

	L_PIECE, J_PIECE, I_PIECE, Z_PIECE, S_PIECE, O_PIECE, T_PIECE;

	public static List<PieceType> PIECES = Arrays.asList(L_PIECE, J_PIECE,
			I_PIECE, Z_PIECE, S_PIECE, O_PIECE, T_PIECE);

	public static int getMaxNumberOfPieces() {
		return PIECES.size();
	}

	public static PieceType getPiece(int index) {
		return PIECES.get(index);
	}
}
