package ooga.view.scene;

import java.io.File;
import java.util.Objects;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import ooga.Main;
import ooga.controller.LoadBoardRecord;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventListener;
import ooga.event.GameEventType;
import ooga.event.command.SelectBoardEditConfigCommand;
import ooga.view.api.ChildView;
import ooga.view.api.ParentView;
import ooga.view.components.MenuBar;
import ooga.view.components.MonopolyBoardBuilder;
import ooga.view.components.MonopolyBoardInteractor;
import ooga.view.components.MonopolyBoardViewModel;

public class MonopolyGameEditorScene extends MonopolyScene implements ParentView<ChildView>,
    GameEventListener {

  private MonopolyBoardViewModel model;
  private Region monopolyBoard;
  private final GameEventHandler gameEventHandler;
  private MonopolyBoardBuilder monopolyBoardBuilder;
  private MonopolyBoardInteractor interactor;
  private String myLanguage;
  private MenuBar myMenu;
  private File config;
  private Stage primaryStage;

  public MonopolyGameEditorScene(Stage primaryStage, GameEventHandler gameEventHandler,
      String language, String style, File initConfig) {
    super(new AnchorPane());
    this.primaryStage = primaryStage;
    this.myLanguage = language;
    rootPane.getStylesheets().add(
        Objects.requireNonNull(
            Main.class.getResource(String.format("/style/%s.css", style)).toString()));
    this.gameEventHandler = gameEventHandler;
    gameEventHandler.addEventListener(this);

    this.config = initConfig;
    initChildren();
    setChildrenLocation();
    addChildrenToRoot();
  }

  private void getInitBoardData() {
    GameEvent gameStart = GameEventHandler.makeGameEventwithCommand(
        GameEventType.VIEW_TO_CONTROLLER_LOAD_BOARD.name(),
        new SelectBoardEditConfigCommand(config));
    gameEventHandler.publish(gameStart);
  }

  @Override
  public void addChild(ChildView child) {
    rootPane.getChildren().add(child.getView());
  }

  public void initChildren() {
    this.model = new MonopolyBoardViewModel();
    this.interactor = new MonopolyBoardInteractor(model);
    getInitBoardData();
    this.monopolyBoardBuilder = new MonopolyBoardBuilder(model, gameEventHandler);
    this.monopolyBoard = monopolyBoardBuilder.build();
    this.myMenu = new MenuBar(myLanguage, primaryStage, gameEventHandler, this);
  }

  public void setChildrenLocation() {
    AnchorPane.setTopAnchor(myMenu.getView(), 0.0);
    AnchorPane.setTopAnchor(monopolyBoard, 50.0);
  }

  public void addChildrenToRoot() {
    rootPane.getChildren().addAll(myMenu.getView(), monopolyBoard);
  }

  @Override
  public void onGameEvent(GameEvent event) {
    if (event.getGameEventType().equals("CONTROLLER_TO_VIEW_LOAD_BOARD")) {
      LoadBoardRecord command = (LoadBoardRecord) event.getGameEventCommand().getCommand()
          .getCommandArgs();
      interactor.initialize(command);
    }
    if (event.getGameEventType().equals("VIEW_POST_ACTION_DRAW_BOARD")) {
      monopolyBoardBuilder.drawPostProcessing();
    }
  }
}
