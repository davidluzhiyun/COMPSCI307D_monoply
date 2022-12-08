package ooga.view.components;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableDoubleValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MonopolyCardViewModel {

  private final ObservableDoubleValue width;
  private final ObservableDoubleValue height;

  public MonopolyCardViewModel(ObservableDoubleValue width, ObservableDoubleValue height) {
    this.width = width;
    this.height = height;
  }

  public ObservableDoubleValue heightProperty() {
    return height;
  }

  public ObservableDoubleValue widthProperty() {
    return width;
  }
}
