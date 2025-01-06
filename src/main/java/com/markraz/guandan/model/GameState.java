package com.markraz.guandan.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private final static Logger logger = LoggerFactory.getLogger(GameState.class);

    // Player turn tracker between four players, must be an int between 0 and 3 inclusive
    private int playerTurn = 0;
    private final List<Player> players = new ArrayList<>();
    private final TeamLevel teamOneLevel = new TeamLevel();
    private final TeamLevel teamTwoLevel = new TeamLevel();

    private final Deck deck = new Deck();

    public GameState() {
        for (int i = 0; i < 4; i++) {
            players.add(new Player());
        }
    }

    /**
     * Setup a new round of GuanDan:
     * - reset/reshuffle the deck of cards
     * - hand out new hands of cards for players
     */
    public void setupNewRound(){
        deck.shuffleDeck();
        List<List<Card>> playerHands = deck.splitPlayerCards();
        for (int i = 0; i < playerHands.size(); i++) {
            players.get(i).setPlayerHand(playerHands.get(i));
        }
    }

    /**
     * Increment the playerTurn by 1, indicating moving the turn to the following player.
     * If the next player has a hand size of 0 (no more cards to play), than move onto the next player that has a non-empty hand.
     */
    public void nextPlayerTurn() {
        int numberOfPlayers = 4;
        for (int i = 0; i < numberOfPlayers; i++) {
            playerTurn = ((playerTurn + 1) % numberOfPlayers);
            if (!players.get(playerTurn).getPlayerCards().isEmpty()) {
                return;
            }
        }
        logger.info("Game round complete, only one player with hand cards left.");
    }


}
