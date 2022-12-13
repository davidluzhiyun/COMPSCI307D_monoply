package ooga.view.components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Builder;
import ooga.Main;

public class MonopolyImageCardBuilder implements Builder<Region> {

  private static final String ROOT_IMAGE_DIR = "/res/view/";
  private MonopolyImageCardViewModel model;
  private int location;

  public MonopolyImageCardBuilder() {
  }

  public void setModel(MonopolyImageCardViewModel model) {
    this.model = model;
  }

  @Override
  public Region build() {
    StackPane card = new StackPane();
    styleCard(card);
    if (model.isCorner()) {
      card.getChildren().addAll(createImageView());
    } else {
      card.getChildren().addAll(createUpperLabel(), createImageView(), createBottomLabel());
    }
    card.getChildren().forEach(child -> child.setRotate(model.getRotation()));
    return card;
  }

  private void styleCard(StackPane card) {
    card.setMaxSize(model.getWidth(), model.getHeight());
    card.setMinSize(model.getWidth(), model.getHeight());
    card.setBorder(new Border(
        new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
            new BorderWidths(1, 1, 1, 1))));

  }

  private double calculateHypotenuse(double adjacent, double opposite) {
    return Math.sqrt((adjacent * adjacent) + (opposite * opposite));
  }

  private double getTextWidth() {
    if (model.isCorner()) {
      return calculateHypotenuse(model.getHeight() / 3, model.getWidth() / 3);
    } else {
      return isTopOrBottom() ? model.getWidth() : model.getHeight();
    }
  }

  private boolean isTopOrBottom() {
    return location % 2 == 0;
  }

  private Label createUpperLabel() {
    ResizableTextLabel label = new ResizableTextLabel(model.getUpperText(), getTextWidth());
    Label ret = label.build();

    // set the AnchorPane value from
    StackPane.setAlignment(ret, Pos.TOP_CENTER);

    return ret;
  }

  private ImageView createImageView() {
    ImageView iv = new ImageView(
        new Image(getClass().getResource(ROOT_IMAGE_DIR + model.getImageString()).toExternalForm())
    );
    iv.setPreserveRatio(true);
    iv.setFitWidth(model.getWidth() * 0.8);
    iv.setFitHeight(model.getHeight() * 0.5);
    StackPane.setAlignment(iv, Pos.CENTER);

    return iv;
  }

  private Label createBottomLabel() {
    ResizableTextLabel label = new ResizableTextLabel(model.getBottomText(), getTextWidth());
    Label ret = label.build();

    // set the AnchorPane value from
    StackPane.setAlignment(ret, Pos.BOTTOM_CENTER);
    return ret;
  }

  public void setLocation(int location) {
    this.location = location;
  }
}
