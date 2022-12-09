package ooga.view.pop_ups;


import javafx.stage.Stage;

public class BuyPropertyPopUp extends ActionPopUp {
  private String myProperty;
  private final Stage myStage;

  public BuyPropertyPopUp(String propertyName) {
    this.myProperty = propertyName;
    this.myStage = new Stage();
  }

  @Override
  public void close() {myStage.close();}

  @Override
  public void createScene() {

  }

  @Override
  public void showMessage(String language) {

  }
}
