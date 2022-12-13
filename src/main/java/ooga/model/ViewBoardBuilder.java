package ooga.model;

import java.util.Collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ooga.model.exception.CannotBuildHouseException;
import ooga.model.place.Place;
import ooga.model.player.ControllerPlayer;
import ooga.model.player.Player;

/**
 * Builds a ViewPlace from list of Place, a player(likely the current player) and a PlayerTurn
 * @author David Lu
 */
@Deprecated
public class ViewBoardBuilder implements ViewBoard{
  private List<ViewPlace> viewBoardList;

  //Using iterable instead of wrapper because it is already immutable
  public ViewBoardBuilder(Iterable<Place> places, Player player) throws IllegalStateException{
    try {
      viewBoardList = new ArrayList<>();
      for (Place place: places) {
        viewBoardList.add(new ViewPlaceBuilder(place,player));
      }
    }
    catch (NullPointerException e){
      //TODO: refactor message to property files
      throw new IllegalStateException("ViewBoardBuilder need non null inputs",e);
    }
  }

  @Override
  public int getSize() {
    return viewBoardList.size();
  }

  @Override
  public ViewPlace getPlaceAt(int i) {
    return viewBoardList.get(i);
  }

  @Override
  public Iterator<ViewPlace> iterator() {
    return viewBoardList.listIterator();
  }

  /**
   * Inner data class for helping
   * Builds a ViewPlace from a Place, a player and a PlayerTurn
   */
  @Deprecated
  private static class ViewPlaceBuilder implements ViewPlace {
    private final Place place;
    private final Player player;
    public ViewPlaceBuilder(Place place,Player player){
      this.place = place;
      this.player = player;
    }

    @Override
    public Collection<? extends ControllerPlayer> getViewPlayers() {
      return place.getPlayers();
    }

    @Override
    public int getHousesNum() {
      try {
        return place.getHouseCount();
      }
      catch (CannotBuildHouseException e) {
//        LOGGER.info(e.getMessage());
        return 0;
      }
    }

    @Override
    public Collection<PlaceAction> getPlaceActions() {
      place.updateCurrentPlayerPlaceActions(player);
      return place.getPlaceActions();
    }
  }
}
