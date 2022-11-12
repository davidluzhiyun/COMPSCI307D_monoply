package ooga.view;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ResourceBundle;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ooga.Main;
import ooga.view.button.FileUploadButton;

public class StartView extends View{

  private final Scene myScene;
  private final ResourceBundle myScreenResources;
  public static final String SCREEN = "Screen";
  public static final String DEFAULT_LANGUAGE_KEY = "DefaultLanguage";
  public static final String WIDTH_KEY = "Width";
  public static final String HEIGHT_KEY = "Height";

  private Group myRoot;

  public StartView(Stage stage) {
    myScreenResources = ResourceBundle.getBundle(Main.DEFAULT_RESOURCE_PACKAGE + SCREEN);
    FileUploadButton fileButton = new FileUploadButton(myScreenResources.getString(DEFAULT_LANGUAGE_KEY));
    myRoot = new Group(fileButton);
    int width = Integer.parseInt(myScreenResources.getString(WIDTH_KEY));
    int height = Integer.parseInt(myScreenResources.getString(HEIGHT_KEY));
    myScene = new Scene(myRoot, width, height);
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
