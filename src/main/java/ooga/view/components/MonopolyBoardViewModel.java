package ooga.view.components;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableDoubleValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.lang3.ObjectUtils.Null;

public class MonopolyBoardViewModel {

  private static final int DEFAULT_MONOPOLY_ROW_SIZE = 11;
  private static final int DEFAULT_MONOPOLY_COL_SIZE = 11;
  private IntegerProperty boardRow;
  private IntegerProperty boardCol;
  private IntegerProperty numCards;
  private HashMap<Location, Integer> locationToIndex;
  private HashMap<Integer, Location> indexToLocation;
  private HashMap<Integer, Double> indexToRotateDegree;
  private final ObservableList<MonopolyCardViewModel> cards = FXCollections.observableArrayList();
  private final ObjectProperty<MonopolyCardViewModel> activeCard = new SimpleObjectProperty<>();

  public MonopolyBoardViewModel() {
    boardRow = new SimpleIntegerProperty(DEFAULT_MONOPOLY_ROW_SIZE);
    boardCol = new SimpleIntegerProperty(DEFAULT_MONOPOLY_COL_SIZE);
    numCards = new SimpleIntegerProperty((boardCol.get() + boardRow.get() - 2) * 2);
    update();
  }

  private void createRotationMapping() {
    indexToRotateDegree = new HashMap<>();
    int idx;
    for (idx = 1; idx <= boardCol.get() - 2; ++idx) {
      indexToRotateDegree.put(Integer.valueOf(idx), 0.0);
    }
    for (idx = idx + 1; idx <= boardRow.get() + boardCol.get() - 3; ++idx) {
      indexToRotateDegree.put(Integer.valueOf(idx), 90.0);
    }
    for (idx = idx + 1; idx <= numCards.get() - boardRow.get(); ++idx) {
      indexToRotateDegree.put(Integer.valueOf(idx), 180.0);
    }
    for (idx = idx + 1; idx <= numCards.get() - 1; ++idx) {
      indexToRotateDegree.put(Integer.valueOf(idx), 270.0);
    }
  }

  public double getRotationFromIdx(int idx) {
    double ret = 0;
    try {
      ret = indexToRotateDegree.get(Integer.valueOf(idx));
    } catch (NullPointerException e) {
      return 0;
    }
    return ret;
  }

  private void createMapping() {
    locationToIndex = new HashMap<>();

    int i;
    int index = 0;

    for (i = getBoardCol() - 1; i >= 0; i--) {
      locationToIndex.put(new Location(getBoardRow() - 1, i), index++);
    }
    for (i = getBoardRow() - 2; i >= 0; i--) {
      locationToIndex.put(new Location(i, 0), index++);
    }
    for (i = 1; i < getBoardCol(); i++) {
      locationToIndex.put(new Location(0, i), index++);
    }
    for (i = 1; i < getBoardRow() - 1; i++) {
      locationToIndex.put(new Location(i, getBoardCol() - 1), index++);
    }
    indexToLocation = (HashMap<Integer, Location>) invertMapUsingStreams(locationToIndex);
  }

  public HashMap<Integer, Location> getIndexToLocationMap() {
    return indexToLocation;
  }

  public int getBoardRow() {
    return boardRow.get();
  }

  public IntegerProperty boardRowProperty() {
    return boardRow;
  }

  public void setBoardRow(int boardRow) {
    this.boardRow.set(boardRow);
  }

  public int getBoardCol() {
    return boardCol.get();
  }

  public IntegerProperty boardColProperty() {
    return boardCol;
  }

  public void setBoardCol(int boardCol) {
    this.boardCol.set(boardCol);
  }

  public int getNumCards() {
    return numCards.get();
  }

  public IntegerProperty numCardsProperty() {
    return numCards;
  }

  public void setNumCards(int numCards) {
    this.numCards.set(numCards);
  }

  public ObservableList<MonopolyCardViewModel> getCardModels() {
    return cards;
  }

  private static <V, K> Map<V, K> invertMapUsingStreams(Map<K, V> map) {
    return map.entrySet()
        .stream()
        .collect(Collectors.toMap(Entry::getValue, Entry::getKey));
  }

  public void update() {
    numCards = new SimpleIntegerProperty((boardCol.get() + boardRow.get() - 2) * 2);
    createMapping();
    createRotationMapping();
  }
}
