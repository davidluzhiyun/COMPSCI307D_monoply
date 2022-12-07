package ooga.view.button;

import java.util.ResourceBundle;
import ooga.Main;

public class CancelButton extends CustomizedButton {

  public static final String CANCEL_BUTTON_KEY = "CancelButton";

  public CancelButton(String language) {
    super(CANCEL_BUTTON_KEY, language);
    ResourceBundle id = ResourceBundle.getBundle(Main.ID_PROPERTIES);
    this.setId(id.getString(CANCEL_BUTTON_KEY));
  }
}
