package cloudtetris.java;

import cloudtetris.core.CloudTetris;
import playn.core.PlayN;
import playn.java.JavaPlatform;

public class CloudTetrisJava {

  public static void main(String[] args) {
    JavaPlatform platform = JavaPlatform.register();
    platform.assetManager().setPathPrefix("cloudtetris/resources");
    PlayN.run(new CloudTetris());
  }
}
