package ooga.view;

import java.lang.reflect.Method;
import java.util.ResourceBundle;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ooga.Main;
import ooga.Reflection;
import ooga.view.button.CustomizedButton;
import ooga.view.button.FileUploadButton;

public class StartView extends View {

  private final Scene myScene;
  private final ResourceBundle myScreenResources;
  public static final String SCREEN = "Screen";
  public static final String DEFAULT_LANGUAGE_KEY = "DefaultLanguage";
  public static final String WIDTH_KEY = "Width";
  public static final String HEIGHT_KEY = "Height";
  public static final String START_BUTTONS_KEY = "StartButtons";
  public static final String SPACE_REGEX = " ";
  public static final String STRING_FORMATTER = "%s%s";
  public static final String METHOD = "Method";
  private final String defaultLanguage;
  private Group myRoot;

  public StartView(Stage stage) {
    myScreenResources = ResourceBundle.getBundle(Main.DEFAULT_RESOURCE_PACKAGE + SCREEN);
    defaultLanguage = myScreenResources.getString(DEFAULT_LANGUAGE_KEY);
    myRoot = new Group();
    makeButtons();
    int width = Integer.parseInt(myScreenResources.getString(WIDTH_KEY));
    int height = Integer.parseInt(myScreenResources.getString(HEIGHT_KEY));
    myScene = new Scene(myRoot, width, height);
    stage.setScene(myScene);
    stage.show();
  }

  private void makeButtons() {
    String[] buttonNames = myScreenResources.getString(START_BUTTONS_KEY).split(SPACE_REGEX);
    for (String button : buttonNames) {
      myRoot.getChildren().add((CustomizedButton) makeInteractiveObject(button));
    }
  }

  /**
   * I also kind of referenced my cellsociety code (which I also wrote, don't worry!!) for this
   *
   * @param name: name of the class you would like to create
   * @return
   */
  @Override
  public InteractiveObject makeInteractiveObject(String name) {
    Reflection reflection = new Reflection();
    ResourceBundle buttonResources = ResourceBundle.getBundle(View.BUTTON_PROPERTIES);
    String className = buttonResources.getString(name);
    InteractiveObject myButton = (InteractiveObject) reflection.makeObject(className,
        new Class[]{String.class},
        new Object[]{defaultLanguage});
    String method = buttonResources.getString(String.format(STRING_FORMATTER, name, METHOD));
    try {
      Method m = StartView.class.getDeclaredMethod(method);
      myButton.setAction(m, this);
    } catch (NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
    return myButton;
  }

  /**
   * Obviously this will be changed later.
   */
  public void fileHandler() {
    System.out.println("is reflection working!?");
  }

  public void startButtonHandler() {
    System.out.println("This should be changed to start the actual game screen!");
  }
}
