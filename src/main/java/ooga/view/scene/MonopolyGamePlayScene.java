package ooga.view.scene;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MonopolyGamePlayScene extends BorderPane {

  public MonopolyGamePlayScene(Stage primaryStage) {
    setupGamePlayScene();
  }

  private void setupGamePlayScene() {
    this.setCenter(); // monopoly board
    this.setRight(); // player status
    this.setTop(); // HUD?
    this.setBottom(); // Action Buttons
  }
}
