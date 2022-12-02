package ooga.event.command;

import com.google.gson.internal.LinkedTreeMap;

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
