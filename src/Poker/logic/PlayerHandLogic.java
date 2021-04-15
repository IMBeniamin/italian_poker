package Poker.logic;

import Poker.components.Card;
import Poker.components.Player;
import Poker.util.PokerHands;
import Poker.util.Suits;

import java.util.HashMap;

/**
 * Allows the user to interact with the hand rank calculation algorithms which returns the type of hand the user has
 */
public class PlayerHandLogic {
    static public PokerHands calcHandRank(Player player) {
        HashMap<Suits, Integer> suitsMap = HandMapper.suitsMapper(player);
        HashMap<Integer, Integer> valuesMap = HandMapper.valueMapper(player);
        return HandRanking.getHandRank(suitsMap, valuesMap);
    }
}

/**
 * Contains the methods that generate the maps that are used by the algorithms to calculate the player rank hand
 */
class HandMapper {
    /**
     * Generates a map in which to each value of card in the player's hand is associated the number of occurrences of the same value
     * @param player Player from which data will be extracted
     * @return A map that contains all hand values and their associated values
     */
    static public HashMap<Integer, Integer> valueMapper(Player player) {

        HashMap<Integer, Integer> valuesMap = new HashMap<>();
        for (Card card : player.getHand()) {
            valuesMap.computeIfPresent(card.getValue(), (k,v) -> v += 1 );
            valuesMap.putIfAbsent(card.getValue(), 1);
        }
        return valuesMap;
    }

    /**
     * Generates a map in which to each suit type is associated the number of occurrences found in the player's hand
     * @param player Player from which data will be extracted
     * @return A map that contains all possible suits values and their associated number of occurrences
     */
    static public HashMap<Suits, Integer> suitsMapper(Player player) {

        HashMap<Suits, Integer> suitsMap = new HashMap<>();
        suitsMap.put(Suits.Clubs, 0);
        suitsMap.put(Suits.Diamonds, 0);
        suitsMap.put(Suits.Hearts, 0);
        suitsMap.put(Suits.Spades, 0);

        for (Card card : player.getHand()) {
                Suits temp = card.getSuit();
                suitsMap.merge(temp, 1, Integer::sum);
            }
        return suitsMap;
    }
}
