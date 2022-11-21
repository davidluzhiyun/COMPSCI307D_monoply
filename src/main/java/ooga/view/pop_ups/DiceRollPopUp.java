package ooga.view.pop_ups;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Method;
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
import ooga.Reflection;
import ooga.view.GameView;
import ooga.view.StartView;
import ooga.view.View;
import ooga.view.button.DiceRollButton;

/**
 * This pop up is a little different because it requires action to be taken. May want to consider
 * having two abstract classes for pop-ups: information pop up and action pop up.
 */
public class DiceRollPopUp implements PopUp {
  private int currentPlayer;
  private final Stage myStage;
  private String myLanguage;
  private DiceRollButton button;

  public DiceRollPopUp(int player) {
    this.currentPlayer = player;
    this.myStage = new Stage();
  }

  @Override
  public void showMessage(String language) {
    this.myLanguage = language;
    ResourceBundle resources = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_PACKAGE + language);
    Text playerText = new Text(String.format(resources.getString("PlayerText"), currentPlayer));
    Text rollText = new Text(resources.getString("RollDice"));
    ImageView diceImage = createDiceImage();
    createRollButton();
    VBox root = new VBox(playerText, rollText, diceImage, button);
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

  private DiceRollButton createRollButton() {
    button = new DiceRollButton(myLanguage);
    return button;
  }

  public void makeButtonActive(View view) {
    ResourceBundle resources = ResourceBundle.getBundle(View.BUTTON_PROPERTIES);
    Reflection reflection = new Reflection();
    String method = resources.getString(
        String.format(StartView.STRING_FORMATTER, "DiceRollButton", StartView.METHOD));
    button.setAction(reflection.makeMethod(method, GameView.class, null), view);

  }

  public void close() {
    myStage.close();
  }
}
