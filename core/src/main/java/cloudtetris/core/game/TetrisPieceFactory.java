package cloudtetris.core.game;

public class TetrisPieceFactory {

    private RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

    public TetrisPiece getPiece(TetrisBoard board) {
        TetrisPiece tetrisPiece = new TetrisPiece(PieceType.getPiece(randomNumberGenerator.getRandom()), board);
        tetrisPiece.setCentrePoint(new Point(board.getColumns() / 2, 1));
        return tetrisPiece;
    }

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }
}
