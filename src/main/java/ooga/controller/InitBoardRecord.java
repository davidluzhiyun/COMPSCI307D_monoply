package ooga.controller;

import java.util.Collection;
import java.util.List;

public record InitBoardRecord(List<ParsedProperty> places, List<String> stationaryActions, Collection<PlayerRecord> players, int currentPlayerId) {
}
