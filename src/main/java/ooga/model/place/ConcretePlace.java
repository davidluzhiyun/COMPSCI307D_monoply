package ooga.model.place;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ooga.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public abstract class ConcretePlace implements Place, ViewPlace {
  private final int placeId;
  private List<ConcretePlayer> players;
  private List<StationaryAction> stationaryActions;
  private List<PlaceAction> placeActions;
  public static final String DEFAULT_RESOURCE_PACKAGE = ConcretePlace.class.getPackageName() + ".";
  public static final String DEFAULT_RESOURCE_FOLDER =
    "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/");
  private Map<String, ?> config;

  public ConcretePlace(int id) {
    placeId = id;
    players = new ArrayList<>();
    stationaryActions = new ArrayList<>();
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

  @Override
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

  @Override
  public List<StationaryAction> getStationaryActions() {
    return stationaryActions;
  }

  @Override
  public List<PlaceAction> getPlaceAction(Player player) {
    return placeActions;
  }

  public void addStationaryAction(StationaryAction stationaryAction) {
    this.stationaryActions.add(stationaryAction);
  }

  public void addPlaceAction(PlaceAction placeAction) {
    this.placeActions.add(placeAction);
  }
}
