package ooga.event.command;

import ooga.model.ControllerPlayer;

public class PlayerStartCommand implements Command{

    private ControllerPlayer player;

    public PlayerStartCommand(ControllerPlayer player) {
        this.player = player;
    }

    @Override
    public Object getCommandArgs() {
        return this.player;
    }
}
