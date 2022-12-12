package ooga.view.components;

import java.util.ResourceBundle;
import javafx.scene.layout.HBox;
import ooga.Reflection;
import ooga.view.InteractiveObject;
import ooga.view.StartView;
import ooga.view.View;

public class MenuBar extends HBox {

  public MenuBar(String language) {

  }
  private void makeButtons() {
//    public InteractiveObject makeInteractiveObject(String name, String language, View view) {
//      Reflection reflection = new Reflection();
//      ResourceBundle resources = ResourceBundle.getBundle(View.BUTTON_PROPERTIES);
//      String className = resources.getString(name);
//      InteractiveObject object = (InteractiveObject) reflection.makeObject(className,
//          new Class[]{String.class},
//          new Object[]{language});
//      String method = resources.getString(
//          String.format(StartView.STRING_FORMATTER, name, StartView.METHOD));
//      if (name.contains(StartView.DROP_DOWN)) {
//        object.setAction(reflection.makeMethod(method, view.getClass(), new Class[]{Number.class}),
//            view);
//      } else {
//        object.setAction(reflection.makeMethod(method, view.getClass(), null), view);
//      }
//      return object;
    }

  }

}
