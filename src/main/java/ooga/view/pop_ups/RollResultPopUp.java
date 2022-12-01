package ooga.view.pop_ups;

import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ooga.Main;
import ooga.view.View;

public class RollResultPopUp extends InformationPopUp {

  public static final String DICE_ROLL_MESSAGE = "DiceRollPopUp";
  public static final String DICE_ROLL_MESSAGE_HEADER = "DiceRollPopUpHeader";
  private int myRoll1;
  private int myRoll2;
  private int myRoll;

  public RollResultPopUp (int roll1, int roll2){
    this.myRoll1 = roll1;
    this.myRoll2 = roll2;
    this.myRoll = roll1 + roll2;
  }

  @Override
  public void showMessage(String language) {
    ResourceBundle resources = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_PACKAGE + language);
    Alert alert = new Alert(AlertType.INFORMATION, String.format(resources.getString(DICE_ROLL_MESSAGE), myRoll));
    alert.setHeaderText(String.format(resources.getString(DICE_ROLL_MESSAGE_HEADER), myRoll));
    alert.setGraphic(makeDiceImage());
    alert.showAndWait();
  }

  private HBox makeDiceImage() {
    ResourceBundle resources = ResourceBundle.getBundle(View.POP_UP_PROPERTIES);
    ImageView die1 = new ImageView(new Image(resources.getString(String.format("DiceRoll%d", myRoll1))));
    ImageView die2 = new ImageView(new Image(resources.getString(String.format("DiceRoll%d", myRoll2))));
    die1.setFitWidth(Integer.parseInt(resources.getString(DiceRollPopUp.DICE_WIDTH)));
    die2.setFitWidth(Integer.parseInt(resources.getString(DiceRollPopUp.DICE_WIDTH)));
    die1.setPreserveRatio(true);
    die2.setPreserveRatio(true);
    return new HBox(die1, die2);
  }
}
