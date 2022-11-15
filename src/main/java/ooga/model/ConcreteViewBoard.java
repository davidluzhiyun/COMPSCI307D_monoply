package ooga.model;

import ooga.model.place.AbstractPlace;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConcreteViewBoard implements ViewBoard{
  List<ViewBoard> viewBoardList = new ArrayList<>();

  public ConcreteViewBoard(List<AbstractPlace> places) {
    ViewPlace view = places.get(1);
  }

  @Override
  public int getSize() {
    return 0;
  }

  @Override
  public ViewPlace getPlaceAt() {
    return null;
  }

  @Override
  public Iterator<ViewPlace> iterator() {
    return null;
  }
}
