package ooga.view.drop_down;

import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import ooga.Main;
import ooga.view.StartView;
import ooga.view.View;

public class LanguageDropDown extends CustomizedDropDown{

  public static final String LANGUAGE_MENU_KEY = "LanguageDropDown";
  public static final String LANGUAGES_KEY = "AvailableLanguages";
  private final ResourceBundle myLanguageResources;

  public LanguageDropDown(String language) {
    super(LANGUAGE_MENU_KEY, language);
    ResourceBundle id = ResourceBundle.getBundle(Main.ID_PROPERTIES);
    myLanguageResources = ResourceBundle.getBundle(View.CHOICE_BOX_PROPERTIES);
    this.setId(id.getString(LANGUAGE_MENU_KEY));
    createChoices();
  }

  @Override
  public void createChoices() {
    String[] availableLanguages = myLanguageResources.getString(LANGUAGES_KEY).split(StartView.COMMA_REGEX);
    choiceBox = new ChoiceBox<>(FXCollections.observableArrayList(availableLanguages));
    this.getChildren().add(choiceBox);
  }
}
