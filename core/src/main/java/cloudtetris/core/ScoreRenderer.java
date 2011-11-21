package cloudtetris.core;

import cloudtetris.core.game.GameState;
import playn.core.GroupLayer;
import playn.core.PlayN;
import tripleplay.ui.*;

import static playn.core.PlayN.*;

public class ScoreRenderer {

    private Interface iface;
    private Label lineLabel;
    private Label scoreLabel;
    private final GameState gameState;


    public ScoreRenderer(GroupLayer groupLayer, GameState gameState) {
        this.gameState = gameState;

        iface = new Interface(null);

        Styles labelStyles = Styles.none().
                add(Style.FONT.is(PlayN.graphics().createFont("Helvetica", playn.core.Font.Style.PLAIN, 24)));
        Stylesheet rootSheet = Stylesheet.builder().
                add(Label.class, labelStyles).
                create();
        Root menu = iface.createRoot(AxisLayout.vertical().gap(15), rootSheet);

        int xOffset = (graphics().width() / 2) + 20;
        int yOffset = 300;

        menu.setSize(xOffset, yOffset);
        menu.addStyles(Styles.make(Style.BACKGROUND.is(Background.solid(0x808080, 0))));
        lineLabel = new Label("Lines: ");
        scoreLabel = new Label("Score:");
        menu.add(scoreLabel, lineLabel);
        groupLayer.add(menu.layer);
    }

    public void paint(float alpha) {
        lineLabel.text.update("Lines: " + gameState.getLines());
        scoreLabel.text.update("Score: " + gameState.getScore());

        iface.paint(alpha);
    }
}
