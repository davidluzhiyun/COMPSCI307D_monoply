package ooga.view.components;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Builder;

public class MonopolyCardBuilder implements Builder<Region> {

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
    } else if (loc == 2) {
      setRotation(180);
    } else if (loc == 3) {
      setRotation(270);
    }
  }

  @Override
  public Region build() {
    StackPane card = new StackPane();
    styleCard(card);
    card.getChildren().addAll(createColor(), createTextLabel());

    return card;
  }

  private void styleCard(StackPane card) {
    card.setBorder(new Border(
        new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
            new BorderWidths(1, 1, 1, 1))));
  }

  private Pane createColor() {

    Pane colorCard = new Pane();
    colorCard.setBackground(Background.fill(Color.web(intToHexColor(model.getColor()))));
    colorCard.setMaxSize(model.getWidth(), model.getHeight() * 0.3);
    setBorder(colorCard, 0, 0, 2, 0);

//    Group ret = new Group(colorCard);
    if (location == 0) {
      StackPane.setAlignment(colorCard, Pos.TOP_CENTER);
    } else if (location == 1) {
      colorCard.setMaxWidth(model.getHeight() * 0.3);
      colorCard.setMaxHeight(model.getWidth());
      setBorder(colorCard, 0, 0, 0, 2);
      StackPane.setAlignment(colorCard, Pos.CENTER_RIGHT);
    } else if (location == 2) {
      colorCard.setRotate(rotation);
      StackPane.setAlignment(colorCard, Pos.BOTTOM_CENTER);
    } else if (location == 3) {
      colorCard.setMaxWidth(model.getHeight() * 0.3);
      colorCard.setMaxHeight(model.getWidth());
      setBorder(colorCard, 0, 2, 0, 0);
      StackPane.setAlignment(colorCard, Pos.CENTER_LEFT);
    }

    return colorCard;
  }

  private Group createTextLabel() {
    ResizableTextLabel label = new ResizableTextLabel(model.getName(), model.getWidth());
    Label text = label.build();
    text.setRotate(rotation);

    Group ret = new Group(text);

    StackPane.setAlignment(ret, Pos.CENTER);

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
