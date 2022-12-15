package ooga.view.button;

import java.util.ResourceBundle;
import ooga.Main;

public class BuyHouseButton extends CustomizedButton {

  public static final String BUY_HOUSE_KEY = "BuyHouseButton";

  public BuyHouseButton(String language) {
    super(BUY_HOUSE_KEY, language);
    System.out.println("hello....");

    ResourceBundle id = ResourceBundle.getBundle(Main.ID_PROPERTIES);
    this.setId(id.getString(BUY_HOUSE_KEY));
  }
}
