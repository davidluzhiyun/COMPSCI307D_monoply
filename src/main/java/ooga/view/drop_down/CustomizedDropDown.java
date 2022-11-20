package ooga.view.drop_down;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ResourceBundle;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ooga.Main;
import ooga.view.InteractiveObject;
import ooga.view.View;

/**
 * Creates a labeled drop-down menu with customized options and label. Also contains method to set a
 * specific Method whenever a user selects an option in the ChoiceBox. Extends VBox (purely for
 * stylistic reasons, to nicely position the text label above the ChoiceBox) and also implements
 * InteractiveObject interface to set action.
 */
public abstract class CustomizedDropDown extends VBox implements InteractiveObject {

  protected ChoiceBox<String> choiceBox;
  private final ResourceBundle myResources;
  public static final String DROP_DOWN_TEXT_ID = "DropDownText";

  public CustomizedDropDown(String labelKey, String language) {
    myResources = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_PACKAGE + language);
    this.getChildren().add(labelDropDown(labelKey));
  }

  private Label labelDropDown(String key) {
    Label text = new Label(myResources.getString(key));
    text.setId(DROP_DOWN_TEXT_ID);
    return text;
  }

  public abstract void createChoices();

  @Override
  public void setAction(Method method, View view) {
    choiceBox.getSelectionModel().selectedIndexProperty().addListener(
        (observable, oldValue, newValue) -> {
          try {
            method.invoke(view, newValue);
          } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
          }
        });
  }

  /**
   * Added just for testing reasons.
   */
  public ChoiceBox<String> getChoiceBox() {
    return choiceBox;
  }
}
