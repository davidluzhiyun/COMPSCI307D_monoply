package ooga.model;

import java.util.Collection;

/**
 * Info that might be updated each round
 */
public interface Output{
  DiceResult getDiceResult();
  Collection<StationaryAction> getStaticActions();

}
