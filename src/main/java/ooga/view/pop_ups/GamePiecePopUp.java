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
import ooga.view.drop_down.CustomizedDropDown;
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
  private VBox root;
  private ImageView icon;
  public static final String PREVIEW_TEXT_KEY = "GamePiecePreviewText";
  public static final String ICON_HEIGHT_KEY = "IconHeight";

  public GamePiecePopUp(int player, String style, String language) {
    super(language);
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
    Text previewText = new Text(myResources.getString(PREVIEW_TEXT_KEY));
    root = new VBox(playerText, (CustomizedDropDown) makeInteractiveObject(GamePieceDropDown.GAME_PIECE_KEY), previewText);
    int height = Integer.parseInt(popUpResources.getString(HEIGHT));
    int width = Integer.parseInt(popUpResources.getString(WIDTH));
    Scene scene = new Scene(root, width, height);
    myStage.setScene(scene);
    popUpStyle(scene, myStyle);
  }

  @Override
  public void close() {
    myStage.close();
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
      object.setAction(reflection.makeMethod(method, GamePiecePopUp.class, new Class[]{Number.class}),
          this);
    } else {
      object.setAction(reflection.makeMethod(method, GamePiecePopUp.class, null), this);
    }
    return object;
  }

  /**
   * Set within property files to handle changes to GamePieceDropDown
   */
  public void previewPiece(Number newValue) {
    root.getChildren().remove(icon);
    ResourceBundle choiceResources = ResourceBundle.getBundle(
        Main.DEFAULT_RESOURCE_PACKAGE + StartView.DROP_DOWN);
    String gamePiece = choiceResources.getString(
        String.format(StartView.STRING_INT_FORMATTER, GameView.GAME_PIECE, newValue));
    Image image = new Image(gamePiece);
    icon = new ImageView(image);
    icon.setPreserveRatio(true);
    icon.setFitHeight(Integer.parseInt(popUpResources.getString(ICON_HEIGHT_KEY)));
    root.getChildren().add(icon);
  }
}
