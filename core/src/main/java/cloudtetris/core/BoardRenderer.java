package cloudtetris.core;

import cloudtetris.core.game.TetrisGame;
import playn.core.*;

public class BoardRenderer {

    private static final int TILE_SIZE = 32;

    private final AssetManager assetManager;
    private final ImageFactory imageFactory;
    private final SurfaceLayer surfaceLayer;
    private final TetrisGame tetrisGame;
    private final int xOffset;
    private final int yOffset;

    public BoardRenderer(AssetManager assetManager,
                         SurfaceLayer surfaceLayer,
                         ImageFactory imageFactory,
                         TetrisGame tetrisGame,
                         int xOffset, int yOffset) {
        this.assetManager = assetManager;
        this.surfaceLayer = surfaceLayer;
        this.imageFactory = imageFactory;
        this.tetrisGame = tetrisGame;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void paint() {
        surfaceLayer.surface().clear();
        surfaceLayer.surface().setFillColor(Color.rgb(128, 128, 128));
        surfaceLayer.surface().fillRect(0, 0, surfaceLayer.width(), surfaceLayer.height());

        if (tetrisGame.hasStarted()) {
            drawRect();

            for (int x = 0; x < tetrisGame.getX(); x++) {
                for (int y = 0; y < tetrisGame.getY(); y++) {
                    if (tetrisGame.hasPieceAt(x, y)) {
                        int xPosition = x * TILE_SIZE;
                        int yPosition = y * TILE_SIZE;
                        surfaceLayer.surface().drawImage(imageFactory.getImage(tetrisGame.getPieceAt(x, y)), xPosition + xOffset, yPosition + yOffset);
                    }
                }
            }
        }
    }

    private void drawRect() {
        int x1 = xOffset;
        int y1 = yOffset;

        int x2 = xOffset + tetrisGame.getX() * TILE_SIZE;
        int y2 = yOffset + tetrisGame.getY() * TILE_SIZE;

        int width = 5;

        surfaceLayer.surface().setFillColor(Color.rgb(0, 0, 0));
        surfaceLayer.surface().drawLine(x1, y1, x2, y1, width);
        surfaceLayer.surface().drawLine(x2, y1, x2, y2, width);
        surfaceLayer.surface().drawLine(x2, y2, x1, y2, width);
        surfaceLayer.surface().drawLine(x1, y2, x1, y1, width);
    }

}
