package ooga.view.pop_ups;

import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.Main;
import ooga.view.View;
import ooga.view.drop_down.GamePieceDropDown;

/**
 * A pop-up that displays the current player and gives them the option to select their game piece.
 * Should show the image/preview of what their game piece will look like whenever they change
 * within the drop-down menu. Give them a "confirm" button
 */
public class GamePiecePopUp extends ActionPopUp {
  private int currentPlayer;
  private final Stage myStage;
  private String myLanguage;
  private ResourceBundle myResources;
  private final ResourceBundle popUpResources;
  private String myStyle;

  public GamePiecePopUp(int player, String style) {
    this.currentPlayer = player;
    this.myStage = new Stage();
    this.popUpResources = ResourceBundle.getBundle(View.POP_UP_PROPERTIES);
    this.myStyle = style;
  }

  @Override
  public void showMessage(String language) {
    myLanguage = language;
    myResources = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_PACKAGE + language);
    createScene();
    myStage.show();
  }

  @Override
  public void createScene() {
    Text playerText = new Text(String.format(myResources.getString(PLAYER_TEXT_KEY), currentPlayer));
    GamePieceDropDown dropDown = new GamePieceDropDown(myLanguage);
    VBox root = new VBox(playerText, dropDown, makeIconPreview());
    int height = Integer.parseInt(popUpResources.getString(HEIGHT));
    int width = Integer.parseInt(popUpResources.getString(WIDTH));
    Scene scene = new Scene(root, width, height);
    myStage.setScene(scene);
    popUpStyle(scene, myStyle);
  }

  private VBox makeIconPreview() {
//    Image image = new Image(DICE_IMAGE);
//    ImageView diceImage = new ImageView(image);
//    diceImage.setPreserveRatio(true);
//    int diceWidth = Integer.parseInt(popUpResources.getString(DICE_WIDTH));
//    diceImage.setFitWidth(diceWidth);
    Text previewText = new Text(myResources.getString("GamePiecePreviewText"));
    ImageView icon = new ImageView();
    return new VBox(icon);
  }
  @Override
  public void close() {
    myStage.close();
  }

}
