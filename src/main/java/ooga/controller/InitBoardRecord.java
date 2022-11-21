package ooga.controller;

import ooga.model.StationaryAction;

import java.util.Collection;
import java.util.List;

public record InitBoardRecord(List<ParsedProperty> places, Collection<StationaryAction> stationaryActions, List<ooga.model.ViewPlayer> players, int currentPlayerId) {
}
