package cloudtetris.core;

import cloudtetris.core.game.Direction;
import cloudtetris.core.game.TetrisGame;
import playn.core.Key;
import playn.core.Keyboard;

import java.util.HashMap;
import java.util.Map;

public class KeyboardController implements Keyboard.Listener {

    private final TetrisGame tetrisGame;
    private final Map<Key, Direction> keyToDirectionMap = new HashMap<Key, Direction>();

    public KeyboardController(TetrisGame tetrisGame) {
        this.tetrisGame = tetrisGame;
        buildKeyToDirectionMap();
    }

    private void buildKeyToDirectionMap() {
        keyToDirectionMap.put(Key.LEFT, Direction.LEFT);
        keyToDirectionMap.put(Key.RIGHT, Direction.RIGHT);
        keyToDirectionMap.put(Key.UP, Direction.ROTATE);
        keyToDirectionMap.put(Key.DOWN, Direction.DOWN);
        keyToDirectionMap.put(Key.SPACE, Direction.FALL);
    }

    @Override
    public void onKeyDown(Keyboard.Event event) {
    }

    @Override
    public void onKeyTyped(Keyboard.TypedEvent event) {
    }

    @Override
    public void onKeyUp(Keyboard.Event event) {
        tetrisGame.movePiece(keyToDirectionMap.get(event.key()));
    }
}
