package cloudtetris.android;

import cloudtetris.core.CloudTetris;
import playn.android.GameActivity;
import playn.core.PlayN;

public class CloudTetrisActivity extends GameActivity {

  @Override
  public void main(){
    platform().assetManager().setPathPrefix("cloudtetris/resources");
    PlayN.run(new CloudTetris());
  }
}
