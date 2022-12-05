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
 * To use in board: create GamePiece, add to root, then call the placeAtGo method.
 */
public class GamePiece extends ImageView implements BoardObjects {

  private int myPlayer;
  private final ResourceBundle myResources;

  /**
   * constructor should also probably take in a location parameter for go/where to initially place
   * it ?
   *
   * @param piece:  should be like GamePiece0, GamePiece1, etc.
   * @param player: int representing which player this game piece belongs to -- might not need this
   */
  public GamePiece(String piece, int player) {
    ResourceBundle resources = ResourceBundle.getBundle(View.CHOICE_BOX_PROPERTIES);
    Image image = new Image(resources.getString(piece));
    this.setImage(image);
    this.setPreserveRatio(true);
    this.myResources = ResourceBundle.getBundle(
        Main.DEFAULT_RESOURCE_PACKAGE + "UserInterface");
    int size = Integer.parseInt(myResources.getString("GamePieceSize"));
    this.setFitHeight(size);
    this.myPlayer = player;
  }

  public void placeAtGo(int xLocation, int yLocation) {
    this.setX(xLocation);
    this.setY(yLocation);
  }

  /**
   * This will move the piece to the given coordinates. Note: referenced this video for help with
   * JavaFX specifics: https://youtu.be/MB97h89xjDw
   *
   * @param xLocation: int, x-coordinate of target location
   * @param yLocation: int, y-coordinate of target location
   */
  @Override
  public void placeObject(int xLocation, int yLocation) {
    TranslateTransition transition = new TranslateTransition();
    double duration = Double.parseDouble(myResources.getString("MovementDuration"));
    transition.setDuration(Duration.millis(duration));
    transition.setNode(this);
    transition.setToX(xLocation);
    transition.setToY(yLocation);
    transition.play();
  }

  @Override
  public void rotateObject(double angleToRotate) {
    RotateTransition transition = new RotateTransition();
    double duration = Double.parseDouble(myResources.getString("RotateDuration"));
    transition.setDuration(Duration.millis(duration));
    transition.setNode(this);
    transition.setByAngle(angleToRotate);
    transition.play();
  }
}
