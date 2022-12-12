package ooga.view.button;

import java.util.ResourceBundle;
import ooga.Main;

public class ViewInfoButton extends CustomizedButton {

  public static final String VIEW_INFO_BUTTON_KEY = "ViewInfoButton";

  public ViewInfoButton(String language) {
    super(VIEW_INFO_BUTTON_KEY, language);
    ResourceBundle id = ResourceBundle.getBundle(Main.ID_PROPERTIES);
    this.setId(id.getString(VIEW_INFO_BUTTON_KEY));
  }
}
