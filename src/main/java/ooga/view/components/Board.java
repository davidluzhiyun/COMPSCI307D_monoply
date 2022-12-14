package ooga.view.components;

import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Board {

  private static final int ROW = 13 * 4;
  private static final int CELL_SIZE = 12;
  private final double defaultFontSize = 13;
  private final double MAX_TEXT_WIDTH = 60;
  private final Font defaultFont = Font.font(defaultFontSize);
  private Pane ap;
  private GridPane board;

  public Board() {
    ap = new AnchorPane();
    ap.setPrefSize(640, 640);

    createBoard();
//    drawBorders();
    addMonopolyLogo();
    addCards();
  }

  private void addMonopolyLogo() {
    StackPane p = new StackPane();
    p.setPrefSize(478, 478);

    p.setBorder(new Border(
        new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
            new BorderWidths(1, 1, 1, 1))));

    Rectangle rect = new Rectangle(478, 478);

    rect.setFill(Color.web("#DCEDC8"));
    p.getChildren().add(rect);

    ImageView logo = new ImageView(
        new Image(getClass().getResource("/res/view/monopoly-logo.png").toExternalForm()));
    logo.setPreserveRatio(true);
    logo.setFitWidth(250);
    StackPane.setAlignment(logo, Pos.CENTER);
    p.getChildren().add(logo);

    AnchorPane.setTopAnchor(p, Double.valueOf(80));
    AnchorPane.setLeftAnchor(p, Double.valueOf(80));
    ap.getChildren().add(p);
  }

  private void addCards() {
    addBottomCards();
//    addLeftCards();
  }

  private void addBottomCards() {
    int row = 9;
    for (int col = 1; col <= 8; ++col) {
      Pane card = makeCard(0, Color.BROWN, "League of Legends");
      GridPane.setRowIndex(card, row);
      GridPane.setColumnIndex(card, col);
      Bounds boundsInScene = card.localToScene(card.getBoundsInLocal());
//      GridPane.setMargin(card, new Insets(1.0, 0, 0, 0));
      board.getChildren().add(card);
    }
  }

  private void addLeftCards() {
    int col = 0;
    for (int row = 1; row <= 8; ++row) {
      Pane card = makeCard(1, Color.RED, "Paris");
      GridPane.setRowIndex(card, row);
      GridPane.setColumnIndex(card, col);
//      GridPane.setMargin(card, new Insets(.0, 1.0, 0, 0));
      board.getChildren().add(card);
    }

  }

  private StackPane makeCard(int location, Color color, String text) {
    Pane colorCard = new Pane();
    colorCard.setMaxSize(60, 20);
    colorCard.setBorder(new Border(
        new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
            new BorderWidths(0, 0, 2, 0))));
    colorCard.setBackground(Background.fill(color));

    final TextField tf = new TextField(text);
    Label city = new Label();
    city.setFont(defaultFont);
    city.textProperty().addListener((observable, oldValue, newValue) -> {
      //create temp Text object with the same text as the label
      //and measure its width using default label font size
      Text tmpText = new Text(newValue);
      tmpText.setFont(defaultFont);

      double textWidth = tmpText.getLayoutBounds().getWidth();

      //check if text width is smaller than maximum width allowed
      if (textWidth <= MAX_TEXT_WIDTH) {
        city.setFont(defaultFont);
      } else {
        //and if it isn't, calculate new font size,
        // so that label text width matches MAX_TEXT_WIDTH
        double newFontSize = defaultFontSize * MAX_TEXT_WIDTH / textWidth - 1;
        city.setFont(Font.font(defaultFont.getFamily(), newFontSize));
      }

    });
    city.textProperty().bind(tf.textProperty());

    city.setTranslateY(-8);
//    city.setMinWidth(Region.USE_PREF_SIZE);

    Label price = new Label("$90");

    StackPane card = new StackPane(colorCard, city, price);
    card.setBorder(new Border(
        new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
            new BorderWidths(1, 1, 2, 1))));
    StackPane.setAlignment(colorCard, Pos.TOP_CENTER);
    StackPane.setAlignment(city, Pos.CENTER);
    StackPane.setAlignment(price, Pos.BOTTOM_CENTER);
//    card.getChildren().add(colorCard);

    return card;
  }

  private void createBoard() {
    board = new GridPane();
    board.setBackground(
        new Background(new BackgroundFill(Color.web("#DCEDC8"), CornerRadii.EMPTY, Insets.EMPTY)));
//    board.setGridLinesVisible(true);
    ColumnConstraints col1 = new ColumnConstraints();
    col1.setPercentWidth(0);
    col1.setPrefWidth(80);
    col1.setHgrow(Priority.SOMETIMES);
    board.getColumnConstraints().add(col1);

    for (int i = 1; i < 9; i++) {
      ColumnConstraints col = new ColumnConstraints();
      col.setPercentWidth(0);
      col.setPrefWidth(60);
      col.setHgrow(Priority.SOMETIMES);
      board.getColumnConstraints().add(col);
    }
    ColumnConstraints col2 = new ColumnConstraints();
    col2.setPercentWidth(0);
    col2.setPrefWidth(80);
    col2.setHgrow(Priority.SOMETIMES);
    board.getColumnConstraints().add(col2);

    RowConstraints row1 = new RowConstraints();
    row1.setPercentHeight(0);
    row1.setPrefHeight(80);
    row1.setVgrow(Priority.SOMETIMES);
    board.getRowConstraints().add(row1);

    for (int i = 1; i < 9; i++) {
      RowConstraints row = new RowConstraints();
      row.setPercentHeight(0);
      row.setPrefHeight(60);
      row.setVgrow(Priority.SOMETIMES);
      board.getRowConstraints().add(row);
    }

    RowConstraints row2 = new RowConstraints();
    row2.setPercentHeight(0);
    row2.setPrefHeight(80);
    row2.setVgrow(Priority.SOMETIMES);
    board.getRowConstraints().add(row2);

    ap.getChildren().add(board);
  }

  /**
   * Called by GamePiecePopUp whenever a new pop up is created. Places the piece at GO (well
   * eventually it will)
   *
   * @param piece:  the new piece that was created
   * @param player: not used right now, but probably a good idea to create some mapping
   */
  public void initializeGamePiece(GamePiece piece, int player) {
    board.getChildren().add(piece);
    //TODO: change to give the location of go!!!
    piece.placeObject(20, 550);
  }

  /**
   * Called by BuyHousePopUp if users decide to buy a house.
   * TODO: figure out how to get the coordinates of the property in order to place the house there
   *
   * @param property: int, index of the property they selected
   */
  public void buildHouse(int property) {
    House house = new House();
    board.getChildren().add(house);
    //TODO: change to actually get the location of the property
    house.placeObject(30, 10);
  }

  public Pane getBoard() {
    return ap;
  }
}
