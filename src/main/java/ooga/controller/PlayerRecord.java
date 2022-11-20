package ooga.controller;

import ooga.model.place.property.Property;

import java.util.ArrayList;
import java.util.Collection;

public record PlayerRecord(int playerId, int currentPlaceId, boolean isInJail, int remainingJailTurns, Collection<Property> ownedProperties, double totalMoney) {
}
