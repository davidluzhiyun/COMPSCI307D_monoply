package ooga.view.drop_down;

import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import ooga.Main;
import ooga.view.StartView;
import ooga.view.View;

/**
 * Creates a CustomizedDropDown with the style options available to user. Currently, the method for
 * this drop down is set in a property file.
 */
public class StyleDropDown extends CustomizedDropDown {

  public static final String STYLE_MENU_KEY = "StyleDropDown";
  public static final String STYLES_KEY = "AvailableStyles";
  public static final String STYLE_CHOICE_BOX_KEY = "StyleDropDownBox";
  private final ResourceBundle myLanguageResources;
  private final ResourceBundle id;

  public StyleDropDown(String language) {
    super(STYLE_MENU_KEY, language);
    id = ResourceBundle.getBundle(Main.ID_PROPERTIES);
    myLanguageResources = ResourceBundle.getBundle(View.CHOICE_BOX_PROPERTIES);
    this.setId(id.getString(STYLE_MENU_KEY));
    createChoices();
  }

  /**
   * Populates the ChoiceBox with the languages available (which are listed in a property file).
   */
  @Override
  public void createChoices() {
    String[] availableStyles = myLanguageResources.getString(STYLES_KEY)
        .split(StartView.COMMA_REGEX);
    choiceBox = new ChoiceBox<>(FXCollections.observableArrayList(availableStyles));
    choiceBox.setId(id.getString(STYLE_CHOICE_BOX_KEY));
    this.getChildren().add(choiceBox);
  }
}
