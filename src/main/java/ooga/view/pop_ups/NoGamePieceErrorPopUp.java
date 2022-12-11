package ooga.view.pop_ups;

import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import ooga.Main;

public class NoGamePieceErrorPopUp extends InformationPopUp {
  public static final String NO_GAME_PIECE_ERROR_TITLE = "NoGamePieceErrorTitle";
  public static final String NO_GAME_PIECE_ERROR = "NoGamePieceError";
  public static final String NO_GAME_PIECE_ERROR_HEADER = "NoGamePieceErrorHeader";

  @Override
  public void showMessage(String language) {
    ResourceBundle resources = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_PACKAGE + language);
    Alert alert = new Alert(Alert.AlertType.ERROR, String.format(resources.getString(NO_GAME_PIECE_ERROR)));
    alert.setTitle(resources.getString(NO_GAME_PIECE_ERROR_TITLE));
    alert.setHeaderText(resources.getString(NO_GAME_PIECE_ERROR_HEADER));
    alert.showAndWait();
  }
}
