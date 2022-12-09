package ooga;

import javafx.application.Application;
import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.controller.SampleController;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.model.GameModel;
import ooga.view.MainView;
import ooga.view.SampleView;
import ooga.view.StartView;
import ooga.view.scene.SceneManager;

public class Main extends Application {

  public static final String DEFAULT_RESOURCE_PACKAGE = "properties.";
  public static final String DEFAULT_LANGUAGE_PACKAGE = DEFAULT_RESOURCE_PACKAGE + "language.";
  public static final String ID_PROPERTIES = Main.DEFAULT_RESOURCE_PACKAGE + "ID";
  public static final String DATA_FILE_FOLDER = System.getProperty("user.dir") + "/data";
  public static final String PAUSED_GAME_DIRECTORY = DATA_FILE_FOLDER + "/paused_games";
  public static final String CONFIG_FILES_DIRECTORY = DATA_FILE_FOLDER + "/config_files";
  public static final String DEFAULT_RESOURCE_FOLDER =
      "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/");


  /**
   * We can definitely change this later!
   *
   * @param primaryStage the primary stage for this application.
   * @throws Exception
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    GameEventHandler gameEventHandler = new GameEventHandler();
    StartView startView = new StartView(primaryStage, gameEventHandler);
    GameModel model = new GameModel(gameEventHandler);
    Controller controller = new Controller(gameEventHandler);
    gameEventHandler.addEventListener(model);
    gameEventHandler.addEventListener(controller);
  }

  /**
   * Start of the program.
   * NOTE: I (Leila) commented this out for now just to allow the start screen to pop up.
   */

//  public static void main(String[] args) {
//    GameEventHandler gameEventHandler = new GameEventHandler();
//    SampleController sampleController = new SampleController(gameEventHandler);
//    gameEventHandler.addEventListener(sampleController);
//    SampleView sampleView = new SampleView(gameEventHandler);
//    gameEventHandler.addEventListener(sampleView);
////    sampleView.start();
//  }

}
