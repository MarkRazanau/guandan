package com.markraz.guandan.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.EnumMap;

/**
 * The TeamLevel class stores the current level card of a team, as well as the respective values of each rank card
 * based on the level.
 */
public class TeamLevel {

    private static final Logger logger = LoggerFactory.getLogger(TeamLevel.class);

    private Rank levelCard;
    private final EnumMap<Rank, Integer> rankValues = new EnumMap<>(Rank.class);

    /**
     * Creates a new TeamLevel, usually upon game initialization, setting the TWO card as the level card
     * for the first round of the game. The initial rank values will then be computed with this in mind.
     */
    public TeamLevel(){
        this.levelCard = Rank.TWO;
        this.updateRankValues();
    }

    /**
     * Change the level card to a new Rank card.
     * @param newLevelCard the new level card to set (must not be null).
     * @throws IllegalArgumentException if newLevelCard is null.
     */
    public void updateTeamLevel(Rank newLevelCard){
        if (newLevelCard == null) {
            throw new IllegalArgumentException("New level card cannot be null, must be of type Rank");
        }
        this.levelCard = newLevelCard;
        this.updateRankValues();
        logger.info("New level card updated to {}", this.levelCard);
    }

    /**
     * Update the values of the 15 Rank cards, mapping each Rank to a unique value.
     * The value of the level card will always be 13, while those of the red and black joker will be 14 and 155 respectively.
     * All other values are assigned incrementally starting from 1.
     */
    private void updateRankValues(){
        this.rankValues.clear();
        // The levelCard will always be right before the Red Joker, so its value can automatically be set to 13
        int levelCardValue = 13;
        int i = 1;
        for (Rank rank : Rank.values()) {
            if (rank == this.levelCard) {
                rankValues.put(rank, levelCardValue);
            } else if (rank == Rank.REDJOKER) {
                rankValues.put(rank, levelCardValue + 1);
            } else if (rank == Rank.BLACKJOKER) {
                rankValues.put(rank, levelCardValue + 2);
            } else {
                rankValues.put(rank, i);
                i += 1;
            }
        }
        logger.info("Rank values updated for level card: {}", this.levelCard);
    }

    /**
     * Return the value of the current state's level card.
     * @param rank the current level card.
     * @return the integer value of the rank card.
     */
    public int getRankValue(Rank rank){
        return this.rankValues.get(rank);
    }

    /**
     * Return the current state's level card.
     * @return the Rank representing the current level card.
     */
    public Rank getLevelCard(){
        return this.levelCard;
    }
}
