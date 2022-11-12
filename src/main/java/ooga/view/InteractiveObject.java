package ooga.view;

import java.lang.reflect.Method;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * This will allow us to create any screen objects (e.g. choice boxes, sliders, buttons) via
 * reflection, and without having to worry about specific types.
 */
public interface InteractiveObject {

  public void setAction(Method method, View view);

}
