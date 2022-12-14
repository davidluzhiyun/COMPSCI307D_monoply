package ooga.model.gamearchive;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import ooga.model.exception.BadDataException;
import ooga.model.player.ControllerPlayer;
import ooga.model.ModelOutput;
import ooga.model.exception.MonopolyException;
import ooga.model.place.ControllerPlace;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameSaver {
    private ModelOutput data;
    private Map<String, Object> loadData = new HashMap<>();
    private Map<String, LinkedTreeMap> initConfigJsonMap;

    public GameSaver(ModelOutput modelOutput, Map<String, LinkedTreeMap> initConfigJsonMap) {
        this.data = modelOutput;
        this.initConfigJsonMap = initConfigJsonMap;
    }

    public void saveToJson() throws IOException {
        Metadata meta = new Metadata(data.getPlayers().size(), data.getCurrentPlayerId());
        loadData.put("initConfig", initConfigJsonMap);
        loadData.put("meta", meta);
        loadData.put("places", buildPlaces());
        loadData.put("players", buildPlayers());

        writeDataFile();
    }

    private void writeDataFile() throws IOException {
        String filePath = "./data/paused_games/loaddata.json";
        Writer writer = new FileWriter(filePath);
        // convert map to JSON File
        new Gson().toJson(loadData, writer);
        writer.close();
    }

    private List<PlaceSaver> buildPlaces() {
        List<PlaceSaver> placeData = new ArrayList<>();
        List<ControllerPlace> places = data.getBoard();
        for (ControllerPlace place : places) {
            Integer ownerId;
            try {ownerId = place.getOwnerId();
            }
            catch (MonopolyException e){
                ownerId = null;
            }
            Integer houseCount;
            try {houseCount = place.getHouseCount();
            }
            catch (MonopolyException e){
                houseCount = null;
            }
            PlaceSaver singlePlaceData = new PlaceSaver(place.getPlaceId(), ownerId, houseCount);
            placeData.add(singlePlaceData);
        }
        return placeData;
    }

    private List<PlayerSaver> buildPlayers() {
        List<PlayerSaver> playerData = new ArrayList<>();
        List<ControllerPlayer> players = data.getPlayers();
        for (ControllerPlayer player : players) {
            PlayerSaver singlePlayerData = new PlayerSaver(player.getPlayerId(),player.getTotalMoney(), player.remainingJailTurns(),
                player.getCurrentPlaceIndex(), player.getPlayerId(), player.hasNextDice(), player.getOwnedRailroadCount(), player.getPropertyIndices(), player.isAlive());
             playerData.add(singlePlayerData);
        }
        return playerData;
    }

    //For test purpose
    protected Map<String, Object> getJsonMap() {
        return loadData;
    }
}

