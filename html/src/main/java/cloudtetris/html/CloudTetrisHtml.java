package cloudtetris.html;

import cloudtetris.core.CloudTetris;
import playn.core.PlayN;
import playn.html.HtmlGame;
import playn.html.HtmlPlatform;

public class CloudTetrisHtml extends HtmlGame {

  @Override
  public void start() {
    HtmlPlatform platform = HtmlPlatform.register();
    platform.assetManager().setPathPrefix("cloudtetris/");
    PlayN.run(new CloudTetris());
  }
}
