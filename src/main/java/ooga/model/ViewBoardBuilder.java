package ooga.model;

import java.util.Collection;
import ooga.model.place.AbstractPlace;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ooga.model.place.Place;

/**
 * Builds a ViewPlace from list of Place, a player(likely the current player) and a PlayerTurn
 * @author David Lu
 */

public class ViewBoardBuilder implements ViewBoard{
  List<ViewBoard> viewBoardList = new ArrayList<>();

  public ViewBoardBuilder(List<AbstractPlace> places) {
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

  /**
   * Inner data class for helping
   * Builds a ViewPlace from a Place, a player and a PlayerTurn
   */
  private static class ViewPlaceBuilder implements ViewPlace {
    private Place place;
    private PlayerTurn turn;
    private Player player;
    public ViewPlaceBuilder(Place place,PlayerTurn turn,Player player){
      this.place = place;
      this.turn = turn;
      this.player = player;
    }

    @Override
    public Collection<? extends ViewPlayer> getViewPlayers() {

    }

    @Override
    public int getHousesNum() {
      return 0;
    }

    @Override
    public Collection<PlaceAction> getPlaceActions() {
      return null;
    }
  }
}
