package ooga.view.components;

import java.util.ResourceBundle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import ooga.Main;
import ooga.view.scene.SceneManager;

public class House extends ImageView implements BoardObject {

  private final ResourceBundle myResources;
  public static final String HOUSE_IMAGE = "house.png";
  public static final String HOUSE_SIZE_KEY = "HouseSize";
  public static final String HOTEL_IMAGE = "hotel.png";

  public House() {
    this.myResources = ResourceBundle.getBundle(
        Main.DEFAULT_RESOURCE_PACKAGE + SceneManager.USER_INTERFACE);
    Image image = new Image(HOUSE_IMAGE);
    this.setImage(image);
    this.setPreserveRatio(true);
    int size = Integer.parseInt(myResources.getString(HOUSE_SIZE_KEY));
    this.setFitHeight(size);
    this.setOpacity(0);
  }

  @Override
  public void placeObject(int xLocation, int yLocation) {
    this.setOpacity(0);
    Translate translate = new Translate();
    translate.setX(xLocation);
    translate.setY(yLocation);
    this.getTransforms().add(translate);
    this.setOpacity(1.0);
  }

  @Override
  public void rotateObject(double angleToRotate) {
    this.setOpacity(1.0);
    Rotate rotate = new Rotate();
    rotate.setAngle(angleToRotate);
    this.getTransforms().add(rotate);
  }

  /**
   * Method to upgrade a house to a hotel by changing the image.
   */
  public void upgradeToHotel() {
    this.setOpacity(0);
    Image image = new Image(HOTEL_IMAGE);
    this.setImage(image);
    this.setPreserveRatio(true);
    int size = Integer.parseInt(myResources.getString(HOUSE_SIZE_KEY));
    this.setFitHeight(size);
    this.setOpacity(1.0);
  }
}
