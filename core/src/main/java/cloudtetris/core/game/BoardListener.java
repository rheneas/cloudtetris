package cloudtetris.core.game;

/**
 * This interface allows an object to listen for BoardEvent's which are
 * generated by a TetrisGame everytime something on the board changes.
 * 
 * @author Scott Clee
 */
public interface BoardListener {
	
	/**
	 * This method is invoked everytime something on board changes in a
	 * TetrisGame.
	 * 
	 * @param event
	 *            a BoardEvent.
	 */
	public void boardChange(TetrisBoard board);
}
