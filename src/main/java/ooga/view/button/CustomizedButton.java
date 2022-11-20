package ooga.view.button;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import ooga.Main;
import ooga.view.InteractiveObject;
import ooga.view.View;

/**
 * Creates a button object with a designated label and method. Extends Button from JavaFX and
 * implements the InteractiveObject interface.
 */
public class CustomizedButton extends Button implements InteractiveObject {

  private final ResourceBundle myResources;

  public CustomizedButton(String labelKey, String language) {
    myResources = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_PACKAGE + language);
    labelButton(labelKey);
  }

  /**
   * Sets the visible text for the button.
   *
   * @param key: String, key for resources file to get the label for the correct button.
   */
  private void labelButton(String key) {
    this.setText(myResources.getString(key));
  }

  /**
   * Sets the button action by invoking a given method. Invokes it within whichever View class is
   * passed in.
   *
   * @param method: Method that we want the button to take when clicked.
   * @param view:   View (sub)class containing this method parameter.
   */
  @Override
  public void setAction(Method method, View view) {
    this.setOnAction(e -> {
      try {
        method.invoke(view);
      } catch (IllegalAccessException | InvocationTargetException ex) {
        throw new RuntimeException(ex);
      }
    });
  }
}
