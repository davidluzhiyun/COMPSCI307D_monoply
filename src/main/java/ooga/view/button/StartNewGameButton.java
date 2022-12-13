package ooga.view.button;

import java.util.ResourceBundle;
import ooga.Main;

public class StartNewGameButton extends CustomizedButton{
  public static final String START_NEW_GAME_BUTTON_KEY = "StartNewGameButton";

  public StartNewGameButton(String language) {
    super(START_NEW_GAME_BUTTON_KEY, language);
    ResourceBundle id = ResourceBundle.getBundle(Main.ID_PROPERTIES);
    this.setId(id.getString(START_NEW_GAME_BUTTON_KEY));
  }
}
