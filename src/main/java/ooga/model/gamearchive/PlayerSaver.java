package ooga.model.gamearchive;

import java.util.Collection;

public record PlayerSaver(int id,
                          double money,
                          int jail,
                          //0 for not in jail, 1, 2, 3 for remaining jail turns 1 to 3
                          int currentPlaceIndex,
                          int dicesTotal,
                          boolean hasNextDice,
                          int ownedRailroadCount,
                          Collection<Integer> properties,
                          boolean isAlive) {
}
