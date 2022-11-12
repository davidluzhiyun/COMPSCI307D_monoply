package ooga.view.button;

import java.util.ResourceBundle;
import ooga.Main;

public class StartButton extends CustomizedButton {

  public static final String START_BUTTON_KEY = "StartButton";

  public StartButton(String language) {
    super(START_BUTTON_KEY, language);
    ResourceBundle id = ResourceBundle.getBundle(Main.ID_PROPERTIES);
    this.setId(id.getString(START_BUTTON_KEY));
  }
}
