package ooga.controller;

import ooga.model.StationaryAction;

import java.awt.*;
import java.util.Collection;
import java.util.List;

public record UpdateViewRecord(Point dice, List<ooga.model.place.Place> places, Collection<StationaryAction> stationaryActions, List<ooga.model.ViewPlayer> players, int currentPlayerId) {
}
