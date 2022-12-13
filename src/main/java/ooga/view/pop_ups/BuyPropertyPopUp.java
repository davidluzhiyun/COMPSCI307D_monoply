package ooga.view.pop_ups;

import java.util.ResourceBundle;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ooga.Main;
import ooga.view.View;
import ooga.view.button.CustomizedButton;
import ooga.view.drop_down.CustomizedDropDown;

public class BuyPropertyPopUp extends ActionPopUp {
  private Stage myStage;
  private String myStyle;
  private ResourceBundle popUpResources;
  private ResourceBundle idResources;
  private String myLanguage;
  private ResourceBundle myResources;

  public BuyPropertyPopUp(String style) {
    this.myStage = new Stage();
    this.popUpResources = ResourceBundle.getBundle(View.POP_UP_PROPERTIES);
    this.myStyle = style;
    this.idResources = ResourceBundle.getBundle(Main.ID_PROPERTIES);
  }

  @Override
  public void close() {myStage.close();}

  @Override
  public void createScene() {
    String property = "New York";
    Label buyText = new Label(String.format(myResources.getString("BuyPropertyText"), property));
    VBox root = new VBox(buyText, makeButtons());
    Scene scene = new Scene(root, Integer.parseInt(popUpResources.getString(HEIGHT)),
        Integer.parseInt(popUpResources.getString(WIDTH)));

    popUpStyle(scene, myStyle);
    myStage.setScene(scene);
  }
  private HBox makeButtons() {
    String[] buttons = popUpResources.getString("BuyPropertyButtons").split(COMMA_REGEX);
    HBox buttonBar = new HBox();
    for (String button: buttons) {
      buttonBar.getChildren().add((CustomizedButton) makeInteractiveObject(button, myLanguage, this));
    }
    return buttonBar;
  }

  @Override
  public void showMessage(String language) {
    this.myLanguage = language;
    this.myResources = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_PACKAGE + language);
    createScene();
    myStage.show();
  }
  public void buyProperty() {
    System.out.println("HELLO");
  }
}
