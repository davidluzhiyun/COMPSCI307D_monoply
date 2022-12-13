package ooga.controller;

import ooga.model.ControllerPlayer;
import ooga.model.place.ControllerPlace;

import java.util.List;

public record BankruptcyRecord(List<ControllerPlace> places, List<ControllerPlayer> players) {
}
