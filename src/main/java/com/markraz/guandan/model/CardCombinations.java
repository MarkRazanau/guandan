package com.markraz.guandan.model;

import java.util.HashMap;
import java.util.List;

public enum CardCombinations {
    //SINGLE,
    //PAIR, // red joker and black joker is not a valid pair
    //TRIPLE,
    //FULL_HOUSE, //consists of a triple and a pair, ranked by the triple in level order
    //STRAIGHT, // five consecutive single cards in nature order, ranked by the highest card in nature order
    //TUBE, // three consecutive pairs in nature order, ranked by the highest cardin nature order
    //PLATE, // two consecutive triples in nature order, ranked by the highest card in nature order

    // The following are bombs (can be played no matter the current rule)
    //QUADRUPLE,
    //QUINTUPLE,
    STRAIGHT_FLUSH, // five consecutive cards of the same suit in nature order
    //SEXTUPLE,
    //SEPTUPLE,
    //OCTUPLE,
    //NONUPLE,
    //DECUPLE,
    //JOKER_BOMB, // combination of two red joker and two black jokers
}