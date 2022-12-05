package ooga.model.gamesaver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ooga.model.ConcretePlayer;
import ooga.model.ControllerPlayer;
import ooga.model.ModelOutput;
import ooga.model.StationaryAction;
import ooga.model.place.ControllerPlace;
import ooga.model.place.property.ConcreteStreet;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardSaveTest {
    ModelOutput output;

    @Test
    void TestModelOutput() throws IOException {
        output = new ModelOutput() {
            @Override
            public Point getDiceNum() {
                return null;
            }

            @Override
            public int getCurrentPlayer() {
                return 1;
            }

            @Override
            public List<ControllerPlayer> getPlayers() {
                return List.of(new ConcretePlayer(0), new ConcretePlayer(1));
            }

            @Override
            public List<ControllerPlace> getBoard() {
                return List.of(new ConcreteStreet("121"),new ConcreteStreet("123"));
            }

            @Override
            public Collection<StationaryAction> getStationaryAction() {
                return null;
            }
        };
        BoardSave boardSave = new BoardSave(output);
        boardSave.saveToJson();
        Metadata meta = (Metadata) boardSave.getJsonMap().get("meta");
        assertEquals(2, meta.playerCount());
    }
}
