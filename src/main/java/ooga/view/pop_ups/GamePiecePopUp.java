package ooga.view.pop_ups;

import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.Main;
import ooga.Reflection;
import ooga.view.GameView;
import ooga.view.InteractiveObject;
import ooga.view.StartView;
import ooga.view.View;
import ooga.view.button.CustomizedButton;
import ooga.view.button.SelectButton;
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
  private String imageURL;
  public static final String PREVIEW_TEXT_KEY = "GamePiecePreviewText";
  public static final String ICON_HEIGHT_KEY = "IconHeight";

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
    Text playerText = new Text(
        String.format(myResources.getString(PLAYER_TEXT_KEY), currentPlayer));
    Text previewText = new Text(myResources.getString(PREVIEW_TEXT_KEY));
    root = new VBox(playerText,
        (CustomizedDropDown) makeInteractiveObject(GamePieceDropDown.GAME_PIECE_KEY), previewText);
    int height = Integer.parseInt(popUpResources.getString(HEIGHT));
    int width = Integer.parseInt(popUpResources.getString(WIDTH));
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
    root.getChildren().remove(icon);
    ResourceBundle choiceResources = ResourceBundle.getBundle(
        Main.DEFAULT_RESOURCE_PACKAGE + StartView.DROP_DOWN);
    imageURL = choiceResources.getString(
        String.format(StartView.STRING_INT_FORMATTER, GameView.GAME_PIECE, newValue));
    Image image = new Image(imageURL);
    icon = new ImageView(image);
    icon.setPreserveRatio(true);
    icon.setFitHeight(Integer.parseInt(popUpResources.getString(ICON_HEIGHT_KEY)));
    root.getChildren().addAll(icon, (CustomizedButton) makeInteractiveObject(
        SelectButton.SELECT_BUTTON_KEY));
  }

  /**
   * Currently set within property file as the method for when the SelectButton is clicked.
   * TODO: actually use this information to generate a new game piece for the player.
   * (Perhaps we can have a map of player to player image
   */
  public void saveChanges() {
    System.out.println(imageURL);
    this.close();
  }

  // TODO: refactor so that this code is not more or less repeated across so many classes
  @Override
  public InteractiveObject makeInteractiveObject(String name) {
    Reflection reflection = new Reflection();
    ResourceBundle resources = ResourceBundle.getBundle(View.BUTTON_PROPERTIES);
    String className = resources.getString(name);
    InteractiveObject object = (InteractiveObject) reflection.makeObject(className,
        new Class[]{String.class},
        new Object[]{myLanguage});
    String method = resources.getString(
        String.format(StartView.STRING_FORMATTER, name, StartView.METHOD));
    if (name.contains(StartView.DROP_DOWN)) {
      object.setAction(
          reflection.makeMethod(method, GamePiecePopUp.class, new Class[]{Number.class}),
          this);
    } else {
      object.setAction(reflection.makeMethod(method, GamePiecePopUp.class, null), this);
    }
    return object;
  }

}
