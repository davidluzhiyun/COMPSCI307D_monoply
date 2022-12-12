package ooga.controller;

import ooga.model.StationaryAction;

import java.util.List;

public record MoveRecord(List<StationaryAction> actions, int placeIndex) {
}
