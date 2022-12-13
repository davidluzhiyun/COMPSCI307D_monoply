package ooga.view.components;

public class MonopolyImageCardViewModel extends MonopolyCardViewModel {

  private double rotation;
  private String upperText = "UpperText";
  private String bottomText = "BottomText";
  private String imageString;
  private boolean isCorner = true;

  public MonopolyImageCardViewModel(String type, String name, String image, String upperText,
      String lowerText, boolean corner) {
    super(type, name);
    this.imageString = image;
    this.upperText = upperText;
    this.bottomText = lowerText;
    this.isCorner = corner;
  }

  public String getImageString() {
    return imageString;
  }

  public void setImageString(String imageString) {
    this.imageString = imageString;
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

  public boolean isCorner() {
    return isCorner;
  }

  public void setCorner(boolean corner) {
    isCorner = corner;
  }
}
