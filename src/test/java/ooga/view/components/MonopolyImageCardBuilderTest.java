package ooga.view.components;

import static org.junit.jupiter.api.Assertions.*;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class MonopolyImageCardBuilderTest extends DukeApplicationTest {

  private Pane card;
  private MonopolyImageCardViewModel model;

  @BeforeEach
  void setUp() {
  }

  @Override
  public void start(Stage stage) throws InterruptedException {
//    model = new MonopolyImageCardViewModel(100, 300);
//    model.setUpperText("hosung");
//    model.setImageString("railroad.png");
//    model.setRotation(0.0);
//    model.setBottomText("kim");
//    MonopolyImageCardBuilder builder = new MonopolyImageCardBuilder(model);
//    this.card = (Pane) builder.build();
//    Group root = new Group(card);
//    Scene scene = new Scene(root, 1080, 720);
//    stage.setScene(scene);
//    stage.show();
  }

  @Test
  void test() {
    sleep(100000);
  }
}