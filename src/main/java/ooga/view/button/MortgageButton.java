package ooga.view.button;

import java.util.ResourceBundle;
import ooga.Main;

public class MortgageButton extends CustomizedButton {

  public final static String MORTGAGE_BUTTON_KEY = "MortgageButton";

  public MortgageButton(String language) {
    super(MORTGAGE_BUTTON_KEY, language);
    ResourceBundle id = ResourceBundle.getBundle(Main.ID_PROPERTIES);
    this.setId(id.getString(MORTGAGE_BUTTON_KEY));

  }
}
