package ooga.view.scene;

import java.util.Objects;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ooga.Main;
import ooga.view.api.ChildView;
import ooga.view.api.ParentView;
import ooga.view.components.MonopolyBoardBuilder;
import ooga.view.components.MonopolyBoardViewModel;

public class MonopolyGameEditorScene extends MonopolyScene implements ParentView<ChildView> {

  private MonopolyBoardViewModel model;
  private Region monopolyBoard;

  public MonopolyGameEditorScene(Stage primaryStage) {
    super(new AnchorPane());
    rootPane.getStylesheets().add(
        Objects.requireNonNull(Main.class.getResource("/style/editor.css")).toString());

  }

  @Override
  public void addChild(ChildView child) {
    rootPane.getChildren().add(child.getView());
  }

  public void initChildren() {
    model = new MonopolyBoardViewModel();
    monopolyBoard = new MonopolyBoardBuilder(model).build();
  }

  public void setChildrenLocation() {
  }

  public void addChildrenToRoot() {
    rootPane.getChildren().addAll(monopolyBoard);
  }
}
