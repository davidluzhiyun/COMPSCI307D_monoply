package ooga.view.scene;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ooga.view.View;

public class GameSelectionScene extends View {

  private Group myRoot;
  private String myLanguage;
  private Stage myStage;

  // might not even need stage parameter
  public GameSelectionScene(String language, Stage stage) {
    this.myRoot = new Group();
    this.myLanguage = language;
    this.myStage = stage;
  }

  public Scene createScene(double width, double height) {
    return new Scene(myRoot, width, height);
  }

}
