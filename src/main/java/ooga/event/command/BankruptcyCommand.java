package ooga.event.command;

import ooga.controller.BankruptcyRecord;

public class BankruptcyCommand implements Command{

    private BankruptcyRecord record;

    public BankruptcyCommand(BankruptcyRecord record) {
        this.record = record;
    }

    @Override
    public Object getCommandArgs() {
        return this.record;
    }
}
