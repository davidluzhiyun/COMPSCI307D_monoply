package ooga.view;

import javafx.scene.Scene;

public abstract class View {

  public static final String DEFAULT_STYLE_PACKAGE = "ooga/style.";
  public static final String STYLESHEETS = "/" + DEFAULT_STYLE_PACKAGE.replace(".", "/");

  public abstract Object makeButtonObject();

  public void styleScene(Scene scene, String file) {
    scene.getStylesheets()
        .add(View.class.getResource(STYLESHEETS + file).toExternalForm());
//    myStage.setScene(scene);
  }
}
