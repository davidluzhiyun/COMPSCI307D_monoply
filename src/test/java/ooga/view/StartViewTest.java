package ooga.view;

import static org.junit.jupiter.api.Assertions.*;

import javafx.stage.Stage;
import ooga.view.button.FileUploadButton;
import ooga.view.button.StartButton;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class StartViewTest extends DukeApplicationTest {

  private FileUploadButton myFileButton;
  private StartButton myStartButton;
  private StartView myStart;

  @Override
  public void start(Stage stage) throws Exception {
    myStart = new StartView(stage);
    myStartButton = lookup("#StartButton").query();
    myFileButton = lookup("#FileUploadButton").query();
  }

  /**
   * NOTE: DURING THE PAUSE, YOU MUST SELECT A FILE -- otherwise the test will fail
   * Verify that the printed file path matches your selection.
   */
  @Test
  void testFileUploadBasic() {
    clickOn(myFileButton);
    sleep(3000);
    System.out.println(myStart.getMyConfigFile());
    assertNotNull(myStart.getMyConfigFile());
  }
}