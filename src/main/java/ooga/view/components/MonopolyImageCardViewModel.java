package ooga.view.components;

public class MonopolyImageCardViewModel {

  private double width;
  private double height;
  private double rotation;
  private String upperText;
  private String bottomText;
  private String imageString;

  public MonopolyImageCardViewModel(double width, double height) {
    this.width = width;
    this.height = height;
  }

  public String getImageString() {
    return imageString;
  }

  public void setImageString(String imageString) {
    this.imageString = imageString;
  }


  public double getWidth() {
    return width;
  }

  public void setWidth(double width) {
    this.width = width;
  }

  public double getHeight() {
    return height;
  }

  public void setHeight(double height) {
    this.height = height;
  }

  public double getRotation() {
    return rotation;
  }

  public void setRotation(double rotation) {
    this.rotation = rotation;
  }

  public String getUpperText() {
    return upperText;
  }

  public void setUpperText(String upperText) {
    this.upperText = upperText;
  }

  public String getBottomText() {
    return bottomText;
  }

  public void setBottomText(String bottomText) {
    this.bottomText = bottomText;
  }
}
