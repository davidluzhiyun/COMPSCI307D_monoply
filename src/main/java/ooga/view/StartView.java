package ooga.view;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ResourceBundle;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ooga.view.button.FileUploadButton;

public class StartView extends View{

  private Scene myScene;
  private Group myRoot;

  public StartView(Stage stage) {
    FileUploadButton fileButton = new FileUploadButton("English");
    myRoot = new Group(fileButton);
    myScene = new Scene(myRoot, 600, 600);
    stage.setScene(myScene);
    stage.show();
  }

  @Override
  public Object makeButtonObject() {
//    ResourceBundle buttonResources = ResourceBundle.getBundle(View.BUTTON_PROPERTIES);
//    String className = buttonResources.getString(name);
//    CustomButton myButton = (CustomButton) makeObject(className, new Class[]{String.class},
//        new Object[]{language});
//    String method = buttonResources.getString(
//        String.format(View.STRING_FORMATTER, name, SimulationView.METHOD));
//    myButton.onClick(e -> {
//      try {
//        Method m = SimulationView.class.getDeclaredMethod(method);
//        m.invoke(sim);
//      } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException h) {
//        throw new RuntimeException(h);
//      }
//    });
//    return myButton;
    return null;
  }
}
