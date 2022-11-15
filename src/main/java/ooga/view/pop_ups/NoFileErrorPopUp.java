package ooga.view.pop_ups;

import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import ooga.Main;

public class NoFileErrorPopUp extends PopUp{

  public static final String NO_FILE_ERROR_TITLE = "NoFileErrorTitle";
  public static final String NO_FILE_ERROR = "NoFileError";
  public static final String NO_FILE_ERROR_HEADER = "NoFileErrorHeader";


  @Override
  public void showMessage(String language) {
    ResourceBundle resources = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_PACKAGE + language);
    Alert alert = new Alert(Alert.AlertType.ERROR, String.format(resources.getString(NO_FILE_ERROR)));
    alert.setTitle(resources.getString(NO_FILE_ERROR_TITLE));
    alert.setHeaderText(resources.getString(NO_FILE_ERROR_HEADER));
    alert.showAndWait();
  }
}
