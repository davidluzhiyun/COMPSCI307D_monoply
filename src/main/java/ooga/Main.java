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
   * Start of the program.
   */
//  public static void main(String[] args) {
//    GameEventHandler gameEventHandler = new GameEventHandler();
//    SampleController sampleController = new SampleController(gameEventHandler);
//    gameEventHandler.addEventListener(sampleController);
//    SampleView sampleView = new SampleView(gameEventHandler);
//    gameEventHandler.addEventListener(sampleView);
//    sampleView.start();
//  }

  @Override
  public void start(Stage primaryStage) throws Exception {
      StartView start = new StartView(primaryStage);
  }
}
