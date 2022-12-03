package ooga.view.pop_ups;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import javafx.stage.Stage;
import ooga.event.GameEventHandler;
import ooga.view.GameView;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class RollResultPopUpTest extends DukeApplicationTest {
  @Override
  public void start(Stage stage) throws Exception {
    Random random = new Random();
    int roll1 = random.ints(1, 7).findFirst().getAsInt();
    int roll2 = random.ints(1, 7).findFirst().getAsInt();
    RollResultPopUp pop = new RollResultPopUp(roll1, roll2);
    pop.showMessage("English");

  }

  @Test
  void testPop() {
  }


}