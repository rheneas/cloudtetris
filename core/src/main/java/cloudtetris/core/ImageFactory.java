package cloudtetris.core;

import cloudtetris.core.game.PieceType;
import playn.core.AssetManager;
import playn.core.Image;

import java.util.HashMap;
import java.util.Map;

public class ImageFactory {

    private final AssetManager assetManager;
    private final Map<PieceType, String> pieceTypeToResourcePathMap = new HashMap<PieceType, String>();

    public ImageFactory(AssetManager assetManager) {
        this.assetManager = assetManager;
        buildPieceTypeToResourcePathMap();
    }

    private void buildPieceTypeToResourcePathMap() {
        pieceTypeToResourcePathMap.put(PieceType.I_PIECE, "images/aqua.png");
        pieceTypeToResourcePathMap.put(PieceType.J_PIECE, "images/blue.png");
        pieceTypeToResourcePathMap.put(PieceType.L_PIECE, "images/green.png");
        pieceTypeToResourcePathMap.put(PieceType.O_PIECE, "images/orange.png");
        pieceTypeToResourcePathMap.put(PieceType.S_PIECE, "images/purple.png");
        pieceTypeToResourcePathMap.put(PieceType.T_PIECE, "images/red.png");
        pieceTypeToResourcePathMap.put(PieceType.Z_PIECE, "images/yellow.png");
    }

    public Image getImage(PieceType pieceType) {
        String path = pieceTypeToResourcePathMap.get(pieceType);
        if (path == null) {
            throw new IllegalArgumentException("Path for pieceType " + pieceType + " not found");
        }

        return assetManager.getImage(path);
    }
}
