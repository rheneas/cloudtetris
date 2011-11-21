package cloudtetris.core.game;

public class GameState {

    private GameStateListener gameStateListener;

	private int totalLines;
	private int delay;
	private int score;
	private boolean playing;

	public void updateDelay() {
		// If we've hit a target then speed things up.
		if (totalLines == 10)
			delay = 500;
		if (totalLines == 20)
			delay = 400;
		if (totalLines == 30)
			delay = 300;
		if (totalLines == 40)
			delay = 250;
		if (totalLines == 50)
			delay = 120;
	}

	public void reset() {
		totalLines = 0;
		score = 0;
		delay = 750;
		playing = false;
	}

	public void incrementTotalLinesBy(int completedLines) {
		totalLines += completedLines;
	}

	public int getScore() {
		return score;
	}

	public void updateScore(int completeLines) {
		score += completeLines * completeLines * 100;
	}

	public boolean isPlaying() {
		return playing;
	}

	public void stopPlaying() {
		playing = false;
        notifyStop();
	}

	public void startPlaying() {
		playing = true;
	}

    private void notifyStop() {
        if (gameStateListener != null) {
            gameStateListener.stopped();
        }
    }

    public long getDelay() {
        return delay;
    }

    public void setGameStateListener(GameStateListener gameStateListener) {
        this.gameStateListener = gameStateListener;
    }

    public int getLines() {
        return totalLines;
    }
}