package ooga.model.place;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ooga.model.*;
import ooga.model.exception.CannotBuildHouseException;
import ooga.model.exception.NoColorAttributeException;

import java.io.*;
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
  private final int placeId;
  private Collection<ConcretePlayer> players;
  private Collection<StationaryAction> inherentStationaryActions;
  private Collection<PlaceAction> inherentPlaceActions;
  public static final String DEFAULT_RESOURCE_PACKAGE = AbstractPlace.class.getPackageName() + ".";
  public static final String DEFAULT_RESOURCE_FOLDER =
          "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/");
  private Map<String, ?> config;

  public AbstractPlace(int id) {
    placeId = id;
    players = new ArrayList<>();
    inherentStationaryActions = new ArrayList<>();
    Gson gson = new Gson();
    Reader reader = null;
    try {
      File file = new File("." + "/src/main/resources" + DEFAULT_RESOURCE_FOLDER + id + ".json");
      reader = new FileReader(file);
      TypeToken<Map<String, ?>> mapType = new TypeToken<>() {
      };
      config = gson.fromJson(reader, mapType);
//      for (Map.Entry<String, ?> entry : config.entrySet()) {
//        System.out.println(entry.getKey() + "=" + entry.getValue());
//        // close reader
//        reader.close();
//      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  protected Map<String, ?> getConfig() {
    return config;
  }


  @Override
  public int getPlaceId() {
    return placeId;
  }


  public Collection<? extends ViewPlayer> getViewPlayers() {
    return players;
  }

  @Override
  public Collection<? extends Player> getPlayers() {
    return players;
  }

  @Override
  public double getMoney() {
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
    Collection<StationaryAction> actions = getCommonTurnBasedStationaryAction(player);
    actions.addAll(inherentStationaryActions);
    return actions;
  }

  @Override
  public Collection<PlaceAction> getPlaceActions(Player player) {
    return inherentPlaceActions;
  }

  @Override
  public int getHouseCount() throws CannotBuildHouseException {
    throw new CannotBuildHouseException();
  }

  @Override
  public int getColorSetId() throws NoColorAttributeException {
    throw new NoColorAttributeException();
  }

  public void addStationaryAction(StationaryAction stationaryAction) {
    this.inherentStationaryActions.add(stationaryAction);
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
}
