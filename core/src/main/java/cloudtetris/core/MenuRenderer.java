package cloudtetris.core;

import playn.core.GroupLayer;
import playn.core.PlayN;
import react.UnitSlot;
import tripleplay.ui.*;

import static playn.core.PlayN.*;

public class MenuRenderer {

    private Interface iface;
    private Button button;
    private Label titleLabel;
    private Label gameOverLabel;

    public MenuRenderer(GroupLayer groupLayer, UnitSlot unitSlot) {
        iface = new Interface(null);
        pointer().setListener(iface.plistener);

        Styles buttonStyles = Styles.none().
                add(Style.BACKGROUND.is(Background.solid(0xFFFFFFFF, 5))).
                add(Style.FONT.is(PlayN.graphics().createFont("Helvetica", playn.core.Font.Style.PLAIN, 16))).
                addSelected(Style.BACKGROUND.is(Background.solid(0xFFCCCCCC, 6, 4, 4, 6)));

        Styles labelStyles = Styles.none().
                add(Style.FONT.is(PlayN.graphics().createFont("Helvetica", playn.core.Font.Style.PLAIN, 32)));
        Stylesheet rootSheet = Stylesheet.builder().
                add(Button.class, buttonStyles).
                add(Label.class, labelStyles).
                create();
        Root menu = iface.createRoot(AxisLayout.vertical().gap(15), rootSheet);
        menu.setSize(graphics().width(), graphics().height());

        button = new Button("Press to Play");
        button.clicked().connect(unitSlot);
        titleLabel = new Label("Cloud Tetris");
        gameOverLabel = new Label("Game Over");
        gameOverLabel.setVisible(false);
        menu.add(titleLabel, gameOverLabel, button);
        groupLayer.add(menu.layer);
    }

    public void paint(float alpha) {
        iface.paint(alpha);
    }

    public void hide() {
        button.setVisible(false);
        titleLabel.setVisible(false);
        gameOverLabel.setVisible(false);
    }

    public void show() {
        button.setVisible(true);
        titleLabel.setVisible(true);
        gameOverLabel.setVisible(true);
    }
}
