package ooga.view.button;


import java.util.ResourceBundle;
import ooga.Main;

public class SelectButton extends CustomizedButton {

  public static final String SELECT_BUTTON_KEY = "SelectButton";

  public SelectButton(String language) {
    super(SELECT_BUTTON_KEY, language);
    ResourceBundle id = ResourceBundle.getBundle(Main.ID_PROPERTIES);
    this.setId(id.getString(SELECT_BUTTON_KEY));

  }
}
