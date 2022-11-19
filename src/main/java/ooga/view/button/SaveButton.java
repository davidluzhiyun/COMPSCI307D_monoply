package ooga.view.button;

import java.util.ResourceBundle;
import ooga.Main;

public class SaveButton extends CustomizedButton {
  public static final String SAVE_KEY = "SaveButton";

  public SaveButton(String language) {
    super(SAVE_KEY, language);
    ResourceBundle id = ResourceBundle.getBundle(Main.ID_PROPERTIES);
    this.setId(id.getString(SAVE_KEY));
  }
}
