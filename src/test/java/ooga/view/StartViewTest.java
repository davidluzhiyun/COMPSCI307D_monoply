package ooga.view;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ooga.Main;
import ooga.event.GameEventHandler;
import ooga.view.button.FileUploadButton;
import ooga.view.button.StartButton;
import ooga.view.drop_down.LanguageDropDown;
import ooga.view.drop_down.StyleDropDown;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class StartViewTest extends DukeApplicationTest {

  private StartButton myStartButton;

  private LanguageDropDown myLanguageDropDown;
  private StyleDropDown myStyleDropDown;

  private StartView myStart;

  @Override
  public void start(Stage stage) throws Exception {
    myStart = new StartView(stage, new GameEventHandler());
    myStartButton = lookup("#StartButton").query();
    myLanguageDropDown = lookup("#LanguageChoice").query();
    myStyleDropDown = lookup("#StyleChoice").query();
  }

  @Test
  void testLanguageChangeGerman() {
    testLanguages("German", "Deutsch");
  }

  @Test
  void testLanguageChangeSpanish() {
    ResourceBundle resources = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_PACKAGE + "Spanish");
    String esp = resources.getString("Spanish");
    testLanguages("Spanish", esp);
  }

  @Test
  void testLanguageChangeIndonesian() {
    testLanguages("Indonesian", "Bahasa Indonesia");
  }

  @Test
  void testLanguageChangeEnglish() {
    testLanguages("English", "English");
  }

  private void testLanguages(String language, String select) {
    ResourceBundle resources = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_PACKAGE + language);
    runAsJFXAction(() -> select(myLanguageDropDown.getChoiceBox(), select));
    myStartButton = lookup("#StartButton").query();
    String newLabel = myStartButton.getText();
    String expected = resources.getString("StartButton");
    assertEquals(newLabel, expected);
  }

  @Test
  void testStyleChange() {
    runAsJFXAction(() -> select(myStyleDropDown.getChoiceBox(), "Light"));
    assertEquals("Light", myStart.getStyle());
  }

  @Test
  void testStyleChangeDark() {
    runAsJFXAction(() -> select(myStyleDropDown.getChoiceBox(), "Dark"));
    assertEquals("Dark", myStart.getStyle());
  }

  @Test
  void testStyleChangeWinter() {
    runAsJFXAction(() -> select(myStyleDropDown.getChoiceBox(), "Winter"));
    assertEquals("Winter", myStart.getStyle());
  }

  @Test
  void testStartButton() {
    clickOn(myStartButton);
    VBox buttons = lookup("#GameSelectVBox").query();
    assertNotNull(buttons);
  }
}