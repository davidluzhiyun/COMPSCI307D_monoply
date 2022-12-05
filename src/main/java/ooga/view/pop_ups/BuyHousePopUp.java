package ooga.view.pop_ups;

import java.util.ResourceBundle;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ooga.Main;
import ooga.view.View;
import ooga.view.button.CustomizedButton;
import ooga.view.components.Board;

public class BuyHousePopUp extends ActionPopUp{
  private int currentPlayer;
  private final Stage myStage;
  private final ResourceBundle popUpResources;
  private final String myStyle;
  private final ResourceBundle idResources;
  private Board myBoard;
  private String myLanguage;
  private ResourceBundle myResources;
  public static final String HOUSE_POP_UP_TEXT = "BuyHouseText";

  public BuyHousePopUp(int player, String style, Board board) {
    this.currentPlayer = player;
    this.myStage = new Stage();
    this.popUpResources = ResourceBundle.getBundle(View.POP_UP_PROPERTIES);
    this.myStyle = style;
    this.myBoard = board;
    this.idResources = ResourceBundle.getBundle(Main.ID_PROPERTIES);
  }

  @Override
  public void close() {myStage.close();}

  // going to need a drop down showing available properties
  @Override
  public void createScene() {
    Label playerText = new Label(
        String.format(myResources.getString(PLAYER_TEXT_KEY), currentPlayer));
    playerText.setId(idResources.getString(PLAYER_TEXT_KEY));
    Label text = new Label(myResources.getString(HOUSE_POP_UP_TEXT));
    VBox root = new VBox(playerText, text, (CustomizedButton) makeInteractiveObject("PurchaseButton", myLanguage, this));
  }

  @Override
  public void showMessage(String language) {
    this.myLanguage = language;
    this.myResources = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_PACKAGE + language);
    createScene();
    myStage.show();
  }
  public void buildHouse() {
    System.out.println("hi");
  }
}
