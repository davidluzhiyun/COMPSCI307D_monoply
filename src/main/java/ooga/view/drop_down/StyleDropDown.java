package ooga.view.drop_down;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
  private final ResourceBundle myResources;
  private final ResourceBundle myLanguageResources;
  private final ResourceBundle id;

  public StyleDropDown(String language) {
    super(STYLE_MENU_KEY, language);
    id = ResourceBundle.getBundle(Main.ID_PROPERTIES);
    myResources = ResourceBundle.getBundle(View.CHOICE_BOX_PROPERTIES);
    myLanguageResources = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_PACKAGE + language);
    this.setId(id.getString(STYLE_MENU_KEY));
    createChoices();
  }

  /**
   * Populates the ChoiceBox with the languages available (which are listed in a property file).
   */
  @Override
  public void createChoices() {
    String[] availableStyles = myResources.getString(STYLES_KEY).split(StartView.COMMA_REGEX);
    List<String> styleList = Arrays.asList(availableStyles);
    List<String> styles = new ArrayList<>();
    styleList.forEach((style) -> styles.add(myLanguageResources.getString(style)));
    choiceBox = new ChoiceBox<>(FXCollections.observableArrayList(styles));
    choiceBox.setId(id.getString(STYLE_CHOICE_BOX_KEY));
    this.getChildren().add(choiceBox);
  }
}
