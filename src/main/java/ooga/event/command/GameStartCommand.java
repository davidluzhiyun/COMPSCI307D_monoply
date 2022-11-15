package ooga.event.command;

import com.google.gson.internal.LinkedTreeMap;
import javafx.util.Pair;
import ooga.controller.ParsedProperty;

import java.util.Hashtable;
import java.util.Map;

public class GameStartCommand implements Command{

    private Map<String, LinkedTreeMap> parsedConfig;

    public GameStartCommand(Map parsedJson){
        parsedConfig = parsedJson;
    };

    @Override
    public Object getCommandArgs() {
        return parsedConfig;
    }
}
