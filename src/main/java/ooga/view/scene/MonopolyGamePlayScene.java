package ooga.view.scene;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ooga.view.components.Board;

public class MonopolyGamePlayScene extends BorderPane {

  public MonopolyGamePlayScene(Stage primaryStage) {
    setupGamePlayScene();
  }

  private void setupGamePlayScene() {
    Board test = new Board();
    this.setCenter(test.getBoard()); // monopoly board
//    this.setRight(); // player status
//    this.setTop(); // HUD?
//    this.setBottom(); // Action Buttons
  }
}
