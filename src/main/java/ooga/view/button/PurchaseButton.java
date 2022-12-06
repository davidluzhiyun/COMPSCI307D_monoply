package ooga.view.button;

import java.util.ResourceBundle;
import ooga.Main;

public class PurchaseButton extends CustomizedButton{
  public static final String PURCHASE_BUTTON_KEY = "PurchaseButton";

  public PurchaseButton(String language) {
    super(PURCHASE_BUTTON_KEY, language);
    ResourceBundle id = ResourceBundle.getBundle(Main.ID_PROPERTIES);
    this.setId(id.getString(PURCHASE_BUTTON_KEY));
  }
}
