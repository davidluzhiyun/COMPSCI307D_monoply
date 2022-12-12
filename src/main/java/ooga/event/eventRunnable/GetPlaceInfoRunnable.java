package ooga.event.eventRunnable;

import com.google.gson.internal.LinkedTreeMap;
import ooga.controller.Controller;
import ooga.controller.ParsedProperty;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventType;
import ooga.event.command.Command;
import ooga.event.command.GameStartCommand;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public class GetPlaceInfoRunnable extends ParsingJsonRunnable implements EventGenerator {

    private int queriedPlaceIndex;

    private Map<String, LinkedTreeMap> parsedJson;

    public GetPlaceInfoRunnable (Command arguments) {
        this.queriedPlaceIndex = (int) arguments.getCommandArgs();
    }

    @Override
    public GameEvent processEvent() {
        List<ParsedProperty> places = Controller.getControllerPlaces();
        String fileName = PLACE_PATH + places.get(this.queriedPlaceIndex).id() + JSON_EXTENSION;
        try {
            File file = getFileFromResource(fileName);
            this.parsedJson = parseJSON(file);
        } catch (URISyntaxException e) {
            System.out.println("Couldn't get property file!");
        }
        GameStartCommand placeInfo = new GameStartCommand(parsedJson);
        return GameEventHandler.makeGameEventwithCommand(GameEventType.CONTROLLER_TO_VIEW_GET_PLACE_INFO.name(), placeInfo);
    }
}
