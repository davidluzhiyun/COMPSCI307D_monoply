package ooga.view.pop_ups;

import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import ooga.Main;

public class RollResultPopUp extends InformationPopUp {

  public static final String DICE_ROLL_MESSAGE = "DiceRollPopUp";
  public static final String DICE_ROLL_MESSAGE_HEADER = "DiceRollPopUpHeader";
  private int myRoll;

  public RollResultPopUp (int roll){
    this.myRoll = roll;
  }

  @Override
  public void showMessage(String language) {
    ResourceBundle resources = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_PACKAGE + language);
    Alert alert = new Alert(AlertType.INFORMATION, String.format(resources.getString(DICE_ROLL_MESSAGE), myRoll));
    alert.setHeaderText(String.format(resources.getString(DICE_ROLL_MESSAGE_HEADER), myRoll));
    alert.showAndWait();
  }
}
