package ooga.view.drop_down;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import ooga.Main;
import ooga.view.StartView;
import ooga.view.View;

/**
 * Extends CustomizedDropDown to display a ChoiceBox to users, allowing them to select which game
 * piece they would like to use.
 */
public class GamePieceDropDown extends CustomizedDropDown {

  public static final String GAME_PIECE_KEY = "GamePieceDropDown";
  public static final String AVAILABLE_PIECES_KEY = "AvailablePieces";
  public static final String GAME_PIECE_BOX_KEY = "GamePieceDropDownBox";
  private final ResourceBundle id;
  private final ResourceBundle myResources;
  private final ResourceBundle myLanguageResources;

  public GamePieceDropDown(String language) {
    super(GAME_PIECE_KEY, language);
    myResources = ResourceBundle.getBundle(View.CHOICE_BOX_PROPERTIES);
    id = ResourceBundle.getBundle(Main.ID_PROPERTIES);
    this.setId(id.getString(GAME_PIECE_KEY));
    myLanguageResources = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_PACKAGE + language);
    createChoices();
  }

  /**
   * Generates a ChoiceBox populated with options from property files.
   */
  @Override
  public void createChoices() {
    String[] availablePieces = myResources.getString(AVAILABLE_PIECES_KEY).split(StartView.COMMA_REGEX);
    List<String> pieceList = Arrays.asList(availablePieces);
    List<String> pieces = new ArrayList<>();
    pieceList.forEach((piece) -> pieces.add(myLanguageResources.getString(piece.replace(" ", ""))));
    choiceBox = new ChoiceBox<>(FXCollections.observableArrayList(pieces));
    choiceBox.setId(id.getString(GAME_PIECE_BOX_KEY));
    this.getChildren().add(choiceBox);
  }
}
