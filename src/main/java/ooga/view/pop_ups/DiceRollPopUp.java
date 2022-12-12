package ooga.view.pop_ups;

import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ooga.Main;
import ooga.Reflection;
import ooga.view.GameView;
import ooga.view.StartView;
import ooga.view.View;
import ooga.view.button.DiceRollButton;
import ooga.view.pop_ups.errors.NoRollErrorPopUp;

/**
 * This pop up is a little different because it requires action to be taken. May want to consider
 * having two abstract classes for pop-ups: information pop up and action pop up.
 * TODO: LOTS OF REFACTORING
 */
public class DiceRollPopUp extends ActionPopUp {

  private final int currentPlayer;
  private final Stage myStage;
  private String myLanguage;
  private DiceRollButton button;
  public static final String DICE_IMAGE = "dice.png";
  public static final String ROLL_DICE_TEXT_KEY = "RollDice";
  public static final String START_TURN = "StartTurn";
  public static final String DICE_WIDTH = "DiceWidth";
  private final ResourceBundle popUpResources;
  private ResourceBundle myResources;
  private final int myWidth;
  private final int myHeight;
  private final String myStyle;
  private final ResourceBundle idResources;
  public static final String ROLL_TEXT = "RollText";
  public static final String DICE_ROLL_POP_UP_KEY = "DiceRollPopUp";
  private boolean isRolled = false;


  public DiceRollPopUp(int player, String style) {
    this.currentPlayer = player;
    this.myStage = new Stage();
    myStage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
      close();
    });
    this.popUpResources = ResourceBundle.getBundle(View.POP_UP_PROPERTIES);
    this.myHeight = Integer.parseInt(popUpResources.getString(HEIGHT));
    this.myWidth = Integer.parseInt(popUpResources.getString(WIDTH));
    this.myStyle = style;
    this.idResources = ResourceBundle.getBundle(Main.ID_PROPERTIES);
  }

  @Override
  public void showMessage(String language) {
    this.myLanguage = language;
    this.myResources = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_PACKAGE + language);
    createScene();
    myStage.show();
  }

  @Override
  public void createScene() {
    Label playerText = new Label(
        String.format(myResources.getString(PLAYER_TEXT_KEY), currentPlayer));
    playerText.setId(idResources.getString(PLAYER_TEXT_KEY));
    Label rollText = new Label(myResources.getString(ROLL_DICE_TEXT_KEY));
    rollText.setId(idResources.getString(ROLL_TEXT));
    VBox root = new VBox(playerText, rollText, createDiceImage(), createRollButton());
    root.setId(idResources.getString(DICE_ROLL_POP_UP_KEY));
    Scene scene = new Scene(root, myWidth, myHeight);
    myStage.setTitle(myResources.getString(START_TURN));
    myStage.setScene(scene);
    popUpStyle(scene, myStyle);

  }

  private ImageView createDiceImage() {
    Image image = new Image(DICE_IMAGE);
    ImageView diceImage = new ImageView(image);
    diceImage.setPreserveRatio(true);
    int diceWidth = Integer.parseInt(popUpResources.getString(DICE_WIDTH));
    diceImage.setFitWidth(diceWidth);
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

  @Override
  public void close() {
    if(isRolled) {myStage.close();}
    else {
      myStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent event) {
          event.consume();
        }
      });
      NoRollErrorPopUp pop = new NoRollErrorPopUp(myLanguage);
    }
  }

  /**
   * Users should not be able to close this pop up without first rolling the dice.
   */
  public void closeFromGame() {
    this.isRolled = true;
    close();
  }

}
