package ooga.view;

import static org.junit.jupiter.api.Assertions.*;

import javafx.stage.Stage;
import ooga.event.GameEventHandler;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.io.File;

class GameViewTest extends DukeApplicationTest {
  private GameView myView;
  @Override
  public void start(Stage stage) throws Exception {
    myView = new GameView(new GameEventHandler(), "English", new Stage(), new File(""));
    myView.setUpScene(1280,800, "Light");
  }

  @Test
  void hello() {
    sleep(1000);
  }
}