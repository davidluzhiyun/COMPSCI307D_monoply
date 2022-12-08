package ooga.view.button;

import java.util.ResourceBundle;
import ooga.Main;

public class EndTurnButton extends CustomizedButton{
  public static final String END_TURN_KEY = "EndTurnButton";

  public EndTurnButton(String language) {
    super(END_TURN_KEY, language);
    ResourceBundle id = ResourceBundle.getBundle(Main.ID_PROPERTIES);
    this.setId(id.getString(END_TURN_KEY));

  }
}
