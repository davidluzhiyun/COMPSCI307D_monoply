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

    MonopolyCardBuilder builder = new MonopolyCardBuilder(grid);

    drawBottom(map, models, builder);
    drawLeft(map, models, builder);
    drawTop(map, models, builder);
    drawRight(map, models, builder);
  }

  private void drawBottom(HashMap<Integer, Location> map, List<MonopolyCardViewModel> models,
      MonopolyCardBuilder builder) {
    for (int i = 0; i < model.getBoardCol() - 1; i++) {
      MonopolyCardViewModel model = models.get(i);
      Location loc = map.get(Integer.valueOf(i));
      Bounds bounds = grid.getCellBounds(loc.col(), loc.row());
      model.setWidth(bounds.getWidth());
      model.setHeight(bounds.getHeight());
      builder.setModel(model); // get the type from here
      builder.setLocation(0); // indicate bottom
      Pane card = (Pane) builder.build();
      int index = i;
      card.setOnMouseClicked(e -> dealWithMouseClick(index));
      grid.add(card, loc.col(), loc.row());
      currIdx = i;
    }
  }

  private void drawLeft(HashMap<Integer, Location> map, List<MonopolyCardViewModel> models,
      MonopolyCardBuilder builder) {
    for (int i = currIdx + 1; i < model.getBoardCol() - 1 + model.getBoardRow() - 1; i++) {
      MonopolyCardViewModel model = models.get(i);
      Location loc = map.get(Integer.valueOf(i));
      Bounds bounds = grid.getCellBounds(loc.col(), loc.row());
      model.setWidth(bounds.getWidth());
      model.setHeight(bounds.getHeight());
      builder.setModel(model); // get the type from here
      builder.setLocation(1); // indicate bottom
      builder.setRotation(this.model.getRotationFromIdx(i));
      Pane card = (Pane) builder.build();
      int index = i;
      card.setOnMouseClicked(e -> dealWithMouseClick(index));
      grid.add(card, loc.col(), loc.row());
      currIdx = i;
    }

  }

  private void drawTop(HashMap<Integer, Location> map, List<MonopolyCardViewModel> models,
      MonopolyCardBuilder builder) {
    for (int i = currIdx + 1; i < model.getNumCards() - (model.getBoardRow() - 1) + 1; i++) {
      MonopolyCardViewModel model = models.get(i);
      Location loc = map.get(Integer.valueOf(i));
      Bounds bounds = grid.getCellBounds(loc.col(), loc.row());
      model.setWidth(bounds.getWidth());
      model.setHeight(bounds.getHeight());
      builder.setModel(model); // get the type from here
      builder.setLocation(2); // indicate bottom
      Pane card = (Pane) builder.build();
      int index = i;
      card.setOnMouseClicked(e -> dealWithMouseClick(index));
      grid.add(card, loc.col(), loc.row());
      currIdx = i;
    }

  }

  private void drawRight(HashMap<Integer, Location> map, List<MonopolyCardViewModel> models,
      MonopolyCardBuilder builder) {
    for (int i = currIdx; i < model.getNumCards(); i++) {
      MonopolyCardViewModel model = models.get(i);
      Location loc = map.get(Integer.valueOf(i));
      Bounds bounds = grid.getCellBounds(loc.col(), loc.row());
      model.setWidth(bounds.getWidth());
      model.setHeight(bounds.getHeight());
      builder.setModel(model); // get the type from here
      builder.setLocation(3); // indicate bottom
      builder.setRotation(this.model.getRotationFromIdx(i));
      Pane card = (Pane) builder.build();
      int index = i;
      card.setOnMouseClicked(e -> dealWithMouseClick(index));
      grid.add(card, loc.col(), loc.row());
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
    GamePiece piece = playerPieces.get(currentPlayer - 1);
    Bounds bound = getBoundsbyIndex(newIdx);
    piece.placeObject(bound.getMinX(), bound.getMinY());
  }

  public Bounds getBoundsbyIndex(int idx) {
    HashMap<Integer, Location> map = model.getIndexToLocationMap();
    Location loc = map.get(Integer.valueOf(idx));
    return grid.getCellBounds(loc.row(), loc.col());
  }
}
