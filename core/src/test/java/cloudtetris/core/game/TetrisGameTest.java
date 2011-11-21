package cloudtetris.core.game;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static cloudtetris.core.game.Direction.*;

public class TetrisGameTest {

    @Mock
    private RandomNumberGenerator randomNumberGenerator;
    private TetrisGame tetrisGame;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPlayGame() {
        when(randomNumberGenerator.getRandom()).thenReturn(PieceType.PIECES.indexOf(PieceType.O_PIECE));
        TetrisPieceFactory.randomNumberGenerator = randomNumberGenerator;
        tetrisGame = new TetrisGame();
        tetrisGame.ignoreWait();
        
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
    }

    private void move(Direction direction, int times) {
        for (int i = 0; i < times; i++) {
            tetrisGame.movePiece(direction);
        }
    }
}
