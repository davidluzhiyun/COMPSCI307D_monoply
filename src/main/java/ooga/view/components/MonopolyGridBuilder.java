package ooga.view.components;

import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Builder;
import ooga.Main;

public class MonopolyGridBuilder implements Builder<Region> {

  private static final String DEFAULT_RESOURCE_DIR = Main.DEFAULT_RESOURCE_PACKAGE;
  private final ResourceBundle resources = ResourceBundle.getBundle(
      DEFAULT_RESOURCE_DIR + "Board");
  private static final double CORNER_SIZE_PERCENTAGE = 12.5;
  private static final double CARD_SIZE_PERCENTAGE = 100 - (CORNER_SIZE_PERCENTAGE * 2);
  private MonopolyBoardViewModel model;
  private GridPane grid;
  private Color boardColor;

  private int boardWidth;
  private int boardHeight;
  private Pane board;

  public MonopolyGridBuilder(MonopolyBoardViewModel model, Pane board) {
    this.model = model;
    this.board = board;
    initData();
  }

  private void initData() {
    boardColor = Color.web(resources.getString("BoardColor"));
    boardWidth = Integer.parseInt(resources.getString("BoardWidth"));
    boardHeight = Integer.parseInt(resources.getString("BoardHeight"));
  }

  @Override
  public Region build() {
    grid = new GridPane();
    grid.setPrefSize(boardWidth, boardHeight);
    grid.setGridLinesVisible(true);

    grid.setBackground(
        new Background(new BackgroundFill(boardColor, CornerRadii.EMPTY, Insets.EMPTY))
    );

    setBoardConstraints(model.getBoardRow(), model.getBoardCol());

    board.getChildren().add(grid);

    setBoardLogo();

    return board;
  }

  private void setBoardLogo() {
    StackPane p = new StackPane();
    double middleLogoSize = grid.getPrefHeight() * CARD_SIZE_PERCENTAGE / 100;

    p.setPrefSize(middleLogoSize, middleLogoSize);

//    p.setBorder(new Border(
//        new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
//            new BorderWidths(1, 1, 1, 1))));

    Rectangle rect = new Rectangle(middleLogoSize, middleLogoSize);

    rect.setFill(boardColor);
    p.getChildren().add(rect);

    ImageView logo = new ImageView(
        new Image(getClass().getResource("/res/view/monopoly-logo.png").toExternalForm()));
    logo.setPreserveRatio(true);
    logo.setFitWidth(250);
    StackPane.setAlignment(logo, Pos.CENTER);
    p.getChildren().add(logo);

    AnchorPane.setTopAnchor(p, grid.getPrefHeight() * CORNER_SIZE_PERCENTAGE / 100);
    AnchorPane.setLeftAnchor(p, grid.getPrefWidth() * CORNER_SIZE_PERCENTAGE / 100);

    board.getChildren().add(p);
  }

  private void setBoardConstraints(int row, int col) {
    setRowConstraints(row);
    setColConstraints(col);
  }

  private void setRowConstraints(int rown) {
    RowConstraints row1 = new RowConstraints();
    row1.setPercentHeight(CORNER_SIZE_PERCENTAGE);
    row1.setPrefHeight(boardHeight * CORNER_SIZE_PERCENTAGE / 100);
    row1.setVgrow(Priority.SOMETIMES);
    grid.getRowConstraints().add(row1);

    for (int i = 1; i <= rown - 2; i++) {
      RowConstraints row = new RowConstraints();
      row.setPercentHeight(CARD_SIZE_PERCENTAGE / (rown - 2));
      row.setVgrow(Priority.SOMETIMES);
      grid.getRowConstraints().add(row);
    }

    RowConstraints row2 = new RowConstraints();
    row1.setPercentHeight(CORNER_SIZE_PERCENTAGE);
    row2.setVgrow(Priority.SOMETIMES);
    grid.getRowConstraints().add(row2);
  }

  private void setColConstraints(int coln) {
    ColumnConstraints col1 = new ColumnConstraints();
    col1.setPercentWidth(CORNER_SIZE_PERCENTAGE);
    col1.setPrefWidth(boardWidth * CORNER_SIZE_PERCENTAGE / 100);
    col1.setHgrow(Priority.SOMETIMES);
    grid.getColumnConstraints().add(col1);

    for (int i = 1; i <= coln - 2; i++) {
      ColumnConstraints col = new ColumnConstraints();
      col.setPercentWidth(CARD_SIZE_PERCENTAGE / (coln - 2));
      col.setHgrow(Priority.SOMETIMES);
      grid.getColumnConstraints().add(col);
    }

    ColumnConstraints col2 = new ColumnConstraints();
    col2.setPercentWidth(CORNER_SIZE_PERCENTAGE);
    col2.setHgrow(Priority.SOMETIMES);
    grid.getColumnConstraints().add(col2);
  }
}
