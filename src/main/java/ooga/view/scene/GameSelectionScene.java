package ooga.view.scene;

import java.io.File;
import java.util.ResourceBundle;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ooga.Main;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.command.Command;
import ooga.event.command.GameEditorCommand;
import ooga.event.command.GameStartViewCommand;
import ooga.view.StartView;
import ooga.view.View;
import ooga.view.pop_ups.NoFileErrorPopUp;

/**
 * Allows users to
 */
public class GameSelectionScene extends View {

  private final Group myRoot;
  private String myLanguage;
  private VBox buttons;
  private Scene scene;
  private ResourceBundle idResources;
  private final Stage myStage;
  private final GameEventHandler myGameEventHandler;
  public static final String GAME_SELECTION_OBJECTS_KEY = "GameSelectionObjects";
  public static final String JSON_FILE_EXTENSION = "JSON Files";
  public static final String DATA_FILE_JSON_EXTENSION = "*.json";

  public GameSelectionScene(String language, Stage stage, GameEventHandler handler) {
    this.myRoot = new Group();
    this.myStage = stage;
    this.myLanguage = language;
    this.myGameEventHandler = handler;
  }

  public Scene createScene(double width, double height) {
    Rectangle background = new Rectangle(width, height);
    this.idResources = ResourceBundle.getBundle(Main.ID_PROPERTIES);
    background.setId(idResources.getString(StartView.BACKGROUND));
    myRoot.getChildren().add(background);
    createButtons();
    scene = new Scene(myRoot, width, height);
    return scene;
  }

  public void setStyle(String style) {styleScene(scene, style);}

  private void createButtons() {
    buttons = new VBox();
    buttons.setId(idResources.getString(GAME_SELECTION_OBJECTS_KEY));
    ResourceBundle screenResources = ResourceBundle.getBundle(
        Main.DEFAULT_RESOURCE_PACKAGE + SCREEN);
    String[] objects = screenResources.getString(GAME_SELECTION_OBJECTS_KEY).split(COMMA_REGEX);
    for (String button : objects) {
      buttons.getChildren().add((Node) makeInteractiveObject(button, myLanguage, this));
    }
    myRoot.getChildren().add(buttons);
  }

  /**
   * Centers the VBox so the buttons appear more in the middle of the scene
   *
   * @param width:  width of the scene
   * @param height: height of the scene
   */
  public void placeButtons(double width, double height) {
    centerVertically(buttons, height);
    centerHorizontally(buttons, width);
  }

  /**
   * Set within property files to be the method invoked when users click the StartNewGameButton.
   * Loads a file chooser that opens to the directory for configuration files.
   * NOTE: should eventually publish some event to the controller...
   */
  public void startNewGame() {
    makeFileDialog(Main.CONFIG_FILES_DIRECTORY);
  }

  /**
   * Set within property files to be the method invoked when users click the LoadGameButton.
   * This opens a different directory for JSON files that represent paused games.
   * NOTE: should eventually also publish some event to the controller...
   */
  public void loadGame() {
   makeFileDialog(Main.PAUSED_GAME_DIRECTORY);
  }

  private void makeFileDialog(String initialDirectory) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setInitialDirectory(new File(initialDirectory));
    fileChooser.getExtensionFilters().setAll(new FileChooser.ExtensionFilter(JSON_FILE_EXTENSION,
        DATA_FILE_JSON_EXTENSION));
    File configFile = fileChooser.showOpenDialog(myStage);
    if (configFile == null) {
      NoFileErrorPopUp pop = new NoFileErrorPopUp();
      pop.showMessage(myLanguage);
    } else {
      Command cmd = new GameStartViewCommand(configFile);
      GameEvent event = GameEventHandler.makeGameEventwithCommand("VIEW_LAUNCH_GAME_SCREEN",
          cmd);
      myGameEventHandler.publish(event);
    }
  }

  /**
   * Set within property files to be the method invoked when users click the GoToEditorButton.
   */
  public void goToEditor() {
    Command cmd = new GameEditorCommand();
    GameEvent event = GameEventHandler.makeGameEventwithCommand("VIEW_LAUNCH_BOARD_EDITOR",
        cmd);
    myGameEventHandler.publish(event);
  }

}
