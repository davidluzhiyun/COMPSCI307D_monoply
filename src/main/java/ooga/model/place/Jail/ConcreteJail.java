package ooga.model.place.Jail;

import java.util.Collection;

import ooga.event.GameEventHandler;
import ooga.model.player.Player;
import ooga.model.StationaryAction;
import ooga.model.place.AbstractPlace;

public class ConcreteJail extends AbstractPlace implements Jail {

  public ConcreteJail(String id, GameEventHandler gameEventHandler) {
    super(id, gameEventHandler);
  }

  /**
   * Overrides parent to output the StationaryActions for players in jail and just parking
   *
   * @param player the current player
   * @return
   * @author David Lu
   */
  @Override
  public Collection<StationaryAction> getStationaryActions(Player player) {
    Collection<StationaryAction> stationaryActionList = getCommonTurnBasedStationaryAction(player);
    if (stationaryActionList.contains(StationaryAction.ROLL_DICE) && (player.remainingJailTurns() > 0)) {
      stationaryActionList.add(StationaryAction.PAY_FINE);
    }
    return stationaryActionList;
  }
}
