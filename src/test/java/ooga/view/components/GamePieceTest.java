package ooga.view.components;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ooga.view.pop_ups.RollResultPopUp;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class GamePieceTest extends DukeApplicationTest {

  private GamePiece piece;

  @Override
  public void start(Stage stage) throws Exception {
    this.piece = new GamePiece("GamePiece1", 1);
    Group root = new Group(piece);
    Scene scene = new Scene(root, 600, 600);
    stage.setScene(scene);
    stage.show();
  }

  @Test
  void testPlaceAtGo() {
    piece.placeAtGo(20, 100);
    assertEquals(20, piece.getX());
    assertEquals(100, piece.getY());
  }

  @Test
  void testMovement() {
    piece.placeObject(200, 300);
  }
}