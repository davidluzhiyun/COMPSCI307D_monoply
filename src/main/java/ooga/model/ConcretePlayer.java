package ooga.model;


import java.util.HashSet;
import ooga.model.place.Place;

import java.util.ArrayList;
import java.util.Collection;

public class ConcretePlayer implements Player, ControllerPlayer {
    private double money;
    private int playerId;
    private int currentPlaceIndex;
    private boolean isInJail = false;
    private int dicesLeft;
    private int dicesTotal;
    private int remainingJailTurns;
    private final Collection<Integer> properties;

    /**
     * Universal constructor for loading the game./
     * @param money
     * @param playerId
     * @param currentPlaceIndex
     * @param isInJail
     * @param dicesLeft
     * @param dicesTotal
     * @param remainingJailTurns
     * @param properties
     */
    public ConcretePlayer(double money, int playerId, int currentPlaceIndex, boolean isInJail,
        int dicesLeft, int dicesTotal, int remainingJailTurns, Collection<Integer> properties){
        this.money = money;
        this.playerId = playerId;
        this.currentPlaceIndex = currentPlaceIndex;
        this.isInJail = isInJail;
        this.dicesLeft = dicesLeft;
        this.properties = properties;
    }

    public ConcretePlayer(int playerId) {
        this.currentPlaceIndex = 0;
        this.money = 0;
        this.playerId = playerId;
        properties = new ArrayList<>();
    }

    public void newTurn() {
        dicesLeft = 1;
        dicesTotal = 1;
        //TODO: when in jail
    }

    @Override
    public void earnMoney(double money) {
        this.money += money;
    }

    public void decrementOneDiceLeft() {
        dicesLeft--;
    }

    public void addOneDiceRoll() {
        dicesLeft++;
        dicesTotal++;
        if (dicesTotal == 4)
            isInJail = true;
    }

    public boolean hasNextDice() {
        return dicesLeft > 0;
    }

    public boolean goJail() {
        return isInJail;
    }

    @Override
    public int getPlayerId() {
        return 0;
    }

    @Override
    public int getCurrentPlaceIndex() {
        return currentPlaceIndex;
    }

    @Override
    public Boolean isInJail() {
        return isInJail;
    }

    @Override
    public int remainingJailTurns() {
        return remainingJailTurns;
    }

    @Override
    public Collection<Integer> getPropertyIndices() {
        return new HashSet<>(properties);
    }

    @Override
    public double getTotalMoney() {
        return money;
    }

    /**
     * By Luyao Wang, updated by Zhiyun Lu
     * Method moves the player to specified index. Doesn't check if the index is legal because
     * the player class is blind to the board. The job of calculating the correct index is of
     * playerTurn
     * @param destinationIndex the index the player should be moved to.
     */
    @Override
    public void move(int destinationIndex) {
        //Auto part
        currentPlaceIndex = destinationIndex;
    }

    @Override
    public void purchase(Place property, int propertyIndex) {
        try {
            money -= property.getPurchasePrice();
            properties.add(propertyIndex);
            property.purchaseBy(playerId);
        } catch (IllegalStateException e) {
            throw new IllegalStateException();
        }
    }
}
