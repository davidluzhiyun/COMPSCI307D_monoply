package ooga.view.pop_ups;

import java.util.ResourceBundle;
import javafx.stage.Stage;
import ooga.Main;

public class GamePiecePopUp extends ActionPopUp {
  private int currentPlayer;
  private final Stage myStage;
  private String myLanguage;

  public GamePiecePopUp(int player) {
    this.currentPlayer = player;
    this.myStage = new Stage();
  }

  @Override
  public void showMessage(String language) {
    this.myLanguage = language;
    ResourceBundle resources = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_PACKAGE + language);
  }
}
