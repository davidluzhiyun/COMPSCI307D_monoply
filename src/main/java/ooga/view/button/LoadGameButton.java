package ooga.view.button;

import java.util.ResourceBundle;
import ooga.Main;

public class LoadGameButton extends CustomizedButton {

  public static final String LOAD_GAME_BUTTON_KEY = "LoadGameButton";

  public LoadGameButton(String language) {
    super(LOAD_GAME_BUTTON_KEY, language);
    ResourceBundle id = ResourceBundle.getBundle(Main.ID_PROPERTIES);
    this.setId(id.getString(LOAD_GAME_BUTTON_KEY));

  }
}
