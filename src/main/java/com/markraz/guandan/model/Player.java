package com.markraz.guandan.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Player {
    private final List<Card> playerCards = new ArrayList<>();

    public Player() {}

    public void resetHand(){
        playerCards.clear();
    }

    public void setPlayerHand(List<Card> playerCards) {
        this.resetHand();
        this.playerCards.addAll(new ArrayList<>(playerCards));
    }

    public void removeCard(Card card) {
        if (!playerCards.remove(card)) {
            throw new IllegalArgumentException("Card not in hand");
        }
    }

    public List<Card> getPlayerCards() {
        return new ArrayList<>(playerCards);
    }

    @Override
    public String toString() {
        StringBuilder playerHandString = new StringBuilder("Player's cards in hand:\n");
        for (Card card : playerCards) {
            playerHandString.append(card).append("\n");
        }
        return playerHandString.toString();
    }
}
