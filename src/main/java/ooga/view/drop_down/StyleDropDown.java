package ooga.view.drop_down;

import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import ooga.Main;
import ooga.view.StartView;
import ooga.view.View;

public class StyleDropDown extends CustomizedDropDown {
  public static final String STYLE_MENU_KEY = "StyleDropDown";
  public static final String STYLES_KEY = "AvailableStyles";
  private final ResourceBundle myLanguageResources;

  public StyleDropDown(String language) {
    super(STYLE_MENU_KEY, language);
    ResourceBundle id = ResourceBundle.getBundle(Main.ID_PROPERTIES);
    myLanguageResources = ResourceBundle.getBundle(View.CHOICE_BOX_PROPERTIES);
    this.setId(id.getString(STYLE_MENU_KEY));
    createChoices();
  }

  @Override
  public void createChoices() {
    String[] availableStyles = myLanguageResources.getString(STYLES_KEY).split(StartView.COMMA_REGEX);
    choiceBox = new ChoiceBox<>(FXCollections.observableArrayList(availableStyles));
    this.getChildren().add(choiceBox);

  }
}
