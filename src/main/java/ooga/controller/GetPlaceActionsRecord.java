package ooga.controller;

import ooga.model.PlaceAction;

import java.util.List;

public record GetPlaceActionsRecord(List<PlaceAction> actions, int index) {
}
