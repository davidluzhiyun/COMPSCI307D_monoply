package ooga.view.scene;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ooga.view.api.ChildView;
import ooga.view.api.ParentView;

public class MonopolyGameEditorScene extends MonopolyScene implements ParentView<ChildView> {

  public MonopolyGameEditorScene(Stage primaryStage) {
    super(new AnchorPane());
  }

  @Override
  public void addChild(ChildView child) {
    rootPane.getChildren().add(child.getView());
  }

  public void initChildren() {
  }

  public void setChildrenLocation() {
  }

  public void addChildrenToRoot() {
  }
}
