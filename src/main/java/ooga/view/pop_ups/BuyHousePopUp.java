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
import ooga.view.drop_down.BuyHouseDropDown;
import ooga.view.drop_down.CustomizedDropDown;

public class BuyHousePopUp extends ActionPopUp {

  private int currentPlayer;
  private final Stage myStage;
  private final ResourceBundle popUpResources;
  private final String myStyle;
  private final ResourceBundle idResources;
  private String myLanguage;
  private ResourceBundle myResources;
  public static final String HOUSE_POP_UP_TEXT = "BuyHouseText";
  public static final String HOUSE_POP_UP_BUTTONS_KEY = "HousePopUpButtons";
  public static final String HOUSE_POP_UP_VBOX = "BuyHousePopUpVBox";
  private VBox root;
  private int selectedProperty;

  public BuyHousePopUp(int player, String style) {
    this.currentPlayer = player;
    this.myStage = new Stage();
    this.popUpResources = ResourceBundle.getBundle(View.POP_UP_PROPERTIES);
    this.myStyle = style;
    this.selectedProperty = -1;
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
    root = new VBox(playerText, text, (CustomizedDropDown) makeInteractiveObject(
        BuyHouseDropDown.BUY_HOUSE_DROP_DOWN_KEY, myLanguage, this));
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

  /**
   * Called when you press the purchase button (set in Buttons.properties file)
   */
  public void buildHouse() {
//    myBoard.buildHouse(selectedProperty);
    close();
  }

  /**
   * TODO: also need to actually get the amount that this will cost the player.
   *
   * @param newValue
   */
  public void previewPrice(Number newValue) {
    int price = (int) newValue;
    Label priceText = new Label(String.format(myResources.getString("PriceText"), price));
    priceText.setWrapText(true);
    root.getChildren().addAll(priceText, makeButtons());
    selectedProperty = price;
  }
}
