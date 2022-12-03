package ooga.view.pop_ups;

import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.Main;
import ooga.view.GameView;
import ooga.view.StartView;
import ooga.view.View;
import ooga.view.button.CustomizedButton;
import ooga.view.button.SelectButton;
import ooga.view.components.GamePiece;
import ooga.view.drop_down.CustomizedDropDown;
import ooga.view.drop_down.GamePieceDropDown;

/**
 * A pop-up that displays the current player and gives them the option to select their game piece.
 * Should show the image/preview of what their game piece will look like whenever they change within
 * the drop-down menu. Give them a "confirm" button
 */
public class GamePiecePopUp extends ActionPopUp {

  private int currentPlayer;
  private final Stage myStage;
  private String myLanguage;
  private ResourceBundle myResources;
  private final ResourceBundle popUpResources;
  private String myStyle;
  private VBox root;
  private ImageView icon;
  private String pieceKey;
  private CustomizedButton selectButton;
  private GamePiece myPiece;
  public static final String PREVIEW_TEXT_KEY = "GamePiecePreviewText";
  public static final String ICON_HEIGHT_KEY = "IconHeight";
  public static final String GAME_PIECE_POP_UP_ID = "GamePiecePopUp";

  public GamePiecePopUp(int player, String style, String language) {
    super(language);
    this.currentPlayer = player;
    this.myStage = new Stage();
    this.popUpResources = ResourceBundle.getBundle(View.POP_UP_PROPERTIES);
    this.myStyle = style;
    this.icon = new ImageView();
  }

  @Override
  public void createScene() {
    ResourceBundle idResources = ResourceBundle.getBundle(Main.ID_PROPERTIES);
    Label playerText = new Label(
        String.format(myResources.getString(PLAYER_TEXT_KEY), currentPlayer));
    playerText.setId(idResources.getString("PlayerText"));
    Label previewText = new Label(myResources.getString(PREVIEW_TEXT_KEY));
    previewText.setId(idResources.getString("PreviewText"));
    root = new VBox(playerText,
        (CustomizedDropDown) makeInteractiveObject(GamePieceDropDown.GAME_PIECE_KEY, myLanguage,
            this), previewText);
    root.setId(GAME_PIECE_POP_UP_ID);
    int height = Integer.parseInt(popUpResources.getString(HEIGHT));
    int width = Integer.parseInt(popUpResources.getString(WIDTH));
    selectButton = (CustomizedButton) makeInteractiveObject(SelectButton.SELECT_BUTTON_KEY,
        myLanguage, this);
    Scene scene = new Scene(root, width, height);
    myStage.setScene(scene);
    popUpStyle(scene, myStyle);
  }

  @Override
  public void showMessage(String language) {
    myLanguage = language;
    myResources = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_PACKAGE + language);
    createScene();
    myStage.show();
  }

  @Override
  public void close() {
    myStage.close();
  }

  /**
   * Set within property files to handle changes to GamePieceDropDown by removing the current
   * visible icon and creating a new one
   */
  public void previewPiece(Number newValue) {
    root.getChildren().removeAll(icon, selectButton);
    ResourceBundle choiceResources = ResourceBundle.getBundle(
        Main.DEFAULT_RESOURCE_PACKAGE + StartView.DROP_DOWN);
    pieceKey = String.format(StartView.STRING_INT_FORMATTER, GameView.GAME_PIECE, newValue);
    String imageURL = choiceResources.getString(pieceKey);
    Image image = new Image(imageURL);
    icon = new ImageView(image);
    icon.setPreserveRatio(true);
    icon.setFitHeight(Integer.parseInt(popUpResources.getString(ICON_HEIGHT_KEY)));
    selectButton = (CustomizedButton) makeInteractiveObject(SelectButton.SELECT_BUTTON_KEY,
        myLanguage, this);
    root.getChildren().addAll(icon, selectButton);
  }

  /**
   * Currently set within property file as the method for when the SelectButton is clicked.
   */
  public void saveChanges() {
    myPiece = new GamePiece(pieceKey, 1);
    this.close();
  }

  // For now this is a getter, may want to change this later...
  public GamePiece getGamePiece() {return myPiece;}
}
