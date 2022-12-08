package ooga.view.components;

import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.util.Builder;

public class MonopolyCardBuilder implements Builder<Region> {

  private final MonopolyCardViewModel model;

  public MonopolyCardBuilder(MonopolyCardViewModel model) {
    this.model = model;
//    this.clickHandler = clickHandler;
  }

  @Override
  public Region build() {
    StackPane card = new StackPane();
    styleCard(card);
//    card.getChildren().addAll(createColor(), createTextLabel(), createPriceLabel(), createImage());

    return card;
  }

  private void styleCard(StackPane card) {
    card.getStyleClass().add("card");
    card.maxHeightProperty().bind(model.heightProperty());
    card.maxWidthProperty().bind(model.widthProperty());
    card.minHeightProperty().bind(model.heightProperty());
    card.minWidthProperty().bind(model.widthProperty());
//    card.setBackground(new Background(new BackgroundFill(model.getBackground(), null, null)));
  }
}
