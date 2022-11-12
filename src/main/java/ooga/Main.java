package ooga;

import javafx.application.Application;
import javafx.stage.Stage;
import ooga.controller.SampleController;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.view.SampleView;
import ooga.view.StartView;

public class Main extends Application {

  public static final String DEFAULT_RESOURCE_PACKAGE = "properties.";
  public static final String DEFAULT_LANGUAGE_PACKAGE = DEFAULT_RESOURCE_PACKAGE + "language.";


  /**
   * We can definitely change this later!
   * @param primaryStage the primary stage for this application, onto which
   * the application scene can be set.
   * @throws Exception
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    StartView start = new StartView(primaryStage);
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
