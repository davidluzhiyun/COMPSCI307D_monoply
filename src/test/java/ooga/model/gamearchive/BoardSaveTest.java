package ooga.model.gamearchive;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import ooga.event.GameEventHandler;
import ooga.model.*;
import ooga.model.place.ControllerPlace;
import ooga.model.place.Place;
import ooga.model.place.property.ConcreteStreet;
import ooga.model.player.BuildHouseCheckerNoColor;
import ooga.model.player.ConcretePlayer;
import ooga.model.player.ControllerPlayer;
import ooga.model.player.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static ooga.model.place.AbstractPlace.DEFAULT_RESOURCE_FOLDER;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardSaveTest {
    static ModelOutput output;
    static Map<String, LinkedTreeMap> initConfigJsonMap;

    @BeforeAll
    static void TestModelOutput() {
        File file = new File("." + "/src/main/resources" + DEFAULT_RESOURCE_FOLDER + "InitialConfig" + ".json");
        initConfigJsonMap = parseJSON(file);
        Place place2 = new ConcreteStreet("123", new GameEventHandler());
//        place2.setOwner(1);
        //TODO:
        Player player1 = new ConcretePlayer(0, new GameEventHandler(), new BuildHouseCheckerNoColor());
        output = new ModelOutput() {
            @Override
            public GameState getGameState() {
                return null;
            }

            @Override
            public Point getDiceNum() {
                return null;
            }

            @Override
            public int getCurrentPlayerId() {
                return 1;
            }

            @Override
            public List<ControllerPlayer> getPlayers() {
                return List.of(player1, new ConcretePlayer(1, new GameEventHandler(), new BuildHouseCheckerNoColor()));
            }
            @Override
            public List<ControllerPlace> getBoard() {
                return List.of(new ConcreteStreet("121", new GameEventHandler()),place2);
            }

            @Override
            public Collection<StationaryAction> getStationaryAction() {
                return null;
            }

            @Override
            public int getQueryIndex() {
                return -1;
            }
        };
    }

    private static Map<String, LinkedTreeMap> parseJSON(File file) {
        try (Reader reader = new FileReader(file)) {
            // Convert JSON File to Java Object
            return new Gson().fromJson(reader, Map.class);
        } catch (FileNotFoundException e) {
            System.out.println("Config file not found1");
        } catch (IOException e) {
            System.out.println("IOException thrown1");
        }
        return null;
    }

    @Test
    void testPlayerCount() throws IOException {
        GameSaver boardSave = new GameSaver(output, initConfigJsonMap);
        boardSave.saveToJson();
        Metadata meta = (Metadata) boardSave.getJsonMap().get("meta");
        assertEquals(2, meta.playerCount());
    }

    @Test
    void testCurrentPlayer() throws IOException {
        GameSaver boardSave = new GameSaver(output, initConfigJsonMap);
        boardSave.saveToJson();
        Metadata meta = (Metadata) boardSave.getJsonMap().get("meta");
        assertEquals(1, meta.currentPlayerId());
    }

    @Test
    void testPlaceId() throws IOException {
        GameSaver boardSave = new GameSaver(output, initConfigJsonMap);
        boardSave.saveToJson();
        List<PlaceSaver> places = (List<PlaceSaver>) boardSave.getJsonMap().get("places");
        assertEquals("121", places.get(0).id());
    }

    @Test
    void testOwnerNoOwner() throws IOException {
        GameSaver boardSave = new GameSaver(output, initConfigJsonMap);
        boardSave.saveToJson();
        List<PlaceSaver> places = (List<PlaceSaver>) boardSave.getJsonMap().get("places");
        assertEquals(-1, places.get(0).owner());
    }

    @Test
    void testOwner() throws IOException {
        GameSaver boardSave = new GameSaver(output, initConfigJsonMap);
        boardSave.saveToJson();
        List<PlaceSaver> places = (List<PlaceSaver>) boardSave.getJsonMap().get("places");
        assertEquals(1, places.get(1).owner());
    }

    @Test
    void testPlayer() throws IOException {
        GameSaver boardSave = new GameSaver(output, initConfigJsonMap);
        boardSave.saveToJson();
        List<PlayerSaver> players = (List<PlayerSaver>) boardSave.getJsonMap().get("players");
        assertEquals(1, players.get(1).id());
    }
}