package Poker.logic;

import Poker.components.Card;
import Poker.components.Player;
import Poker.util.PokerHands;
import Poker.util.Suits;

import java.util.HashMap;

public class PlayerHandLogic {
    static public PokerHands calcHandRank(Player player) {
        HashMap<Suits, Integer> suitsMap = HandMapper.suitsMapper(player);
        HashMap<Integer, Integer> valuesMap = HandMapper.valueMapper(player);
        return HandRanking.getHandRank(suitsMap, valuesMap);
    }
}

// todo add very detailed documentation

class HandMapper {
    static public HashMap<Integer, Integer> valueMapper(Player player) {

        HashMap<Integer, Integer> valuesMap = new HashMap<>();
        for (Card card : player.getHand()) {
            valuesMap.computeIfPresent(card.getValue(), (k,v) -> v += 1 );
            valuesMap.putIfAbsent(card.getValue(), 1);
        }
        return valuesMap;
    }
    static public HashMap<Suits, Integer> suitsMapper(Player player) {

        HashMap<Suits, Integer> suitsMap = new HashMap<>();
        suitsMap.put(Suits.Clubs, 0);
        suitsMap.put(Suits.Diamonds, 0);
        suitsMap.put(Suits.Hearts, 0);
        suitsMap.put(Suits.Spades, 0);

        for (Card card : player.getHand()) {
                //todo add documentation
                Suits temp = card.getSuit();
                suitsMap.merge(temp, 1, Integer::sum);
            }
        return suitsMap;
    }
}
