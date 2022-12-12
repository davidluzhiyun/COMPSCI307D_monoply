package ooga.view.scene;

import javafx.scene.layout.Pane;

public abstract class MonopolyScene {

  protected Pane rootPane;

  public MonopolyScene(Pane rootPane) {
    this.rootPane = rootPane;
  }

  public Pane getRootPane() {
    return rootPane;
  }

  protected abstract void initChildren();

  protected abstract void setChildrenLocation();

  protected abstract void addChildrenToRoot();
}
