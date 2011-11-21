package cloudtetris.core.game;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TetrisGameTest {

    @Mock
    private RandomNumberGenerator randomNumberGenerator;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPlayGame() {
        when(randomNumberGenerator.getRandom()).thenReturn(PieceType.PIECES.indexOf(PieceType.O_PIECE));
        TetrisPieceFactory.randomNumberGenerator = randomNumberGenerator;

        TetrisGame tetrisGame = new TetrisGame();
        tetrisGame.startGame();

        tetrisGame.cycleThroughOneIteration();

        assertTrue(tetrisGame.hasStarted());
    }
}
