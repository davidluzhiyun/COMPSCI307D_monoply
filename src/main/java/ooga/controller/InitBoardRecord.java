package ooga.controller;

import ooga.model.StationaryAction;

import java.util.Collection;
import java.util.List;

public record InitBoardRecord(int rows, int cols, List<ParsedProperty> places, Collection<StationaryAction> stationaryActions, List<ooga.model.ControllerPlayer> players, int currentPlayerId) {
}
