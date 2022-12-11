package ooga.view.drop_down;

import java.awt.Choice;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import ooga.Main;
import ooga.view.StartView;
import ooga.view.View;
import ooga.view.button.CustomizedButton;

public class BuyHouseDropDown extends CustomizedDropDown {

  public static final String BUY_HOUSE_DROP_DOWN_KEY = "BuyHouseDropDown";
  private final ResourceBundle myResources;
  private final ResourceBundle idResources;
  private final ResourceBundle myLanguageResources;

  public BuyHouseDropDown(String language) {
    super(BUY_HOUSE_DROP_DOWN_KEY, language);
    this.myResources = ResourceBundle.getBundle(View.CHOICE_BOX_PROPERTIES);
    this.idResources = ResourceBundle.getBundle(Main.ID_PROPERTIES);
    this.setId(idResources.getString(BUY_HOUSE_DROP_DOWN_KEY));
    this.myLanguageResources = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_PACKAGE + language);
    createChoices();
  }

  /**
   * TODO: actually find way to get the current player's properties that they can buy a house on.
   */
  @Override
  public void createChoices() {
    String placeHolder = "Paris, Seoul";
    String[] availablePlaces = placeHolder.split(StartView.COMMA_REGEX);
    choiceBox = new ChoiceBox<>(FXCollections.observableArrayList(availablePlaces));
    this.getChildren().add(choiceBox);
  }
}
