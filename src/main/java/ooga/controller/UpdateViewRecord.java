package ooga.controller;

import ooga.model.player.ControllerPlayer;
import ooga.model.StationaryAction;

import java.awt.*;
import java.util.Collection;

public record UpdateViewRecord(Point dice, int placeIndex, Collection<StationaryAction> stationaryActions, ControllerPlayer currentPlayer, boolean paidRent) {
}
