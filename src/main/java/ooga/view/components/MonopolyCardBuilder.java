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
  private final Font defaultFont = Font.font(defaultFontSize);
  private double MAX_TEXT_WIDTH;
  private MonopolyCardViewModel model;
  private int location;
  private double rotation;

  public MonopolyCardBuilder() {
  }

  public void setModel(MonopolyCardViewModel model) {
    this.model = model;
  }

  public void setLocation(int loc) {
    location = loc;
    if (loc == 1) {
      setRotation(90);
    } else if (loc == 3) {
      setRotation(270);
    }
  }

  @Override
  public Region build() {
    StackPane card = new StackPane();
    if (model.getType().equals("Street")) {
      card.getChildren().addAll(createColor(), createTextLabel());
    }
    styleCard(card);

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

  private double getTextWidth() {
    return (location % 2 == 0) ? model.getWidth() : model.getHeight();
  }

  private Label createTextLabel() {
    ResizableTextLabel label = new ResizableTextLabel(model.getName(), getTextWidth());
    Label ret = label.build();

    StackPane.setAlignment(ret, Pos.CENTER);

    if (location % 2 == 1) {
      ret.setRotate(rotation);
    }

    return ret;
  }

  private void setBorder(Pane pane, int t, int r, int b, int l) {
    pane.setBorder(new Border(
        new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
            new BorderWidths(t, r, b, l))));
  }

  private String intToHexColor(int color) {
    return String.format("#%06X", (0xFFFFFF & color));
  }

  private void setRotation(double angle) {
    this.rotation = angle;
  }
}
