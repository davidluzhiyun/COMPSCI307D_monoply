package ooga.model.place.property;

import ooga.model.Player;
import ooga.model.StationaryAction;
import ooga.model.place.Place;

import java.util.ArrayList;
import java.util.List;

public class StationaryActionManager {
  public List<StationaryAction> getStationaryAction(Place place, Player player) {
    List<StationaryAction> stationaryActionList = new ArrayList<>();
    if (player.hasNextTurn())
      stationaryActionList.add(StationaryAction.ROLL_DICE);
    else
      stationaryActionList.add(StationaryAction.END_TURN);
    stationaryActionList.addAll(place.getStationaryActions());
    return stationaryActionList;
  }
}
