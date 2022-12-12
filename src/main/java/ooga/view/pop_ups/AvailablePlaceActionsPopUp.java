package ooga.view.pop_ups;

import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ooga.model.PlaceAction;
import ooga.view.View;
import ooga.view.button.CustomizedButton;

public class AvailablePlaceActionsPopUp extends ActionPopUp{
  private List<PlaceAction> myActions;
  private Stage myStage;
  private String myStyle;
  private String myLanguage;

  public AvailablePlaceActionsPopUp(Object actions, String style) {
    this.myActions = (List<PlaceAction>) actions;
    this.myStage = new Stage();
    this.myStyle = style;
  }
  @Override
  public void close() {myStage.close();}

  @Override
  public void createScene() {
    ResourceBundle popUpResources = ResourceBundle.getBundle(View.POP_UP_PROPERTIES);
    Group root = new Group();
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
  private void makeMortgage() {}
  private void viewInfo() {}
  private void buyHouse() {}
}
