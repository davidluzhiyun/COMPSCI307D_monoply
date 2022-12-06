package ooga.view.pop_ups;

import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ooga.Main;
import ooga.view.View;
import ooga.view.button.CustomizedButton;
import ooga.view.components.Board;
import ooga.view.drop_down.BuyHouseDropDown;
import ooga.view.drop_down.CustomizedDropDown;

public class BuyHousePopUp extends ActionPopUp {

  private int currentPlayer;
  private final Stage myStage;
  private final ResourceBundle popUpResources;
  private final String myStyle;
  private final ResourceBundle idResources;
  private Board myBoard;
  private String myLanguage;
  private ResourceBundle myResources;
  public static final String HOUSE_POP_UP_TEXT = "BuyHouseText";
  public static final String HOUSE_POP_UP_BUTTONS_KEY = "HousePopUpButtons";
  public static final String HOUSE_POP_UP_VBOX = "BuyHousePopUpVBox";

  public BuyHousePopUp(int player, String style, Board board) {
    this.currentPlayer = player;
    this.myStage = new Stage();
    this.popUpResources = ResourceBundle.getBundle(View.POP_UP_PROPERTIES);
    this.myStyle = style;
    this.myBoard = board;
    this.idResources = ResourceBundle.getBundle(Main.ID_PROPERTIES);
  }

  @Override
  public void close() {
    myStage.close();
  }

  @Override
  public void createScene() {
    Label playerText = new Label(
        String.format(myResources.getString(PLAYER_TEXT_KEY), currentPlayer));
    playerText.setId(idResources.getString(PLAYER_TEXT_KEY));
    Label text = new Label(myResources.getString(HOUSE_POP_UP_TEXT));
    text.setWrapText(true);
    VBox root = new VBox(playerText, text, (CustomizedDropDown) makeInteractiveObject(
        BuyHouseDropDown.BUY_HOUSE_DROP_DOWN_KEY, myLanguage, this), makeButtons());
    root.setId(idResources.getString(HOUSE_POP_UP_VBOX));
    Scene scene = new Scene(root, Integer.parseInt(popUpResources.getString(HEIGHT)),
        Integer.parseInt(popUpResources.getString(WIDTH)));
    myStage.setScene(scene);
    popUpStyle(scene, myStyle);
  }

  private HBox makeButtons() {
    HBox box = new HBox();
    String[] buttons = popUpResources.getString(HOUSE_POP_UP_BUTTONS_KEY).split(COMMA_REGEX);
    for (String button : buttons) {
      box.getChildren().add((CustomizedButton) makeInteractiveObject(button, myLanguage, this));
    }
    return box;
  }

  @Override
  public void showMessage(String language) {
    this.myLanguage = language;
    this.myResources = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_PACKAGE + language);
    createScene();
    myStage.show();
  }

  public void buildHouse() {
    myBoard.buildHouse(0);
  }
  public void previewPrice(Number newValue) {
  }

}
