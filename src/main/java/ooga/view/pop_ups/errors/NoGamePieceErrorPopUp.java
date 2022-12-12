package ooga.view.pop_ups.errors;

import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import ooga.Main;

public class NoGamePieceErrorPopUp extends ErrorPopUp {
  public static final String NO_GAME_PIECE_ERROR = "NoGamePieceError";
  public NoGamePieceErrorPopUp(String language) {
    super(NO_GAME_PIECE_ERROR, language);
  }
}
