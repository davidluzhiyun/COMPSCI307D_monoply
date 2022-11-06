package ooga.event.command;

import ooga.view.SampleViewData;

public class SampleCommand implements Command<SampleViewData>{
  private final SampleViewData d;

  public SampleCommand(SampleViewData d){
    this.d = d;
  }

  @Override
  public SampleViewData getCommandArgs() {
    return d;
  }
}
