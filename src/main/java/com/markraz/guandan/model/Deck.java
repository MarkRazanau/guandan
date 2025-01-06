package com.markraz.guandan.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representing a game deck consisting of two combined decks of 54 cards (108 cards total)
 */
public class Deck {

    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        for (Suit suit : Suit.values()) {
            if (suit == Suit.JOKER) continue;
            for (Rank rank : Rank.values()) {
                if (rank != Rank.REDJOKER && rank != Rank.BLACKJOKER) {
                    this.cards.add(new Card(rank, suit));
                    this.cards.add(new Card(rank, suit));
                }
            }
        }
        this.cards.add(new Card(Rank.REDJOKER, Suit.JOKER));
        this.cards.add(new Card(Rank.REDJOKER, Suit.JOKER));
        this.cards.add(new Card(Rank.BLACKJOKER, Suit.JOKER));
        this.cards.add(new Card(Rank.BLACKJOKER, Suit.JOKER));

        this.shuffleDeck();
    }

    /**
     * Shuffle the deck of cards to ensure randomized order.
     */
    public void shuffleDeck(){
        Collections.shuffle(this.cards);
    }

    /**
     * Returns a list containing each of the four players' hand of cards.
     * @return a list of 4 sublists representing each player's hand of cards each sublist containing
     *         the same quantity of cards from the randomly shuffled deck of cards.
     */
    public List<List<Card>> splitPlayerCards(){
        List<List<Card>> playerCardsSplit = new ArrayList<>();
        int playerHandSize = this.cards.size() / 4; // Each player will get 27 cards when playing with a deck of 108 cards
        for (int i = 0; i < 4; i++) {
            playerCardsSplit.add(new ArrayList<>(this.cards.subList(i * playerHandSize, (i + 1) * playerHandSize)));
        }
        return playerCardsSplit;
    }
}
