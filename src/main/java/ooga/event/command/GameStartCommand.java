package ooga.event.command;

import javafx.util.Pair;
import ooga.controller.ParsedProperty;

import java.util.Map;

public class GameStartCommand implements Command{

    private Map<String, Pair<String, ?>> parsedConfig;

    public GameStartCommand(Map parsedJson){
        parsedConfig = parsedJson;
    };

    @Override
    public Object getCommandArgs() {
        return parsedConfig;
    }
}
