package ooga.view.pop_ups;

import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ooga.controller.GetPlaceActionsRecord;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventType;
import ooga.event.command.Command;
import ooga.event.command.RequestPropertyInfoCommand;
import ooga.model.PlaceAction;
import ooga.view.View;
import ooga.view.button.CustomizedButton;
import ooga.view.components.MonopolyBoardBuilder;

public class AvailablePlaceActionsPopUp extends ActionPopUp {

  private List<PlaceAction> myActions;
  private Stage myStage;
  private String myStyle;
  private String myLanguage;
  private int propertyIndex;
  private GameEventHandler handler;
  private MonopolyBoardBuilder build;

  public AvailablePlaceActionsPopUp(GetPlaceActionsRecord record, String style,
      GameEventHandler handler, MonopolyBoardBuilder build) {
    this.myActions = record.actions();
    System.out.println(myActions);
    this.myStage = new Stage();
    this.myStyle = style;
    this.propertyIndex = record.index();
    this.handler = handler;
    this.build = build;
  }

  @Override
  public void close() {
    myStage.close();
  }

  @Override
  public void createScene() {
    ResourceBundle popUpResources = ResourceBundle.getBundle(View.POP_UP_PROPERTIES);
    VBox root = new VBox();
    for (PlaceAction act : myActions) {
      String className = popUpResources.getString(act.name());
      root.getChildren().add((CustomizedButton) makeInteractiveObject(className, myLanguage, this));
    }
    Scene scene = new Scene(root, Integer.parseInt(popUpResources.getString(HEIGHT)),
        Integer.parseInt(popUpResources.getString(WIDTH)));
    myStage.setScene(scene);
    popUpStyle(scene, myStyle);
    myStage.show();
  }

  @Override
  public void showMessage(String language) {
    this.myLanguage = language;
    createScene();
  }

  /**
   * Not implemented.
   */
  public void makeMortgage() {
  }

  public void viewInfo() {
    Command command = new RequestPropertyInfoCommand(propertyIndex);
    GameEvent event = GameEventHandler.makeGameEventwithCommand(
        GameEventType.VIEW_TO_CONTROLLER_GET_PLACE_INFO.name(), command);
    handler.publish(event);
    close();
  }

  public void buyHouse() {
    build.buildHouse(propertyIndex);
  }
}
