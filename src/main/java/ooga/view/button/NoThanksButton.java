package ooga.view.button;

import java.util.ResourceBundle;
import ooga.Main;

public class NoThanksButton extends CustomizedButton {
  public static final String NO_THANKS_BUTTON_KEY = "NoThanksButton";

  public NoThanksButton(String labelKey, String language) {
    super(NO_THANKS_BUTTON_KEY, language);
    ResourceBundle id = ResourceBundle.getBundle(Main.ID_PROPERTIES);
    this.setId(id.getString(NO_THANKS_BUTTON_KEY));

  }
}
