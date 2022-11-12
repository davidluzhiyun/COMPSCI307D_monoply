package ooga.view.button;

import java.util.ResourceBundle;
import javafx.scene.control.Button;
import ooga.Main;

public class CustomizedButton extends Button {

  private final ResourceBundle myResources;

  public CustomizedButton(String labelKey, String language) {
    myResources = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_PACKAGE + language);
    labelButton(labelKey);
  }

  private void labelButton(String key) {this.setText(myResources.getString(key));}

}
