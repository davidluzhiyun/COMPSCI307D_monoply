package ooga.view;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ResourceBundle;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ooga.Main;
import ooga.Reflection;
import ooga.view.button.CustomizedButton;

public class StartView extends View {

  private final Scene myScene;
  private final ResourceBundle myScreenResources;
  public static final String SCREEN = "Screen";
  public static final String DEFAULT_LANGUAGE_KEY = "DefaultLanguage";
  public static final String WIDTH_KEY = "Width";
  public static final String HEIGHT_KEY = "Height";
  public static final String START_OBJECTS_KEY = "StartObjects";
  public static final String SPACE_REGEX = " ";
  public static final String COMMA_REGEX = ", ";
  public static final String STRING_FORMATTER = "%s%s";
  public static final String METHOD = "Method";
  public static final String JSON_FILE_EXTENSION = "JSON Files";
  public static final String DATA_FILE_JSON_EXTENSION = "*.json";
  public static final String DROP_DOWN = "DropDown";
  public static final String DATA_FILE_FOLDER = System.getProperty("user.dir") + "/data";
  private final String defaultLanguage;
  private Group myRoot;
  private final Stage myStage;
  private File myConfigFile;
  private VBox layout;
  private final int width;
  private final int height;

  public StartView(Stage stage) {
    myScreenResources = ResourceBundle.getBundle(Main.DEFAULT_RESOURCE_PACKAGE + SCREEN);
    defaultLanguage = myScreenResources.getString(DEFAULT_LANGUAGE_KEY);
    myRoot = new Group();
    width = Integer.parseInt(myScreenResources.getString(WIDTH_KEY));
    height = Integer.parseInt(myScreenResources.getString(HEIGHT_KEY));
    myScene = new Scene(myRoot, width, height);
    this.myStage = stage;
    setUpLayout(defaultLanguage);
  }

  private void placeItems() {
    centerHorizontally(layout, width);
    centerVertically(layout, height);
  }

  private void setUpLayout(String language) {
    layout = new VBox();
    myRoot.getChildren().add(layout);
    makeInteractiveObjects(language);
    myStage.setScene(myScene);
    myStage.show();
    placeItems();
  }

  private void makeInteractiveObjects(String language) {
    String[] names = myScreenResources.getString(START_OBJECTS_KEY).split(SPACE_REGEX);
    for (String name : names) {
      layout.getChildren().add((Node) makeInteractiveObject(name, language));
    }
  }

  /**
   * I also kind of referenced my cellsociety code (which I also wrote, don't worry!!) for this
   *
   * @param name: name of the class you would like to create
   * @return
   */
  @Override
  public InteractiveObject makeInteractiveObject(String name, String language) {
    Reflection reflection = new Reflection();
    ResourceBundle buttonResources = ResourceBundle.getBundle(View.BUTTON_PROPERTIES);
    String className = buttonResources.getString(name);
    InteractiveObject myButton = (InteractiveObject) reflection.makeObject(className,
        new Class[]{String.class},
        new Object[]{language});
    String method = buttonResources.getString(String.format(STRING_FORMATTER, name, METHOD));
    if (name.contains(DROP_DOWN)) {
      myButton.setAction(reflection.makeMethod(method, StartView.class, new Class[]{Number.class}), this);
    } else {
      myButton.setAction(reflection.makeMethod(method, StartView.class, null), this);
    }
    return myButton;
  }

  public void fileHandler() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setInitialDirectory(new File(DATA_FILE_FOLDER));
    fileChooser.getExtensionFilters()
        .setAll(new FileChooser.ExtensionFilter(JSON_FILE_EXTENSION,
            DATA_FILE_JSON_EXTENSION));
    myConfigFile = fileChooser.showOpenDialog(myStage);
  }

  public void changeLanguage(Number newValue) {
    ResourceBundle choiceResources = ResourceBundle.getBundle(Main.DEFAULT_RESOURCE_PACKAGE + "ChoiceBox");
    String language = choiceResources.getString(String.format("%s%d", "Language", newValue));
    myRoot.getChildren().remove(layout);
    myStage.close();
    setUpLayout(language);
    placeItems();
  }

  /**
   * Should throw an error if users click on it without first uploading a file (or selecting a
   * language) Should act as an event that signals controller to parse the file, start up the main
   * game screen.
   */
  public void startButtonHandler() {
    System.out.println(myConfigFile);
  }
  public File getMyConfigFile() {return myConfigFile;}
}
