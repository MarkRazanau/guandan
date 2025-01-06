package com.markraz.guandan.model;

/**
 * Represents a card, that has a unique Rank and Suit.
 */
public class Card {
    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        if (rank == null) {
            throw new IllegalArgumentException("Rank cannot be null, must be of type of Rank");
        } else if (suit == null) {
            throw new IllegalArgumentException("Suit cannot be null, must be of type of Suit");
        }
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * Retrieve the rank of the card instance.
     * @return the rank of the card.
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Retrieve the suit of the card instance.
     * @return the suit of the card.
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Check if two card instances are the same if they have the same suit and rank.
     * @param obj object to check with
     * @return true iff the object is a Card objects with the same Rank and Suit value.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card card = (Card) obj;
        return (card.getRank() == this.getRank() && card.getSuit() == this.getSuit());
    }

    @Override
    public int hashCode() {
        return 31 * this.rank.hashCode() + this.suit.hashCode();
    }

    @Override
    public String toString() {
        return switch (this.suit) {
            case HEARTS -> String.format("%s of ♥", this.rank);
            case CLUBS -> String.format("%s of ♣", this.rank);
            case DIAMONDS -> String.format("%s of ♦", this.rank);
            case SPADES -> String.format("%s of ♠", this.rank);
            case JOKER -> String.format("%s", this.rank);
        };
    }
}
