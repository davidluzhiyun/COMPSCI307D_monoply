package ooga.view.components;

import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import ooga.Main;
import ooga.view.View;
import ooga.view.scene.SceneManager;

/**
 *
 */
public class GamePiece extends ImageView implements BoardObject {

  private int myPlayer;
  private final ResourceBundle myResources;
  public static final String PIECE_SIZE_KEY = "GamePieceSize";
  public static final String MOVEMENT_DURATION_KEY = "MovementDuration";
  public static final String ROTATION_DURATION_KEY = "RotateDuration";

  /**
   * @param piece:  should be like GamePiece0, GamePiece1, etc.
   * @param player: int representing which player this game piece belongs to -- might not need this
   */
  public GamePiece(String piece, int player) {
    ResourceBundle resources = ResourceBundle.getBundle(View.CHOICE_BOX_PROPERTIES);
    Image image = new Image(resources.getString(piece));
    this.setImage(image);
    this.setPreserveRatio(true);
    this.myResources = ResourceBundle.getBundle(
        Main.DEFAULT_RESOURCE_PACKAGE + SceneManager.USER_INTERFACE);
    int size = Integer.parseInt(myResources.getString(PIECE_SIZE_KEY));
    this.setFitHeight(size);
    this.myPlayer = player;
  }

  /**
   * Technically can be used to place the object anywhere, since our game is designed to support
   * flexibility in the location of the GO piece.
   *
   * @param xLocation: int, x-coordinate that the piece should be placed at
   * @param yLocation: int, y-coordinate that the piece should be placed at
   */
  public void placeAtGo(int xLocation, int yLocation) {
    this.setX(xLocation);
    this.setY(yLocation);
  }

  /**
   * This will move the piece to the given coordinates. Note: referenced this video for help with
   * JavaFX specifics: https://youtu.be/MB97h89xjDw.
   *
   * @param xLocation: int, x-coordinate of target location
   * @param yLocation: int, y-coordinate of target location
   */
  @Override
  public void placeObject(int xLocation, int yLocation) {
    TranslateTransition transition = new TranslateTransition();
    double duration = Double.parseDouble(myResources.getString(MOVEMENT_DURATION_KEY));
    transition.setDuration(Duration.millis(duration));
    transition.setNode(this);
    transition.setToX(xLocation);
    transition.setToY(yLocation);
    transition.play();
  }

  /**
   * Rotates the piece. Note: technically in a square board, the rotation will always be 90 degrees
   * -- however, we have made this more flexible to support different board shapes in the future.
   *
   * @param angleToRotate: double, degrees to rotate the piece by
   */
  @Override
  public void rotateObject(double angleToRotate) {
    RotateTransition transition = new RotateTransition();
    double duration = Double.parseDouble(myResources.getString(ROTATION_DURATION_KEY));
    transition.setDuration(Duration.millis(duration));
    transition.setNode(this);
    transition.setByAngle(angleToRotate);
    transition.play();
  }
}
