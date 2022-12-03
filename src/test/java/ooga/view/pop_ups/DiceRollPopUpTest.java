package ooga.view.pop_ups;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.Main;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

/**
 * Check that it is in the same style as selected, also correct language labels.
 * Check that it displays the correct number
 */

class DiceRollPopUpTest extends DukeApplicationTest {

  private ResourceBundle english;
  private ResourceBundle spanish;
  private ResourceBundle indonesian;
  private ResourceBundle german;
  private DiceRollPopUp myPop;
  private int player;


  @Override
  public void start(Stage stage) throws Exception {
//    myPop = new DiceRollPopUp(1, "Default", "English");
    player = (int) Math.random();
    myPop = new DiceRollPopUp(player, "Light", "English");
    myPop.showMessage("English");
    english = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_PACKAGE + "English");
    german = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_PACKAGE + "German");
    spanish = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_PACKAGE + "Spanish");
    indonesian = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_PACKAGE + "Indonesian");

  }

  @Test
  void testPlayerText() {
    String expected = String.format(english.getString("PlayerText"), player);
    Label playerText = lookup("#DicePopPlayerText").query();
    assertEquals(expected, playerText.getText());
  }

}