package ooga.event.command;

import ooga.model.player.ControllerPlayer;

public class GetPlayerCommand implements Command{

    private ControllerPlayer player;

    public GetPlayerCommand(ControllerPlayer player) {
        this.player = player;
    }

    @Override
    public Object getCommandArgs() {
        return this.player;
    }
}
