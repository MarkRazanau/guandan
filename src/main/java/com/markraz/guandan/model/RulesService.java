package com.markraz.guandan.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class RulesService {
    private CardCombinations currentRule;
    private Rank highestRank;
    private TeamLevel currentTeamLevel;

    public RulesService() {}

    public void setCurrentTeamLevel(TeamLevel currentTeamLevel) {
        this.currentTeamLevel = currentTeamLevel;
    }
    public void setCurrentRule(CardCombinations currentRule) {
        this.currentRule = currentRule;
    }

    private boolean isConsecutiveNaturalOrder(HashMap<Rank, Integer> numCardsPerRank) {
        List<Rank> cardRanks = new ArrayList<>(numCardsPerRank.keySet());
        List<Integer> cardRankValues = new ArrayList<>(cardRanks.stream().map(Enum::ordinal).toList());
        Collections.sort(cardRankValues);
        for (int i = 1; i < cardRankValues.size(); i++) {
            if (cardRankValues.get(i) - cardRankValues.get(i-1) != 1) return false;
        }
        return true;
    }

    private CardCombinations getCardCombination(List<Card> playedCards) {
        HashMap<Rank, Integer> numCardsPerRank = new HashMap<>();
        if (playedCards == null || playedCards.isEmpty()) {
            throw new IllegalArgumentException("playedCards is null or empty");
        }

        // Determine the number of cards for each rank played as a hashmap for easier comparison
        for (Card card : playedCards) {
            numCardsPerRank.compute(card.getRank(), (k, v) -> v == null ? 1 : v + 1);
        }

        switch (playedCards.size()){
            case 1:
                return CardCombinations.SINGLE;
            case 2:
                if (numCardsPerRank.size() == 1) return CardCombinations.PAIR;
                throw new IllegalArgumentException("These two cards are not a valid combination.");
            case 3:
                if (numCardsPerRank.size() == 1) return CardCombinations.TRIPLE;
                throw new IllegalArgumentException("These three cards are not a valid combination.");
            case 4:
                switch (numCardsPerRank.size()) {
                    case 1:
                        return CardCombinations.QUADRUPLE;
                    case 2:
                        if (numCardsPerRank.containsValue(2) &&
                                numCardsPerRank.containsKey(Rank.REDJOKER) &&
                                numCardsPerRank.containsKey(Rank.BLACKJOKER)) return CardCombinations.JOKER_BOMB;
                    default:
                        throw new IllegalArgumentException("These four cards are not a valid combination.");
                }
            case 5:
                switch (numCardsPerRank.size()) {
                    case 1:
                        return CardCombinations.QUINTUPLE;
                    case 2:
                        if (numCardsPerRank.containsValue(3) &&
                                numCardsPerRank.containsValue(2)) return CardCombinations.FULL_HOUSE;
                        throw new IllegalArgumentException("These five cards are not a valid combination.");
                    case 5:
                        if (this.isConsecutiveNaturalOrder(numCardsPerRank)) return CardCombinations.STRAIGHT;
                        throw new IllegalArgumentException("These five cards are not a valid combination.");
                }
            case 6:
                switch (numCardsPerRank.size()) {
                    case 1:
                        return CardCombinations.SEXTUPLE;
                    case 2:
                        if (this.isConsecutiveNaturalOrder(numCardsPerRank)) return CardCombinations.PLATE;
                        throw new IllegalArgumentException("These six cards are not a valid combination.");
                    case 3:
                        if (this.isConsecutiveNaturalOrder(numCardsPerRank)) return CardCombinations.TUBE;
                        throw new IllegalArgumentException("These six cards are not a valid combination.");
                }
            case 7:
                if (numCardsPerRank.size() == 1) return CardCombinations.SEPTUPLE;
                throw new IllegalArgumentException("These seven cards are not a valid combination.");
            case 8:
                if (numCardsPerRank.size() == 1) return CardCombinations.OCTUPLE;
                throw new IllegalArgumentException("These eight cards are not a valid combination.");
            case 9:
                if (numCardsPerRank.size() == 1) return CardCombinations.NONUPLE;
                throw new IllegalArgumentException("These nine cards are not a valid combination.");
            case 10:
                if (numCardsPerRank.size() == 1) return CardCombinations.DECUPLE;
                throw new IllegalArgumentException("These ten cards are not a valid combination.");
        }

        throw new IllegalArgumentException("The cards do not form a valid combination.");
    }

    public boolean checkValidMove(List<Card> currentCards) {

    }
}
