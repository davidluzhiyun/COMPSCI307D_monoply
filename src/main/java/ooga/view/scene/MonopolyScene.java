package ooga.view.scene;

import javafx.scene.layout.Pane;

public abstract class MonopolyScene {

  protected Pane rootPane;

  public MonopolyScene(Pane rootPane) {
    this.rootPane = rootPane;
    initChildren();
    setChildrenLocation();
    addChildrenToRoot();
  }

  public Pane getRootPane() {
    return rootPane;
  }

  protected abstract void initChildren();

  protected abstract void setChildrenLocation();

  protected abstract void addChildrenToRoot();

  //setup
  // 1, 2, 3
}
