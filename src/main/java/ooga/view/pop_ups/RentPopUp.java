package ooga.view.pop_ups;

import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import ooga.Main;

public class RentPopUp implements PopUp {
  public static final String RENT_MESSAGE_TITLE = "RentPopUpTitle";
  public static final String RENT_MESSAGE_HEADER = "RentPopUpHeader";
  public static final String RENT_MESSAGE = "RentPopUp";
  private int moneyOwed;

  public RentPopUp(int moneyOwed) {
    this.moneyOwed = moneyOwed;
  }
  @Override
  public void showMessage(String language) {
    // TODO: edit to show the amount they owe/taken out. this should be retrieved from the new command.
    ResourceBundle resources = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_PACKAGE + language);
    Alert alert = new Alert(AlertType.INFORMATION, String.format(resources.getString(RENT_MESSAGE), moneyOwed));
    alert.setTitle(resources.getString(RENT_MESSAGE_TITLE));
    alert.setHeaderText(resources.getString(RENT_MESSAGE_HEADER));
    alert.showAndWait();
  }
}
