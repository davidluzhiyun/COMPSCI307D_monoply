package ooga.view.components;

import java.util.ResourceBundle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ooga.Main;
import ooga.view.View;

public class GamePiece {

  private ImageView myIcon;
  private int myPlayer;

  /**
   * constructor should also probably take in a location parameter for go/where to initially place it ?
   * @param piece: should be like GamePiece0, GamePiece1, etc.
   * @param player: int representing which player this game piece belongs to
   */
  public GamePiece(String piece, int player) {
    ResourceBundle resources = ResourceBundle.getBundle(View.CHOICE_BOX_PROPERTIES);
    Image image = new Image(resources.getString(piece));
    myIcon = new ImageView(image);
    this.myPlayer = player;
  }

  public void movePiece(int location) {
    // not too sure how I'm going to do this yet...
  }

}
