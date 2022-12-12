package ooga.model.gamearchive;

import ooga.model.*;
import ooga.model.place.ControllerPlace;
import ooga.model.place.Place;
import ooga.model.place.property.ConcreteStreet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardSaveTest {
    static ModelOutput output;

    @BeforeAll
    static void TestModelOutput() {
        Place place2 = new ConcreteStreet("123");
//        place2.setOwner(1);
        //TODO:
        Player player1 = new ConcretePlayer(0);
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
                return List.of(player1, new ConcretePlayer(1));
            }
            @Override
            public List<ControllerPlace> getBoard() {
                return List.of(new ConcreteStreet("121"),place2);
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

    @Test
    void testPlayerCount() throws IOException {
        GameSaver boardSave = new GameSaver(output);
        boardSave.saveToJson();
        Metadata meta = (Metadata) boardSave.getJsonMap().get("meta");
        assertEquals(2, meta.playerCount());
    }

    @Test
    void testCurrentPlayer() throws IOException {
        GameSaver boardSave = new GameSaver(output);
        boardSave.saveToJson();
        Metadata meta = (Metadata) boardSave.getJsonMap().get("meta");
        assertEquals(1, meta.currentPlayerId());
    }

    @Test
    void testPlaceId() throws IOException {
        GameSaver boardSave = new GameSaver(output);
        boardSave.saveToJson();
        List<PlaceSaver> places = (List<PlaceSaver>) boardSave.getJsonMap().get("places");
        assertEquals("121", places.get(0).id());
    }

    @Test
    void testOwnerNoOwner() throws IOException {
        GameSaver boardSave = new GameSaver(output);
        boardSave.saveToJson();
        List<PlaceSaver> places = (List<PlaceSaver>) boardSave.getJsonMap().get("places");
        assertEquals(-1, places.get(0).owner());
    }

    @Test
    void testOwner() throws IOException {
        GameSaver boardSave = new GameSaver(output);
        boardSave.saveToJson();
        List<PlaceSaver> places = (List<PlaceSaver>) boardSave.getJsonMap().get("places");
        assertEquals(1, places.get(1).owner());
    }

    @Test
    void testPlayer() throws IOException {
        GameSaver boardSave = new GameSaver(output);
        boardSave.saveToJson();
        List<PlayerSaver> players = (List<PlayerSaver>) boardSave.getJsonMap().get("players");
        assertEquals(1, players.get(1).id());
    }
}