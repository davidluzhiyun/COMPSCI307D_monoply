package ooga.view.components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Builder;

public class MonopolyCardBuilder implements Builder<Region> {

  private final double defaultFontSize = 13;
  private final double MAX_TEXT_WIDTH = 300;
  private final Font defaultFont = Font.font(defaultFontSize);
  private MonopolyCardViewModel model;
  private GridPane grid;
  private int location;
  private double rotation;

  public MonopolyCardBuilder(GridPane grid) {
    this.grid = grid;
  }

  public void setModel(MonopolyCardViewModel model) {
    this.model = model;
  }

  public void setLocation(int loc) {
    location = loc;
  }

  @Override
  public Region build() {
    StackPane card = new StackPane();
    styleCard(card);
//    card.getChildren().addAll(createColor(), createTextLabel(), createPriceLabel(), createImage());
    if (model.getType().equals("Street")) {
      card.getChildren().addAll(createColor(), createTextLabel());
    }

    return card;
  }

  private void styleCard(StackPane card) {
    card.getStyleClass().add("card");
    card.setMaxHeight(model.getHeight());
    card.setMinHeight(model.getHeight());
    card.setMaxWidth(model.getWidth());
    card.setMaxWidth(model.getWidth());
    card.setBorder(new Border(
        new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
            new BorderWidths(1, 1, 1, 1))));
  }

  private Pane createColor() {
    // only create color for street cards
    Pane colorCard = new Pane();
    colorCard.setBackground(Background.fill(Color.web(intToHexColor(model.getColor()))));

    // this needs to change based on location
    if (location == 0) {
      colorCard.setMaxWidth(model.getWidth());
      colorCard.setMaxHeight(model.getHeight() * 0.3);
      setBorder(colorCard, 0, 0, 2, 0);
      StackPane.setAlignment(colorCard, Pos.TOP_CENTER);
    } else if (location == 1) {
      colorCard.setMaxWidth(model.getWidth() * 0.3);
      colorCard.setMaxHeight(model.getHeight());
      setBorder(colorCard, 0, 0, 0, 2);
      StackPane.setAlignment(colorCard, Pos.CENTER_RIGHT);
    } else if (location == 2) {
      colorCard.setMaxWidth(model.getWidth());
      colorCard.setMaxHeight(model.getHeight() * 0.3);
      setBorder(colorCard, 2, 0, 0, 0);
      StackPane.setAlignment(colorCard, Pos.BOTTOM_CENTER);
    } else if (location == 3) {
      colorCard.setMaxWidth(model.getWidth() * 0.3);
      colorCard.setMaxHeight(model.getHeight());
      setBorder(colorCard, 0, 2, 0, 0);
      StackPane.setAlignment(colorCard, Pos.CENTER_LEFT);
    }
    return colorCard;
  }

  private Label createTextLabel() {
    final TextField tf = new TextField(model.getName());
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
    StackPane.setAlignment(city, Pos.CENTER);

    if (location == 1 || location == 3) {
      city.setRotate(rotation);
    }

    return city;
  }

  private void setBorder(Pane pane, int t, int r, int b, int l) {
    pane.setBorder(new Border(
        new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
            new BorderWidths(t, r, b, l))));
  }

  private String intToHexColor(int color) {
    return String.format("#%06X", (0xFFFFFF & color));
  }

  public void setRotation(double angle) {
    this.rotation = angle;
  }
}
