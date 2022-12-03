package ooga.view.scene;

import java.util.ResourceBundle;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ooga.Main;
import ooga.view.InteractiveObject;
import ooga.view.View;

/**
 * Allows users to
 */
public class GameSelectionScene extends View {

  private final Group myRoot;
  private String myLanguage;
  private String myStyle;
  private VBox buttons;
  public static final String GAME_SELECTION_OBJECTS_KEY = "GameSelectionObjects";
  public GameSelectionScene(String language, Stage stage, String style) {
    this.myRoot = new Group();
    this.myLanguage = language;
    this.myStyle = style;
  }

  public Scene createScene(double width, double height) {
    createButtons();
    Scene scene = new Scene(myRoot, width, height);
    styleScene(scene, myStyle);
    return scene;
  }

  private void createButtons() {
    buttons = new VBox();
    ResourceBundle idResources = ResourceBundle.getBundle(Main.ID_PROPERTIES);
    buttons.setId(idResources.getString(GAME_SELECTION_OBJECTS_KEY));
    ResourceBundle screenResources = ResourceBundle.getBundle(Main.DEFAULT_RESOURCE_PACKAGE + SCREEN);
    String[] objects = screenResources.getString(GAME_SELECTION_OBJECTS_KEY).split(COMMA_REGEX);
    for (String button : objects) {
      buttons.getChildren().add((Node) makeInteractiveObject(button, myLanguage, this));
    }
    myRoot.getChildren().add(buttons);
  }

  public VBox getButtons() {return buttons;}
  public void startNewGame() {

  }
  public void loadGame() {

  }
  public void goToEditor(){

  }

}
