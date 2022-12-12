package ooga.event.command;

import ooga.model.ControllerPlayer;

public class PayTaxCommand implements Command{

    private ControllerPlayer player;

    public PayTaxCommand(ControllerPlayer player) {
        this.player = player;
    }

    @Override
    public Object getCommandArgs() {
        return this.player;
    }
}
