package ooga.view.button;

import java.util.ResourceBundle;
import ooga.Main;

public class SaveFileButton extends CustomizedButton {
  public static final String SAVE_FILE_KEY = "SaveFileButton";

  public SaveFileButton(String language) {
    super(SAVE_FILE_KEY, language);
    ResourceBundle id = ResourceBundle.getBundle(Main.ID_PROPERTIES);
    this.setId(id.getString(SAVE_FILE_KEY));
  }
}
