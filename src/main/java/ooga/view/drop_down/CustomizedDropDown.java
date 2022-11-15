package ooga.view.drop_down;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ResourceBundle;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ooga.Main;
import ooga.view.InteractiveObject;
import ooga.view.View;

public abstract class CustomizedDropDown extends VBox implements InteractiveObject {

  protected ChoiceBox<String> choiceBox;
  private final ResourceBundle myResources;

  public CustomizedDropDown(String labelKey, String language) {
    myResources = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_PACKAGE + language);
    this.getChildren().add(labelDropDown(labelKey));
  }

  private Text labelDropDown(String key) {
    return new Text(myResources.getString(key));
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
