package ooga.view.button;

import java.util.ResourceBundle;
import ooga.Main;

public class EditDimensionsButton extends CustomizedButton {

  public static final String EDIT_DIMENSIONS_KEY = "EditDimensionsButton";

  public EditDimensionsButton(String language) {
    super(EDIT_DIMENSIONS_KEY, language);
    ResourceBundle id = ResourceBundle.getBundle(Main.ID_PROPERTIES);
    this.setId(id.getString(EDIT_DIMENSIONS_KEY));

  }
}
