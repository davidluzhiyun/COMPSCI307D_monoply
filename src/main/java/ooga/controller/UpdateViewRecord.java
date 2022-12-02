package ooga.controller;

import ooga.model.StationaryAction;
import ooga.model.place.ControllerPlace;

import java.awt.*;
import java.util.Collection;
import java.util.List;

public record UpdateViewRecord(Point dice, int placeIndex, Collection<StationaryAction> stationaryActions, int currentPlayerId) {
}
