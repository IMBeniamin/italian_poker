package Poker.logic;

import Poker.components.Card;
import Poker.components.Player;
import Poker.util.PokerHands;
import Poker.util.Suits;
import javafx.util.Pair;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;

/**
 * Allows the user to interact with the hand rank calculation algorithms which returns the type of hand the user has
 */
public class PlayerHandLogic {
    static public Pair<PokerHands, Integer> calcHandRank(Player player) {
        player.setSuitsMap(HandMapper.suitsMapper(player));
        player.setValuesMap(HandMapper.valueMapper(player));

        return HandRanking.getHandRank(player);
    }
}

/**
 * Contains the methods that generate the maps that are used by the algorithms to calculate the player rank hand
 */
class HandMapper {
    /**
     * Generates a map in which to each value of card in the player's hand is associated the number of occurrences of the same value
     * @param player Player from which data will be extracted
     * @return A map that contains all hand values and their associated cards
     */
    static public HashMap<Integer, List<Card>> valueMapper(Player player) {

        HashMap<Integer, List<Card>> valuesMap = new HashMap<>();
        for (Card card : player.getHand()) {
            if (valuesMap.containsKey(card.getValue())) {
                ArrayList<Card> tempList = new ArrayList<>(valuesMap.get(card.getValue()));
                tempList.add(card);
                valuesMap.put(card.getValue(), tempList);
            }
            valuesMap.putIfAbsent(card.getValue(), Collections.singletonList(card));
        }
        return valuesMap;
    }

    /**
     * Generates a map in which to each suit type is associated the number of occurrences found in the player's hand
     * @param player Player from which data will be extracted
     * @return A map that contains all possible suits values and their associated number of occurrences
     */
    static public HashMap<Suits, List<Card>> suitsMapper(Player player) {

        HashMap<Suits, List<Card>> suitsMap = new HashMap<>();

        suitsMap.put(Suits.Clubs, new ArrayList<>());
        suitsMap.put(Suits.Diamonds, new ArrayList<>());
        suitsMap.put(Suits.Hearts, new ArrayList<>());
        suitsMap.put(Suits.Spades, new ArrayList<>());

        for (Card card : player.getHand()) {
                Suits tempSuit = card.getSuit();
                ArrayList<Card> tempList = new ArrayList<>(suitsMap.get(tempSuit));
                tempList.add(card);
                suitsMap.put(tempSuit, tempList);
        }
        return suitsMap;
    }
}
