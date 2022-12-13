package ooga.model.component;

import java.awt.Point;
import ooga.model.component.ConcreteDice;

class ManipulableDice extends ConcreteDice {
  private int r1;
  private int r2;
  void set(int r1, int r2){
    this.r1 = r1;
    this.r2 = r2;
  }
  @Override
  public Point roll(){
    return new Point(r1,r2);
  }

}
