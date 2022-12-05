package ooga.view.components;

import java.util.ResourceBundle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ooga.view.View;

public class GamePiece extends ImageView implements BoardObjects {

  private int myPlayer;

  /**
   * constructor should also probably take in a location parameter for go/where to initially place it ?
   * @param piece: should be like GamePiece0, GamePiece1, etc.
   * @param player: int representing which player this game piece belongs to
   */
  public GamePiece(String piece, int player) {
    ResourceBundle resources = ResourceBundle.getBundle(View.CHOICE_BOX_PROPERTIES);
    Image image = new Image(resources.getString(piece));
    this.setImage(image);
    this.myPlayer = player;
    // should place at Go. might need to take that in as a parameter in the constructor
  }

  /**
   * This is not fully implemented just yet, need to first figure out how we want to decide where
   * the piece is going to go... eventually will use transition animations or something of the sort
   * @param xLocation: subject to change
   * @param yLocation: subject to change
   */
  @Override
  public void moveObject(int xLocation, int yLocation) {
    this.setX(xLocation);
    this.setY(yLocation);
  }

  @Override
  public void rotateObject(boolean isClockwise) {
    // rotate 90 *

  }
}
