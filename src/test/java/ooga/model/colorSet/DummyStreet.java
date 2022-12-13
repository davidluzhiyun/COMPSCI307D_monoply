package ooga.model.colorSet;

import ooga.model.player.Player;
import ooga.model.place.property.Street;

import java.util.List;

public class DummyStreet extends DummyPlace implements Street {
  private int color;
  DummyStreet(int color,String id) {
    super(id);
    this.color = color;
  }

  @Override
  public double getHousePrice() {
    return 0;
  }

  @Override
  public void addOneHouse() {

  }

  @Override
  public int getOwnerId() {
    return 0;
  }

  @Override
  public double getPurchasePrice() {
    return 0;
  }

  @Override
  public double getMortgagePrice() {
    return 0;
  }

  @Override
  public void setOwner(int playerId, Player owner) {

  }
  @Override
  public int getColorSetId(){
    return color;
  }

  @Override
  public boolean isMortgaged() {
    return false;
  }

  @Override
  public String getPropertyName() {
    return null;
  }

  @Override
  public String getDescription() {
    return null;
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public List<Double> getRentWithProperties() {
    return null;
  }

  @Override
  public int getHouseCount() {
    return 0;
  }
}
