package ooga.event.command;

import ooga.controller.ParsedProperty;

import java.util.ArrayList;

public class GameStartCommand implements Command{

    private ParsedProperty[] parsedConfig;

    public GameStartCommand(ParsedProperty[] parsedJson){
        parsedConfig = parsedJson;
    };

    @Override
    public Object getCommandArgs() {
        return parsedConfig;
    }
}
