package ooga.view.components;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ooga.view.pop_ups.RollResultPopUp;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class GamePieceTest extends DukeApplicationTest {

  private GamePiece piece;
  private Stage stage;

  @Override
  public void start(Stage stage) throws Exception {
    this.piece = new GamePiece("GamePiece1", 1);
    Group root = new Group(piece);
    this.stage = stage;
    Scene scene = new Scene(root, 600, 600);
    stage.setScene(scene);
    stage.show();
    piece.placeObject(100, 200);
  }

  @Test
  void testMovement() {
    //piece.placeAtGo(20, 100);
    Bounds bound = piece.localToScene(piece.getBoundsInLocal());
    assertEquals(100, bound.getMinX());
    assertEquals(200, bound.getMinY());
  }
}