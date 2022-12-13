package ooga.view.components;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;

public class MonopolyCardViewModel {

  private int color;
  private final BooleanProperty selected = new SimpleBooleanProperty(false);

  private String type;


  private String name;
  private DoubleProperty width = new SimpleDoubleProperty(0);
  private DoubleProperty height = new SimpleDoubleProperty(0);

  public MonopolyCardViewModel(String type, String name, int color) {
    this.type = type;
    this.name = name;
    this.color = color;
  }

  public MonopolyCardViewModel(String type, String name) {
    this.type = type;
    this.name = name;
  }

  public int getColor() {
    return color;
  }

  public void setColor(int color) {
    this.color = color;
  }

  public double getWidth() {
    return width.get();
  }

  public DoubleProperty widthProperty() {
    return width;
  }

  public void setWidth(double width) {
    this.width.set(width);
  }

  public double getHeight() {
    return height.get();
  }

  public DoubleProperty heightProperty() {
    return height;
  }

  public void setHeight(double height) {
    this.height.set(height);
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
