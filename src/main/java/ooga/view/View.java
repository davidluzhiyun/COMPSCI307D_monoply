package ooga.view;

import javafx.scene.Node;
import javafx.scene.Scene;
import ooga.Main;

public abstract class View {

  public static final String DEFAULT_STYLE_PACKAGE = "style.";
  public static final String STYLESHEETS = "/" + DEFAULT_STYLE_PACKAGE.replace(".", "/");
  public static final String BUTTON_PROPERTIES = Main.DEFAULT_RESOURCE_PACKAGE + "Button";
  public static final String CHOICE_BOX_PROPERTIES = Main.DEFAULT_RESOURCE_PACKAGE + "DropDown";
  public static final String POP_UP_PROPERTIES = Main.DEFAULT_RESOURCE_PACKAGE + "PopUp";

  public abstract InteractiveObject makeInteractiveObject(String name);
  public abstract void changeStyle(Number newValue);

  public void styleScene(Scene scene, String file) {
    String fileName = String.format("%s.css", file);
    scene.getStylesheets()
        .add(View.class.getResource(STYLESHEETS + fileName).toExternalForm());
  }

  /**
   * Used just for aesthetic purposes to place an object in the center of the given width.
   *
   * @param node:  object to be centered
   * @param width: length that you would like object to be in the middle of
   */
  public void centerHorizontally(Node node, double width) {
    double xCoordinate = width / 2 - node.getBoundsInParent().getWidth() / 2;
    node.setLayoutX(xCoordinate);
  }

  /**
   * Again, used for aesthetic purposes. Places an object in the center of a given height.
   *
   * @param node:   object to be centered
   * @param height: height to center the object in
   */
  public void centerVertically(Node node, double height) {
    double yCoordinate = height / 2 - node.getBoundsInParent().getWidth() / 2;
    node.setLayoutY(yCoordinate);

  }
}
