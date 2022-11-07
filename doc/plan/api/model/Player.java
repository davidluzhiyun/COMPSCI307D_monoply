package model;

import java.util.Collection;

public interface Player {
  int getPlayId();
  int getCurrentSpace();
  Boolean isInJail();
  int remainingJailTurns();
  Collection<Property> getProperties();
  int getTotalMoney();
}
