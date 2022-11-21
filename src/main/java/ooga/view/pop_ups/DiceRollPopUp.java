package ooga.view.pop_ups;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ResourceBundle;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.Main;

/**
 * This pop up is a little different because it requires action to be taken. May want to consider
 * having two abstract classes for pop-ups: information pop up and action pop up.
 */
public class DiceRollPopUp implements PopUp {
  private int currentPlayer;
  private final Stage myStage;

  public DiceRollPopUp(int player) {
    this.currentPlayer = player;
    this.myStage = new Stage();
  }

  @Override
  public void showMessage(String language)  {
    ResourceBundle resources = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_PACKAGE + language);
    Text playerText = new Text(String.format(resources.getString("PlayerText"), currentPlayer));
    Text rollText = new Text(resources.getString("RollDice"));
    ImageView diceImage = createDiceImage();
    VBox root = new VBox(playerText, rollText, diceImage);
    Scene scene = new Scene(root, 300, 200);
    myStage.setScene(scene);
    myStage.show();
  }
  private ImageView createDiceImage() {
    Image image = new Image("dice.png");
    ImageView diceImage = new ImageView(image);
    diceImage.setPreserveRatio(true);
    diceImage.setFitWidth(200);
    return diceImage;
  }

  public void close() {
    myStage.close();
  }
}
