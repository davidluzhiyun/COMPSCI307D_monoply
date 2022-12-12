package ooga.model.place;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.beans.EventHandler;

import ooga.model.ConcretePlayer;
import ooga.model.ControllerPlayer;
import ooga.model.PlaceAction;
import ooga.model.Player;
import ooga.model.StationaryAction;
import ooga.model.exception.CannotBuildHouseException;
import ooga.model.exception.MonopolyException;
import ooga.model.exception.NoColorAttributeException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * abstract class representing generic place
 *
 * @author david_luzhiyun Modified the class and design
 * @author Luyao Wang original class
 */
public abstract class AbstractPlace implements Place {
  private final String placeId;
  private Collection<ConcretePlayer> players;
  private Collection<PlaceAction> inherentPlaceActions;
  private Collection<PlaceAction> placeActions;
  public static final String PLACE_PACKAGE_NAME = AbstractPlace.class.getPackageName() + ".";
  public static final String DEFAULT_RESOURCE_FOLDER =
      "/" + PLACE_PACKAGE_NAME.replace(".", "/");
  private Map<String, ?> config;

  public AbstractPlace(String id) {
    placeId = id;
    players = new ArrayList<>();
    inherentPlaceActions = List.of(PlaceAction.VIEW_INFO);
    placeActions = new ArrayList<>();
    Gson gson = new Gson();
    Reader reader;
    try {
      File file = new File("." + "/src/main/resources" + DEFAULT_RESOURCE_FOLDER + id + ".json");
      reader = new FileReader(file);
      TypeToken<Map<String, ?>> mapType = new TypeToken<>() {
      };
      config = gson.fromJson(reader, mapType);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  protected Map<String, ?> getConfig() {
    return config;
  }

  @Override
  public String getPlaceId() {
    return placeId;
  }


  public Collection<? extends ControllerPlayer> getViewPlayers() {
    return players;
  }

  @Override
  public Collection<ControllerPlayer> getPlayers() {
    return new ArrayList<>(players);
  }

  @Override
  public double getMoney(Player player) {
    return 0;
  }

  /**
   * Rewritten by
   *
   * @param player the current player
   * @return
   * @author David Lu
   */
  @Override
  public Collection<StationaryAction> getStationaryActions(Player player) {
    Collection<StationaryAction> stationaryActionList = getCommonTurnBasedStationaryAction(player);
    stationaryActionList.addAll(getPlaceBasedStationaryActions(player));
    return stationaryActionList;
  }

  @Override
  public Collection<StationaryAction> getPlaceBasedStationaryActions(Player player) {
    return new ArrayList<>();
  }

  @Override
  public void updateCurrentPlayerPlaceActions(Player player) {
    placeActions.clear();
    placeActions.addAll(inherentPlaceActions);
  }

  @Override
  public double getPurchasePrice() throws MonopolyException {
    throw new MonopolyException("cannotPurchaseException");
  }

  @Override
  public void setOwner(int playerId, Player owner) throws MonopolyException {
    throw new MonopolyException("cannotPurchaseException");
  }

  @Override
  public void setHouseCount(int count) throws MonopolyException {
    throw new MonopolyException("cannotBuildHouseException");
  }

  /**
   * @return
   * @author David Lu
   * Returns updated place actions. Modification of the collection doesn't affect the model
   * The method is also used for modifying the collection by subclasses
   */
  @Override
  public Collection<PlaceAction> getPlaceActions() {
    return placeActions;
  }

  @Override
  public int getHouseCount() throws MonopolyException {
    throw new MonopolyException("cannotBuildHouseException");
  }
  @Override
  public double getHousePrice() throws MonopolyException {
    throw new MonopolyException("cannotBuildHouseException");
  }

  @Override
  public int getColorSetId() throws MonopolyException {
    throw new MonopolyException("noColorAttributeException");
  }

  @Override
  public int getOwnerId() throws MonopolyException {
    throw new MonopolyException("cannotPurchaseException");
  }

  /**
   * Helper method for getting turn related stationary actions
   *
   * @param player current player playing
   * @return A collection of stationary actions
   * @author David Lu
   * Modified based on code from defunct class StationaryActionManager from
   * @author Luyao Wang
   */
  protected Collection<StationaryAction> getCommonTurnBasedStationaryAction(Player player) {
    List<StationaryAction> stationaryActionList = new ArrayList<>();
    if (player.hasNextDice())
      stationaryActionList.add(StationaryAction.ROLL_DICE);
    else
      stationaryActionList.add(StationaryAction.END_TURN);
    return stationaryActionList;
  }

  public void addPlaceAction(PlaceAction placeAction) {
    this.inherentPlaceActions.add(placeAction);
  }

  /**
   * @author David Lu
   * Fetch a copy of the inherentPlaceActions for subclass
   */

  @Override
  public void landingEffect(Player player) {
    EventHandler eventHandler;
  }
}
