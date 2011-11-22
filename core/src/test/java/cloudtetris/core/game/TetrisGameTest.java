package cloudtetris.core.game;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static cloudtetris.core.game.Direction.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TetrisGameTest {

    @Mock
    private RandomNumberGenerator randomNumberGenerator;
    private TetrisGame tetrisGame;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        when(randomNumberGenerator.getRandom()).thenReturn(PieceType.PIECES.indexOf(PieceType.O_PIECE));
        tetrisGame = new TetrisGame();
        tetrisGame.setRandomNumberGenerator(randomNumberGenerator);
        tetrisGame.ignoreWait();
    }

    @Test
    public void shouldEndGame() {
        tetrisGame.startGame();

        for (int i = 0; i < 9; i++) {
            tetrisGame.cycleThroughOneIteration();
            tetrisGame.movePiece(FALL);
        }

        tetrisGame.cycleThroughOneIteration();
        assertFalse(tetrisGame.getGameState().isPlaying());
    }

    @Test
    public void shouldMakeALine() {
        tetrisGame.startGame();
        tetrisGame.cycleThroughOneIteration();

        assertTrue(tetrisGame.hasStarted());
        assertEquals(PieceType.O_PIECE, tetrisGame.getCurrentPiece().getType());

        move(LEFT, 4);
        tetrisGame.movePiece(FALL);

        tetrisGame.cycleThroughOneIteration();
        move(LEFT, 2);
        tetrisGame.movePiece(FALL);

        tetrisGame.cycleThroughOneIteration();
        tetrisGame.movePiece(FALL);

        tetrisGame.cycleThroughOneIteration();
        move(RIGHT, 2);
        tetrisGame.movePiece(FALL);

        tetrisGame.cycleThroughOneIteration();
        move(RIGHT, 4);
        tetrisGame.movePiece(FALL);
        tetrisGame.cycleThroughOneIteration();

        assertEquals(2, tetrisGame.getGameState().getLines());
        assertEquals(400, tetrisGame.getGameState().getScore());
    }

    private void move(Direction direction, int times) {
        for (int i = 0; i < times; i++) {
            tetrisGame.movePiece(direction);
        }
    }
}
