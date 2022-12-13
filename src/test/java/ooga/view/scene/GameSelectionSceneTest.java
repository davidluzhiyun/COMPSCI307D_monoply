package ooga.view.scene;

import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ooga.event.GameEventHandler;
import ooga.view.GameView;
import ooga.view.StartView;
import ooga.view.button.GoToEditorButton;
import ooga.view.button.LoadGameButton;
import ooga.view.button.StartButton;
import ooga.view.button.StartNewGameButton;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class GameSelectionSceneTest extends DukeApplicationTest {
  private GameSelectionScene myScene;
  private GoToEditorButton editorButton;
  private LoadGameButton loadButton;
  private StartNewGameButton startNewGameButton;
  private StartButton start;
  @Override
  public void start(Stage stage) throws Exception {
    StartView startView = new StartView(new Stage(), new GameEventHandler());
    start = lookup("#StartButton").query();
    clickOn(start);
//    loadButton = lookup("#LoadGameButton").query();
//    startNewGameButton = lookup("#StartGameButton").query();
  }

  @Test
  void testEditorButton() {
    editorButton = lookup("#GoToGameEditorButton").query();
//    clickOn(editorButton);
//    Pane editor = lookup("#GameEditorScene").query();
//    assertNotNull(editor);
  }

  @Test
  void testStartNewButton() {
    startNewGameButton = lookup("#StartGameButton").query();
//    clickOn(startNewGameButton);
    //must select a file
//    sleep(20000);
//    HBox buttons = lookup("#GameButtons").query();
//    assertNotNull(buttons);
  }
}