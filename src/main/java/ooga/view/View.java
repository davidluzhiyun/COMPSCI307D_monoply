package ooga.view;

import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.Scene;
import ooga.Main;
import ooga.Reflection;

public abstract class View {

  public static final String DEFAULT_STYLE_PACKAGE = "style.";
  public static final String STYLESHEETS = "/" + DEFAULT_STYLE_PACKAGE.replace(".", "/");
  public static final String BUTTON_PROPERTIES = Main.DEFAULT_RESOURCE_PACKAGE + "Button";
  public static final String CHOICE_BOX_PROPERTIES = Main.DEFAULT_RESOURCE_PACKAGE + "DropDown";
  public static final String POP_UP_PROPERTIES = Main.DEFAULT_RESOURCE_PACKAGE + "PopUp";
  public static final String SCREEN = "Screen";
  public static final String SPACE_REGEX = " ";
  public static final String COMMA_REGEX = ", ";


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


  /**
   * Creates new object of type InteractiveObject & also uses its setAction method to invoke the
   * desired method (which is specified in property files!).
   *
   * @param name:     name of the class you would like to create
   * @param language: String, language that you want the object labeled in
   * @param view:     type of View subclass that contains the desired handler method for the object
   * @return the new object
   */
  public InteractiveObject makeInteractiveObject(String name, String language, View view) {
    Reflection reflection = new Reflection();
    ResourceBundle resources = ResourceBundle.getBundle(View.BUTTON_PROPERTIES);
    String className = resources.getString(name);
    InteractiveObject object = (InteractiveObject) reflection.makeObject(className,
        new Class[]{String.class},
        new Object[]{language});
    String method = resources.getString(
        String.format(StartView.STRING_FORMATTER, name, StartView.METHOD));
    if (name.contains(StartView.DROP_DOWN)) {
      object.setAction(reflection.makeMethod(method, view.getClass(), new Class[]{Number.class}),
          view);
    } else {
      object.setAction(reflection.makeMethod(method, view.getClass(), null), view);
    }
    return object;
  }

}
