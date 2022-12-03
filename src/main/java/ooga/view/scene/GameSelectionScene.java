package ooga.view.scene;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ooga.view.View;

/**
 * Allows users to
 */
public class GameSelectionScene extends View {

  private Group myRoot;
  private String myLanguage;
  private Stage myStage;

  public GameSelectionScene(String language, Stage stage) {
    this.myRoot = new Group();
    this.myLanguage = language;
    this.myStage = stage;
  }

  public Scene createScene(double width, double height) {
    return new Scene(myRoot, width, height);
  }

}
