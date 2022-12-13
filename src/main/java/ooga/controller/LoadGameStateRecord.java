package ooga.controller;

import ooga.model.ControllerPlayer;

import java.util.List;

public record LoadGameStateRecord(List<ControllerPlayer> players, List<Integer> numHouses) {
}
