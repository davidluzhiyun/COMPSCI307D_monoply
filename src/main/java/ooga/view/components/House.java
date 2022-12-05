package ooga.view.components;

import java.util.ResourceBundle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ooga.Main;

public class House extends ImageView implements BoardObjects {
  private final ResourceBundle myResources;

  public House() {
    this.myResources = ResourceBundle.getBundle(
        Main.DEFAULT_RESOURCE_PACKAGE + "UserInterface");
    Image image = new Image(myResources.getString("HouseImage"));
    this.setImage(image);
    this.setPreserveRatio(true);
    int size = Integer.parseInt(myResources.getString("HouseSize"));
    this.setFitHeight(size);
  }

  @Override
  public void placeObject(int xLocation, int yLocation) {

  }

  @Override
  public void rotateObject(double angleToRotate) {

  }
}
