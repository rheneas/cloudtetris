package cloudtetris.core.game;

public class TetrisGame {

    private static final int NUMBER_OF_COLS = 10;
    private static final int NUMBER_OF_ROWS = 20;

    private GameState gameState;
    private TetrisBoard board;
    private PreviewBoards previewBoards;
    private TetrisPiece currentPiece;
    private TetrisPieceFactory factory;
    private long lastUpdate = 0;
    private boolean ignoreWait = false;

    public TetrisGame() {
        board = new TetrisBoard(NUMBER_OF_COLS, NUMBER_OF_ROWS);
        previewBoards = new PreviewBoards(TetrisPieceFactory.NUMBER_OF_PIECES_TO_GENERATE);
        gameState = new GameState();
        factory = new TetrisPieceFactory();
    }

    public void startGame() {
        if (!gameState.isPlaying()) {
            board.resetBoard();
            previewBoards.resetBoard();
            gameState.reset();
            gameState.startPlaying();
            currentPiece = null;
        }
    }

    public TetrisPiece getCurrentPiece() {
        return currentPiece;
    }

    public PieceType getPieceAt(int x, int y) {
        return board.getPieceAt(x, y);
    }

    public boolean hasPieceAt(int x, int y) {
        return getPieceAt(x, y) != null;
    }

    public int getX() {
        return board.getColumns();
    }

    public int getY() {
        return board.getRows();
    }

    public void stopGame() {
        gameState.stopPlaying();
    }

    public void movePiece(Direction direction) {
        if (direction == null) {
            return;
        }

        if (currentPiece != null && gameState.isPlaying()) {
            if (direction == Direction.DOWN || direction == Direction.FALL) {
                // If it won't go any further then drop it there.
                if (!currentPiece.move(direction)) {
                    currentPiece = null;
                }
            } else {
                currentPiece.move(direction);
            }
        }
    }

    public void cycleThroughOneIteration() {
        long timeSinceLastUpdate = System.currentTimeMillis() - lastUpdate;

        if (!ignoreWait && (timeSinceLastUpdate < gameState.getDelay())) {
            return;
        }

        lastUpdate = System.currentTimeMillis();

        if (gameState.isPlaying()) {
            if (currentPiece == null) {
                int completedLines = board.getNumberOfRowsThatWillBeRemoved();
                board.removeCompletedLines();

                if (completedLines > 0) {
                    gameState.incrementTotalLinesBy(completedLines);
                    gameState.updateDelay();
                    gameState.updateScore(completedLines);
                }

                currentPiece = factory.getPiece(board);
                updatePreviewBoards();

                if (board.willFit(currentPiece)) {
                    board.addPiece(currentPiece);
                } else {
                    board.addPiece(currentPiece);
                    stopGame();
                }
            } else {
                movePiece(Direction.DOWN);
            }
        }
    }

    private void updatePreviewBoards() {
        previewBoards.resetBoard();
        factory.setNextPieces(previewBoards.getBoards());

    }

    public boolean hasStarted() {
        return gameState.isPlaying();
    }

    public void setGameStateListener(GameStateListener gameStateListener) {
        gameState.setGameStateListener(gameStateListener);
    }

    public GameState getGameState() {
        return gameState;
    }

    public void ignoreWait() {
        ignoreWait = true;
    }

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        factory.setRandomNumberGenerator(randomNumberGenerator);
    }

    @Override
    public String toString() {
        return board.toString();
    }
}
