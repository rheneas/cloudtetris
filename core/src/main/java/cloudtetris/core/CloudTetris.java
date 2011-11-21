package cloudtetris.core;

import cloudtetris.core.game.GameStateListener;
import cloudtetris.core.game.TetrisGame;
import playn.core.Game;
import playn.core.GroupLayer;
import playn.core.SurfaceLayer;
import react.UnitSlot;

import static playn.core.PlayN.*;

public class CloudTetris implements Game {

    private TetrisGame tetrisGame;
    private BoardRenderer boardRenderer;
    private MenuRenderer menuRenderer;
    private ScoreRenderer scoreRenderer;

    private GroupLayer groupLayer;
    private SurfaceLayer boardLayer;

    private long lastUpdate = System.currentTimeMillis();

    @Override
    public void init() {
        groupLayer = graphics().createGroupLayer();
        graphics().setSize(1024, 768);
        graphics().rootLayer().add(groupLayer);
        // put the game layer on first
        createGame(groupLayer);
        UnitSlot buttonPressedCallback = new UnitSlot() {
            @Override
            public void onEmit() {
                buttonPressed();
            }
        };

        menuRenderer = new MenuRenderer(groupLayer, buttonPressedCallback);
        scoreRenderer = new ScoreRenderer(groupLayer, tetrisGame.getGameState());
    }

    private void buttonPressed() {
        tetrisGame.startGame();
        menuRenderer.hide();
    }

    private void gameFinished() {
        menuRenderer.show();
    }

    private void createGame(GroupLayer groupLayer) {
        tetrisGame = new TetrisGame();
        tetrisGame.setGameStateListener(new GameStateListener() {
            @Override
            public void stopped() {
                gameFinished();
            }
        });

        KeyboardController keyboardController = new KeyboardController(tetrisGame);
        keyboard().setListener(keyboardController);

        ImageFactory imageFactory = new ImageFactory(assetManager());

        boardLayer = graphics().createSurfaceLayer(1024, 768);
        boardRenderer = new BoardRenderer(assetManager(), boardLayer, imageFactory, tetrisGame, (graphics().width() / 2) - 100, 100);
        groupLayer.add(boardLayer);
    }

    @Override
    public void paint(float alpha) {
        menuRenderer.paint(alpha);
        scoreRenderer.paint(alpha);
        boardRenderer.paint();
    }

    @Override
    public void update(float delta) {
        tetrisGame.cycleThroughOneIteration();
    }

    @Override
    public int updateRate() {
        return 25;
    }
}
