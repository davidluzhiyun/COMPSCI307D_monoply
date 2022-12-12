package ooga.event.command;

import ooga.controller.PayRentRecord;

public class PayRentCommand implements Command{

    private PayRentRecord record;

    public PayRentCommand(PayRentRecord record) {
        this.record = record;
    }

    @Override
    public Object getCommandArgs() {
        return this.record;
    }
}
