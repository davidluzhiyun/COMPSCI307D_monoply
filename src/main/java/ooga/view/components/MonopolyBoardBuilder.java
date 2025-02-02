package ooga.view.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.geometry.Bounds;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.util.Builder;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.command.Command;
import ooga.event.command.RequestPlaceActionsCommand;

public class MonopolyBoardBuilder implements Builder<Region> {

  private Pane board;
  private GridPane grid;
  private MonopolyBoardViewModel model;
  private int currIdx;
  private GameEventHandler gameEventHandler;
  private List<GamePiece> playerPieces = new ArrayList<GamePiece>();

  public MonopolyBoardBuilder(MonopolyBoardViewModel model, GameEventHandler gameEventHandler) {
    board = new AnchorPane();
    grid = new MonopolyGridBuilder(model, board).build();
    this.gameEventHandler = gameEventHandler;
    this.model = model;
  }

  private void addCardsToGrid() {
    HashMap<Integer, Location> map = model.getIndexToLocationMap();
    List<MonopolyCardViewModel> models = model.getCardModels();

    MonopolyCardBuilder builder = new MonopolyCardBuilder();

    currIdx = 0;
    // call draw 4 times bottom, left, top, right
    draw(map, models, currIdx, model.getBoardCol() - 2, 0);
    draw(map, models, currIdx + 1, model.getBoardCol() + model.getBoardRow() - 3, 1);
    draw(map, models, currIdx + 1, model.getNumCards() - model.getBoardRow(), 2);
    draw(map, models, currIdx + 1, model.getNumCards() - 1, 3);
  }

  private void draw(HashMap<Integer, Location> map, List<MonopolyCardViewModel> models,
      int startIdx, int endIdx, int location) {
    Pane card;

    MonopolyCardBuilder builder = new MonopolyCardBuilder();
    MonopolyImageCardBuilder imageCardBuilder = new MonopolyImageCardBuilder();

    for (int i = startIdx; i <= endIdx; i++) {
      MonopolyCardViewModel model = models.get(i);
      Location loc = map.get(Integer.valueOf(i));
      Bounds bounds = grid.getCellBounds(loc.col(), loc.row());
      if (location % 2 == 1) {
        model.setWidth(bounds.getHeight());
        model.setHeight(bounds.getWidth());
      } else {
        model.setWidth(bounds.getWidth());
        model.setHeight(bounds.getHeight());
      }

      // we need different cards based on different
      if (model instanceof MonopolyImageCardViewModel) {
        if (((MonopolyImageCardViewModel) model).isCorner()) {
          ((MonopolyImageCardViewModel) model).setRotation((location * 90) - 45);
        }
        // update the builder parameters
        imageCardBuilder.setModel((MonopolyImageCardViewModel) model);
        imageCardBuilder.setLocation(location); // indicate bottom
        card = (Pane) imageCardBuilder.build();
      } else {
        builder.setModel(model); // get the type from here
        builder.setLocation(location); // indicate bottom
        card = (Pane) builder.build();
      }

      int idx = i; // has to be final
      card.setOnMouseClicked(e -> dealWithMouseClick(idx));

      // add card to the grid
      grid.add(card, loc.col(), loc.row());
      currIdx = i;
    }
  }

  /**
   * Send command to model to tell them we need place actions for the given property.
   *
   * @param index: the index of the place card the user has clicked on
   */
  private void dealWithMouseClick(int index) {
    Command cmd = new RequestPlaceActionsCommand(index);
    GameEvent event = GameEventHandler.makeGameEventwithCommand("VIEW_TO_MODEL_GET_PLACE_ACTIONS",
        cmd);
    gameEventHandler.publish(event);
  }

  public void drawPostProcessing() {
    addCardsToGrid();
  }

  @Override
  public Region build() {
    return board;
  }

  /**
   * Called by GamePiecePopUp whenever a new pop up is created. Places the piece at GO (well
   * eventually it will)
   *
   * @param piece:  the new piece that was created
   * @param player: not used right now, but probably a good idea to create some mapping
   */
  public void initializeGamePiece(GamePiece piece, int player) {
    playerPieces.add(player - 1, piece);
    board.getChildren().add(piece);
    Bounds bound = getBoundsbyIndex(0);
    piece.placeObject(bound.getMinX(), bound.getMinY());
  }

  /**
   * Called by BuyHousePopUp if users decide to buy a house.
   *
   * @param property: int, index of the property they selected
   */
  public void buildHouse(int property) {
    House house = new House();
    board.getChildren().add(house);
    Bounds bound = getBoundsbyIndex(property);
    house.placeObject(bound.getMinX(), bound.getMinY());
  }

  /**
   * TODO: need to either have the piece know its current index, or get the result from model.
   *
   * @param newIdx         index of the new property the player should move to
   * @param currentPlayer: int of player whose piece needs to be moved
   */
  public void movePlayer(int newIdx, int currentPlayer) {
    GamePiece piece = playerPieces.get(currentPlayer);
    Bounds bound = getBoundsbyIndex(newIdx);
    piece.placeObject(bound.getMinX(), bound.getMinY());
  }

  public Bounds getBoundsbyIndex(int idx) {
    HashMap<Integer, Location> map = model.getIndexToLocationMap();
    Location loc = map.get(Integer.valueOf(idx));
    return grid.getCellBounds(loc.col(), loc.row());
  }
}
