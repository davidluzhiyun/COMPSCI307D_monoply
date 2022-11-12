package ooga.view.button;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import ooga.Main;
import ooga.view.InteractiveObject;
import ooga.view.View;

public class CustomizedButton extends Button implements InteractiveObject {

  private final ResourceBundle myResources;

  public CustomizedButton(String labelKey, String language) {
    myResources = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_PACKAGE + language);
    labelButton(labelKey);
  }

  private void labelButton(String key) {
    this.setText(myResources.getString(key));
  }

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
