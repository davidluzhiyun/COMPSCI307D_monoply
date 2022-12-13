package ooga.view;

public class SampleViewData {

  private final String initString = "GAME_START";
  private int randomData;

  public SampleViewData(int randomData) {
    this.randomData = randomData;
  }

  public int getData() {
    return randomData;
  }
}
