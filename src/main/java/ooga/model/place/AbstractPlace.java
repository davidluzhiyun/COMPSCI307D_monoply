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
    private final String placeId;
    private Collection<ConcretePlayer> players;
    private Collection<StationaryAction> inherentStationaryActions;
    private Collection<StationaryAction> stationaryActions;
    private Collection<PlaceAction> inherentPlaceActions;

    private Collection<PlaceAction> updatedPlaceActions;
    public static final String PLACE_PACKAGE_NAME = AbstractPlace.class.getPackageName() + ".";
    public static final String DEFAULT_RESOURCE_FOLDER =
            "/" + PLACE_PACKAGE_NAME.replace(".", "/");
    private Map<String, ?> config;

    public AbstractPlace(String id) {
        placeId = id;
        players = new ArrayList<>();
        inherentStationaryActions = new ArrayList<>();
        stationaryActions = new ArrayList<>();
        Gson gson = new Gson();
        Reader reader = null;
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
        updateStationaryActions(player);
        return stationaryActions;
    }

    private void updateStationaryActions(Player player) {
        stationaryActions = getCommonTurnBasedStationaryAction(player);
        stationaryActions.addAll(inherentStationaryActions);
    }

    @Override
    public void updatePlaceActions(Player player) {
        updatedPlaceActions = new ArrayList<>(inherentPlaceActions);
    }

    @Override
    public double getPurchasePrice() throws IllegalStateException {
        throw new IllegalStateException();
    }

    @Override
    public void purchaseBy(Player player) throws IllegalStateException {
        throw new IllegalStateException();
    }

    @Override
    public Collection<PlaceAction> getPlaceActions() {
        return updatedPlaceActions;
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
