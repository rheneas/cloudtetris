package cloudtetris.core.game;

public class EventHandler {

	private ScoreListener scoreListener;

	public void fireScoreEvent(int score) {
		if (scoreListener != null) {
			scoreListener.scoreChange(score);
		}
	}

	public void setScoreListener(ScoreListener listener) {
		scoreListener = listener;
	}
}