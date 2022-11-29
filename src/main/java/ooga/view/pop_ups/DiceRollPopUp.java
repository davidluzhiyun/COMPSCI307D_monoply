package ooga.view.pop_ups;

import java.util.ResourceBundle;
import javafx.scene.Scene;
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
 * TODO: LOTS OF REFACTORING
 */
public class DiceRollPopUp extends ActionPopUp {
  private int currentPlayer;
  private final Stage myStage;
  private String myLanguage;
  private DiceRollButton button;
  public static final String DICE_IMAGE = "dice.png";
  public static final String PLAYER_TEXT_KEY = "PlayerText";
  public static final String ROLL_DICE_TEXT_KEY = "RollDice";

  public DiceRollPopUp(int player) {
    this.currentPlayer = player;
    this.myStage = new Stage();
  }

  /**
   * @param language
   */
  @Override
  public void showMessage(String language) {
    this.myLanguage = language;
    ResourceBundle resources = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_PACKAGE + language);
    Text playerText = new Text(String.format(resources.getString(PLAYER_TEXT_KEY), currentPlayer));
    Text rollText = new Text(resources.getString(ROLL_DICE_TEXT_KEY));
    ImageView diceImage = createDiceImage();
    createRollButton();
    VBox root = new VBox(playerText, rollText, diceImage, button);
    Scene scene = new Scene(root, 300, 200);
    myStage.setTitle("START TURN");
    myStage.setScene(scene);
    myStage.show();
  }
  private ImageView createDiceImage() {
    Image image = new Image(DICE_IMAGE);
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
        String.format(StartView.STRING_FORMATTER, DiceRollButton.DICE_ROLL_KEY, StartView.METHOD));
    button.setAction(reflection.makeMethod(method, GameView.class, null), view);
  }

  public void close() {
    myStage.close();
  }

}
