package ooga.controller;

import ooga.model.StationaryAction;
import ooga.model.player.ControllerPlayer;

import java.util.Collection;
import java.util.List;

public record InitBoardRecord(int rows, int cols, List<ParsedProperty> places, Collection<StationaryAction> stationaryActions, List<ControllerPlayer> players, int currentPlayerId) {
}
