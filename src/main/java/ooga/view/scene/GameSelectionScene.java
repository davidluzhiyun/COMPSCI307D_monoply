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
  private Stage myStage;
  private final ResourceBundle myLanguageResources;

  public GameSelectionScene(String language, Stage stage) {
    this.myLanguageResources = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_PACKAGE + language);
    this.myRoot = new Group();
    this.myLanguage = language;
    this.myStage = stage;
  }

  public Scene createScene(double width, double height) {
    System.out.println("HELLO>>>");
    createButtons();
    return new Scene(myRoot, width, height);
  }

  private void createButtons() {
    System.out.println("hello??");
    VBox buttons = new VBox();
    ResourceBundle screenResources = ResourceBundle.getBundle(Main.DEFAULT_RESOURCE_PACKAGE + SCREEN);
    String[] objects = screenResources.getString("GameSelectionObjects").split(COMMA_REGEX);
    for (String button : objects) {
      buttons.getChildren().add((Node) makeInteractiveObject(button, myLanguage, this));
    }
    myRoot.getChildren().add(buttons);
  }
  public void startNewGame() {

  }
  public void loadGame() {

  }
  public void goToEditor(){

  }

}
