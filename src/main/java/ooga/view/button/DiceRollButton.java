package ooga.view.button;

import java.util.ResourceBundle;
import ooga.Main;

public class DiceRollButton extends CustomizedButton {

  public static final String DICE_ROLL_KEY = "RollDiceButton";

  public DiceRollButton(String language) {
    super(DICE_ROLL_KEY, language);
    ResourceBundle id = ResourceBundle.getBundle(Main.ID_PROPERTIES);
    this.setId(id.getString(DICE_ROLL_KEY));
  }
}
