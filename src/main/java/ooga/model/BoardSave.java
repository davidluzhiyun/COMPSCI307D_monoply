package ooga.model;

import com.google.gson.Gson;
import ooga.model.exception.CannotBuildHouseException;
import ooga.model.place.ControllerPlace;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardSave {
    private ModelOutput data;
    Map<String, Object> loadData = new HashMap<>();

    public BoardSave(ModelOutput modelOutput) {
        this.data = modelOutput;
    }

    public void saveToJson() throws IOException {
        Map<String, Object> meta = new HashMap<>();
        loadData.put("meta", meta);
        meta.put("current playerId", data.getCurrentPlayer());//TODO: cannot tell if this player have the option to roll dice
        meta.put("players", data.getPlayers().size());
        loadData.put("places", buildPlaces());
        loadData.put("players", buildPlayers());

        writeDataFile();
    }

    private void writeDataFile() throws IOException {
        // create a writer
        String filePath = "./src/main/resources/loaddata.json";
        Writer writer = new FileWriter(filePath);
        // convert map to JSON File
        new Gson().toJson(loadData, writer);

        // close the writer
        writer.close();
    }

    private List<Map<String, Object>> buildPlaces() {
        List<Map<String, Object>> placeData = new ArrayList<>();
        List<ControllerPlace> places = data.getBoard();
        for (ControllerPlace place : places) {
            Map<String, Object> singlePlaceData = new HashMap<>();
            singlePlaceData.put("id", place.getPlaceId());
            try {
                singlePlaceData.put("owner", place.getOwnerId());
            } catch (IllegalStateException e) {
                singlePlaceData.put("owner", "Not Defined");
            }
            try {
                singlePlaceData.put("house count", place.getHouseCount());
            } catch (CannotBuildHouseException e) {
                singlePlaceData.put("house count", "Not Defined");
            }
            placeData.add(singlePlaceData);
        }
        return placeData;
    }

    private List<Map<String, Object>> buildPlayers() {
        List<Map<String, Object>> playerData = new ArrayList<>();
        List<ControllerPlayer> players = data.getPlayers();
        for (ControllerPlayer player : players) {
            Map<String, Object> singlePlayerData = new HashMap<>();
            singlePlayerData.put("id", player.getPlayerId());
            singlePlayerData.put("money", player.getTotalMoney());
            singlePlayerData.put("jail", player.isInJail()); //TODO: refactor this method so that returns 1, 2, 3 if in jail and 0 if not so only method is needed related to jail
            singlePlayerData.put("current place index", player.getCurrentPlaceIndex());
            singlePlayerData.put("properties", player.getPropertyIndices());//List of placeIds
            playerData.add(singlePlayerData);
        }
        return playerData;
    }

    //For test purpose
    protected Map<String, Object> getJsonMap() {
        return loadData;
    }
}

