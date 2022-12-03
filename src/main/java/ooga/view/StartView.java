package ooga.view;

import java.io.File;
import java.util.ResourceBundle;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ooga.Main;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.command.Command;
import ooga.event.command.GoToGameSelectionCommand;
import ooga.view.scene.SceneManager;

/**
 * @author Leila. Responsible for the creation of the start screen, where users can select a
 * language, style, and upload a config file.
 */
public class StartView extends View {

  public static final String DEFAULT_LANGUAGE_KEY = "DefaultLanguage";
  public static final String DEFAULT_STYLE_KEY = "DefaultStyle";
  public static final String WIDTH_KEY = "Width";
  public static final String HEIGHT_KEY = "Height";
  public static final String START_OBJECTS_KEY = "StartObjects";
  public static final String STRING_FORMATTER = "%s%s";
  public static final String STRING_INT_FORMATTER = "%s%d";
  public static final String LANGUAGE = "Language";
  public static final String STYLE = "Style";
  public static final String METHOD = "Method";
  public static final String JSON_FILE_EXTENSION = "JSON Files";
  public static final String DATA_FILE_JSON_EXTENSION = "*.json";
  public static final String DROP_DOWN = "DropDown";
  public static final String DATA_FILE_FOLDER = System.getProperty("user.dir") + "/data";
  public static final String LAYOUT_ID = "MainVBox";
  public static final String BACKGROUND = "Background";
  private Group myRoot;
  private final Stage myStage;
  private VBox layout;
  private final int width;
  private final int height;
  private String myLanguage;
  private String myStyle;
  private final ResourceBundle myScreenResources;
  private final GameEventHandler gameEventHandler;

  public StartView(Stage stage, GameEventHandler gameEventHandler) {
    this.gameEventHandler = gameEventHandler;
    myScreenResources = ResourceBundle.getBundle(Main.DEFAULT_RESOURCE_PACKAGE + SCREEN);
    myLanguage = myScreenResources.getString(DEFAULT_LANGUAGE_KEY);
    myStyle = myScreenResources.getString(DEFAULT_STYLE_KEY);
    width = Integer.parseInt(myScreenResources.getString(WIDTH_KEY));
    height = Integer.parseInt(myScreenResources.getString(HEIGHT_KEY));
    this.myStage = stage;
    setUpScene();
  }

  private void placeItems() {
    centerHorizontally(layout, width);
    centerVertically(layout, height);
  }

  private void setUpScene() {
    Rectangle background = new Rectangle(width, height);
    background.setId(BACKGROUND);
    myRoot = new Group();
    layout = new VBox();
    layout.setId(LAYOUT_ID);
    myRoot.getChildren().addAll(background, layout);
    makeInteractiveObjects();
    Scene myScene = new Scene(myRoot, width, height);
    styleScene(myScene, myStyle);
    myStage.setScene(myScene);
    myStage.show();
    placeItems();
  }

  private void makeInteractiveObjects() {
    String[] names = myScreenResources.getString(START_OBJECTS_KEY).split(SPACE_REGEX);
    for (String name : names) {
      layout.getChildren().add((Node) makeInteractiveObject(name, myLanguage, this));
    }
  }

  /**
   * Set in property files to be the handler method when a suer clicks on a FileUploadButton. Starts
   * up a FileChooser dialog to let the user select a file from their computer & restricts them to
   * choosing only JSON files (since that is our designated file format for config files). Saves
   * this file to instance variable myConfigFile.
   */
//  public void fileHandler() {
//    FileChooser fileChooser = new FileChooser();
//    fileChooser.setInitialDirectory(new File(DATA_FILE_FOLDER));
//    fileChooser.getExtensionFilters()
//        .setAll(new FileChooser.ExtensionFilter(JSON_FILE_EXTENSION,
//            DATA_FILE_JSON_EXTENSION));
//    myConfigFile = fileChooser.showOpenDialog(myStage);
//  }

  /**
   * Set in property files to be the method called whenever a user changes their selection in the
   * LanguageDropDown
   *
   * @param newValue: Number that represents which option the user has picked
   */
  public void changeLanguage(Number newValue) {
    ResourceBundle choiceResources = ResourceBundle.getBundle(
        Main.DEFAULT_RESOURCE_PACKAGE + DROP_DOWN);
    myLanguage = choiceResources.getString(String.format(STRING_INT_FORMATTER, LANGUAGE, newValue));
    myRoot.getChildren().remove(layout);
    myStage.close();
    setUpScene();
    placeItems();
  }

  /**
   * Set within property files to be called whenever a user selects something within the style
   * choice box. Changes the CSS stylesheet by resetting the stage.
   *
   * @param newValue: index of the choice selected within the choice box; used to determine which
   */
  public void changeStyle(Number newValue) {
    ResourceBundle choiceResources = ResourceBundle.getBundle(
        Main.DEFAULT_RESOURCE_PACKAGE + DROP_DOWN);
    myStyle = choiceResources.getString(String.format(STRING_INT_FORMATTER, STYLE, newValue));
    myRoot.getChildren().remove(layout);
    myStage.close();
    setUpScene();
    placeItems();
  }

  /**
   * Should
   */
  public void startButtonHandler() {
    // this code should be moved to the next screen
//      Command cmd = new GameStartViewCommand();
//      GameEvent event = GameEventHandler.makeGameEventwithCommand("VIEW_LAUNCH_GAME_SCREEN",
//          cmd);
//      gameEventHandler.publish(event);
    Command cmd = new GoToGameSelectionCommand();
    GameEvent event = GameEventHandler.makeGameEventwithCommand("VIEW_LAUNCH_GAME_SELECTION_SCREEN", cmd);
    SceneManager sceneManager = new SceneManager(new Stage(), myLanguage, gameEventHandler);
    gameEventHandler.addEventListener(sceneManager);
    gameEventHandler.publish(event);
  }
}
