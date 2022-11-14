package ooga.view;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ResourceBundle;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import ooga.Main;
import ooga.view.button.FileUploadButton;
import ooga.view.button.StartButton;
import ooga.view.drop_down.LanguageDropDown;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class StartViewTest extends DukeApplicationTest {

  private FileUploadButton myFileButton;
  private StartButton myStartButton;

  private LanguageDropDown myLanguageDropDown;

  private StartView myStart;

  @Override
  public void start(Stage stage) throws Exception {
    myStart = new StartView(stage);
    myStartButton = lookup("#StartButton").query();
    myFileButton = lookup("#FileUploadButton").query();
    myLanguageDropDown = lookup("#LanguageChoice").query();
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

  @Test
  void testLanguageChangeGerman() {testLanguages("German", "Deutsch");}
  @Test
  void testLanguageChangeSpanish() {
    ResourceBundle resources = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_PACKAGE + "Spanish");
    String esp = resources.getString("Spanish");
    testLanguages("Spanish", esp);
  }
  @Test
  void testLanguageChangeIndonesian() {testLanguages("Indonesian", "Bahasa Indonesia");}

  @Test
  void testLanguageChangeEnglish() {testLanguages("English", "English");}
  private void testLanguages(String language, String select) {
    ResourceBundle resources = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_PACKAGE + language);
    runAsJFXAction(() -> select(myLanguageDropDown.getChoiceBox(), select));
    myStartButton = lookup("#StartButton").query();
    String newLabel = myStartButton.getText();
    String expected = resources.getString("StartButton");
    assertEquals(newLabel, expected);
  }

}