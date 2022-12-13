package ooga.controller;

import ooga.model.place.ControllerPlace;
import ooga.model.player.ControllerPlayer;

import java.util.List;

public record BankruptcyRecord(List<ControllerPlace> places, List<ControllerPlayer> players) {
}
