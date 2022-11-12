package ooga.view;

import javafx.scene.Scene;
import ooga.Main;

public abstract class View {

  public static final String DEFAULT_STYLE_PACKAGE = "ooga/style.";
  public static final String STYLESHEETS = "/" + DEFAULT_STYLE_PACKAGE.replace(".", "/");
  public static final String BUTTON_PROPERTIES = Main.DEFAULT_RESOURCE_PACKAGE + "Button";


  public abstract InteractiveObject makeInteractiveObject(String name);

  public void styleScene(Scene scene, String file) {
    scene.getStylesheets()
        .add(View.class.getResource(STYLESHEETS + file).toExternalForm());
//    myStage.setScene(scene);
  }
}
