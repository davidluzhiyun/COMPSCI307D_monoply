package ooga.view.button;

import java.util.ResourceBundle;
import ooga.Main;

public class GoToEditorButton extends CustomizedButton {
  public static final String GO_TO_EDITOR_BUTTON_KEY = "GoToEditorButton";

  public GoToEditorButton(String language) {
    super(GO_TO_EDITOR_BUTTON_KEY, language);
    ResourceBundle id = ResourceBundle.getBundle(Main.ID_PROPERTIES);
    this.setId(id.getString(GO_TO_EDITOR_BUTTON_KEY));
  }
}
