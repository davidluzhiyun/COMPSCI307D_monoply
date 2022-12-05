package ooga.view.components;

import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import ooga.Main;
import ooga.view.View;
import ooga.view.scene.SceneManager;

/**
 * To use in board: create GamePiece, add to root, then call the placeAtGo method.
 */
public class GamePiece extends ImageView implements BoardObjects {

  private int myPlayer;

  /**
   * constructor should also probably take in a location parameter for go/where to initially place it ?
   * @param piece: should be like GamePiece0, GamePiece1, etc.
   * @param player: int representing which player this game piece belongs to -- might not need this
   */
  public GamePiece(String piece, int player) {
    ResourceBundle resources = ResourceBundle.getBundle(View.CHOICE_BOX_PROPERTIES);
    Image image = new Image(resources.getString(piece));
    this.setImage(image);
    this.setPreserveRatio(true);
    ResourceBundle resources2 = ResourceBundle.getBundle(Main.DEFAULT_RESOURCE_PACKAGE + "UserInterface");
    int size = Integer.parseInt(resources2.getString("GamePieceSize"));
    this.setFitHeight(size);
    this.myPlayer = player;
  }

  public void placeAtGo(int xLocation, int yLocation) {
    this.setX(xLocation);
    this.setY(yLocation);
  }

  /**
   * This will move the piece forward in whatever direction it is currently facing
   * @param xLocation: subject to change
   * @param yLocation: subject to change
   *                 https://youtu.be/MB97h89xjDw
   */
  @Override
  public void placeObject(int xLocation, int yLocation) {
    TranslateTransition transition = new TranslateTransition();
    transition.setDuration(Duration.seconds(1));
    transition.setNode(this);
    transition.setToX(xLocation);
    transition.setToY(yLocation);
    transition.play();
  }

  @Override
  public void rotateObject(double angleToRotate) {
    // rotate 90 *

  }
}
