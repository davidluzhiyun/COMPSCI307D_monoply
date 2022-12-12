package ooga.view.pop_ups.errors;

import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import ooga.Main;
import ooga.view.pop_ups.PopUp;

public class ErrorPopUp implements PopUp {
  private String myError;
  public static final String TITLE = "Title";
  public static final String HEADER = "Header";
  public ErrorPopUp (String errorType, String language) {
    this.myError = errorType;
    showMessage(language);
  }

  @Override
  public void showMessage(String language) {
    ResourceBundle resources = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_PACKAGE + language);
    Alert alert = new Alert(Alert.AlertType.ERROR, String.format(resources.getString(myError)));
    alert.setTitle(resources.getString(myError + TITLE));
    alert.setHeaderText(resources.getString(myError + HEADER));
    alert.showAndWait();
  }
}
