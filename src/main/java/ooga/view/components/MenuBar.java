package ooga.view.components;

import static ooga.event.GameEventType.VIEW_LAUNCH_BOARD_EDITOR;
import static ooga.event.GameEventType.VIEW_POST_ACTION_DRAW_BOARD;
import static ooga.view.scene.GameSelectionScene.DATA_FILE_JSON_EXTENSION;
import static ooga.view.scene.GameSelectionScene.JSON_FILE_EXTENSION;

import java.io.File;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ooga.Main;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.command.Command;
import ooga.event.command.ConcreteCommand;
import ooga.view.View;
import ooga.view.api.ChildView;
import ooga.view.button.CustomizedButton;
import ooga.view.scene.MonopolyGameEditorScene;
import ooga.view.scene.SceneManager;

public class MenuBar extends View implements ChildView {

  private String myLanguage;
  private HBox buttonBar;
  private Stage primaryStage;
  public static final String MENU_BAR_BUTTONS = "MenuBarButtons";
  private GameEventHandler gameEventHandler;
  private MonopolyGameEditorScene scene;

  public MenuBar(String language, Stage primaryStage, GameEventHandler gameEventHandler,
      MonopolyGameEditorScene scene) {
    ResourceBundle resources = ResourceBundle.getBundle(
        Main.DEFAULT_RESOURCE_PACKAGE + SceneManager.USER_INTERFACE);
    String[] buttons = resources.getString(MENU_BAR_BUTTONS).split(COMMA_REGEX);
    this.myLanguage = language;
    this.primaryStage = primaryStage;
    this.gameEventHandler = gameEventHandler;
    this.scene = scene;
    makeButtons(buttons);
  }

  private void makeButtons(String[] buttons) {
    this.buttonBar = new HBox();
    for (String button : buttons) {
      buttonBar.getChildren()
          .add((CustomizedButton) makeInteractiveObject(button, myLanguage, this));
    }
  }

  public void saveFile() {

  }

  public void changeDimensions() {
  }

  public void fileHandler() {
    File f = makeFileDialogForEditor();
    Command cmd = new ConcreteCommand<File>(f);
    GameEvent event = GameEventHandler.makeGameEventwithCommand("VIEW_LAUNCH_BOARD_EDITOR",
        cmd);
    gameEventHandler.removeEventListener(scene);
    gameEventHandler.publish(event);
  }

  private File makeFileDialogForEditor() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setInitialDirectory(new File(Main.CONFIG_FILES_DIRECTORY));
    fileChooser.getExtensionFilters().setAll(new FileChooser.ExtensionFilter(JSON_FILE_EXTENSION,
        DATA_FILE_JSON_EXTENSION));
    File configFile = fileChooser.showOpenDialog(primaryStage);

    return configFile;
  }

  @Override
  public Node getView() {
    return buttonBar;
  }
}
