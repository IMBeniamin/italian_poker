package Poker.logic;

import Poker.util.PokerHands;
import Poker.util.Suits;

import java.util.*;

//todo add very detailed documentation for this


interface HandRankingCalculator {
    boolean calculate(HashMap<Suits, Integer> suitsMap, HashMap<Integer, Integer> valuesMap);
}

public class HandRanking {
    static final private LinkedHashMap<PokerHands, HandRankingCalculator> map = new LinkedHashMap<>();

    static {
        // It is important to insert the poker hand types from the MOST IMPORTANT to THE LEAST IMPORTANT

        HandRanking.map.put(PokerHands.RoyalFlush, (suitsMap, valuesMap) -> {
            for (int i = 10; i < 15; i++)
                if (!valuesMap.containsKey(i)) return false;
            return suitsMap.containsValue(5);
        });

        HandRanking.map.put(PokerHands.StraightFlush, (suitsMap, valuesMap) ->
                HandRanking.map.get(PokerHands.Straight).calculate(suitsMap, valuesMap)
                        &&
                        suitsMap.containsValue(5));

        HandRanking.map.put(PokerHands.FourOfAKind, (suitsMap, valuesMap) -> valuesMap.containsValue(4));

        HandRanking.map.put(PokerHands.Flush, (suitsMap, valuesMap) -> suitsMap.containsValue(5));

        HandRanking.map.put(PokerHands.FullHouse, (suitsMap, valuesMap) ->
                HandRanking.map.get(PokerHands.OnePair).calculate(suitsMap, valuesMap)
                        &&
                        HandRanking.map.get(PokerHands.ThreeOfAKind).calculate(suitsMap, valuesMap));

        HandRanking.map.put(PokerHands.Straight, (suitsMap, valuesMap) -> {
            for (Integer value : valuesMap.values())
                if ( value > 1 ) return false;

            SortedSet<Integer> keys = new TreeSet<>(valuesMap.keySet());
            return keys.last() - keys.first() == keys.size() - 1;
        });

        HandRanking.map.put(PokerHands.ThreeOfAKind, (suitsMap, valuesMap) -> valuesMap.containsValue(3));

        HandRanking.map.put(PokerHands.TwoPair, (suitsMap, valuesMap) -> {
            int pairs = 0;
            for (Map.Entry<Integer, Integer> CardValue : valuesMap.entrySet())
                if (CardValue.getValue() == 2) pairs += 1;
            return pairs == 2;
        });

        HandRanking.map.put(PokerHands.OnePair, (suitsMap, valuesMap) -> valuesMap.containsValue(2));
    }

    static public PokerHands getHandRank(HashMap<Suits, Integer> suitsMap, HashMap<Integer, Integer> valuesMap) {
        for (Map.Entry<PokerHands, HandRankingCalculator> rank : HandRanking.map.entrySet()) {
            if (rank.getValue().calculate(suitsMap, valuesMap)) {
                return rank.getKey();
            }
        }
        return PokerHands.HighCard;
    }
}
