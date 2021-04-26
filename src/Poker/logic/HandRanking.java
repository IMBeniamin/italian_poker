package Poker.logic;

import Poker.components.Card;
import Poker.components.Player;
import Poker.util.Pair;
import Poker.util.PokerHands;
import Poker.util.Suits;

import java.util.*;


/**
 * Interface which contains a method which is implemented through a lambda later and provides a way to call it
 */
interface HandRankingCalculator {
    Pair<Boolean, Integer> calculate(HashMap<Suits, List<Card>> suitsMap, HashMap<Integer, List<Card>> valuesMap);
}

/**
 * Static utility class that maps each rank of poker hand to a lambda which calculates it based on 2 maps provided to the function
 * The map can then be iterated through and all methods can be applied to the current player hand
 */
public class HandRanking {
    /**
     * A LinkedHashMap is needed as the order of assignment of the lambdas to the map should be preserved
     */
    static final private LinkedHashMap<PokerHands, HandRankingCalculator> map = new LinkedHashMap<>();

    static {
        // It is important to insert the poker hand types from the MOST IMPORTANT to THE LEAST IMPORTANT
        // Each lambda is added to the map using the put method
        HandRanking.map.put(PokerHands.RoyalFlush, (suitsMap, valuesMap) -> {
            for (int i = 10; i < 15; i++)
                if (!valuesMap.containsKey(i)) return new Pair<>(false, 0);
            for (Map.Entry<Suits, List<Card>> suitEntry : suitsMap.entrySet()) {
                if (suitEntry.getValue().size() == 5)
                    return new Pair<>(true, suitEntry.getValue().stream().mapToInt(Card::getPoints).sum());
            }
            return new Pair<>(false, 0);
        });
        HandRanking.map.put(PokerHands.StraightFlush, (suitsMap, valuesMap) -> {
            Pair<Boolean, Integer> straight = HandRanking.map.get(PokerHands.Straight).calculate(suitsMap, valuesMap);
            Pair<Boolean, Integer> flush = HandRanking.map.get(PokerHands.Flush).calculate(suitsMap, valuesMap);
            boolean isStraightFlush = straight.fst && flush.fst;

            return new Pair<>(isStraightFlush, straight.snd + flush.snd);
        });
        HandRanking.map.put(PokerHands.FourOfAKind, (suitsMap, valuesMap) -> {
            for (Map.Entry<Integer, List<Card>> valueEntry : valuesMap.entrySet())
                if (valueEntry.getValue().size() == 4)
                    return new Pair<>(true, valueEntry.getValue().stream().mapToInt(Card::getPoints).sum());
            return new Pair<>(false, 0);
        });
        HandRanking.map.put(PokerHands.Flush, (suitsMap, valuesMap) -> {
            for (Map.Entry<Suits, List<Card>> suitEntry : suitsMap.entrySet())
                if (suitEntry.getValue().size() == 5)
                    return new Pair<>(true, suitEntry.getValue().stream().mapToInt(Card::getPoints).sum());
            return new Pair<>(false, 0);
        });

        HandRanking.map.put(PokerHands.FullHouse, (suitsMap, valuesMap) -> {
            Pair<Boolean, Integer> onePair = HandRanking.map.get(PokerHands.OnePair).calculate(suitsMap, valuesMap);
            Pair<Boolean, Integer> threeOfKind = HandRanking.map.get(PokerHands.ThreeOfAKind).calculate(suitsMap, valuesMap);
            return new Pair<>(onePair.fst && threeOfKind.fst, onePair.snd + threeOfKind.snd);
        });

        HandRanking.map.put(PokerHands.Straight, (suitsMap, valuesMap) -> {
            if (valuesMap.values().stream().anyMatch(arr -> arr.size() > 1)) return new Pair<>(false, 0);

            SortedSet<Integer> keys = new TreeSet<>(valuesMap.keySet());
            boolean isStraight = keys.last() - keys.first() == keys.size() - 1;
            int points = 0;
            // nested one-liner sum
            // please don't do this <3
            if (isStraight) points = suitsMap.values().stream().mapToInt(cardList -> cardList.stream().mapToInt(Card::getPoints).sum()).sum();
            return new Pair<>(isStraight, points);
        });

        HandRanking.map.put(PokerHands.ThreeOfAKind, (suitsMap, valuesMap) -> {
            for (Map.Entry<Integer, List<Card>> valueEntry : valuesMap.entrySet())
                if (valueEntry.getValue().size() == 3)
                    return new Pair<>(true, valueEntry.getValue().stream().mapToInt(Card::getPoints).sum());
            return new Pair<>(false, 0);
        });

        HandRanking.map.put(PokerHands.TwoPair, (suitsMap, valuesMap) -> {
            int pairs = 0, points = 0;
            for (Map.Entry<Integer, List<Card>> cardEntry : valuesMap.entrySet())
                if (cardEntry.getValue().size() == 2) {
                    pairs++;
                    points += cardEntry.getValue().stream().mapToInt(Card::getPoints).sum();
                }
            if (pairs >= 2) return new Pair<>(true, points);
            else return new Pair<>(false, 0);
        });

        HandRanking.map.put(PokerHands.OnePair, (suitsMap, valuesMap) -> {
            for (Map.Entry<Integer, List<Card>> cardEntry : valuesMap.entrySet())
                if (cardEntry.getValue().size() == 2)
                    return new Pair<>(true, cardEntry.getValue().stream().mapToInt(Card::getPoints).sum());
            return new Pair<>(false, 0);
        });
    }
    /**
     * Ranking algorithm used for hand ranking
     * the total points are calculated with a weighted sum:
     *     the hand rank has a magnitude of a 100:1 in respect to the card points
     *     the card points are then used to distinguish between hands with the same rank
     * @param handRank Rank of the hand
     * @param cardsPoint The total points of the user's hand (note: this is not a sum of all the cards' points, but rather
     *                   a sum of the cards that are valid towards the rank and thus have to be calculated individually for
     *                   each rank
     */
    static private int pointsRanker(PokerHands handRank, int cardsPoint) {
        return handRank.ordinal() * 100 + cardsPoint;
    }

    static public Pair<PokerHands, Integer> getHandRank(Player player) {
        // temporary placeholder for the hand rank that is being evaluated
        Pair<Boolean, Integer> currentHandRank;
        // iterates through all the map's ranks
        for (Map.Entry<PokerHands, HandRankingCalculator> rank : HandRanking.map.entrySet()) {
            // sets the current rank variable to the calculated value based on rank's handRankingCalculator
            currentHandRank = rank.getValue().calculate(player.getSuitsMap(), player.getValuesMap());
            // checks if the calculations had a positive result
            if (currentHandRank.fst) {
                // if the calculations had a positive result then a new pair is created that has the rank type based on
                // the current rank's calculator associated value ( see map )
                // and as points the points that the pointRanker has generated
                return new Pair<>(rank.getKey(),pointsRanker(rank.getKey(),currentHandRank.snd));
            }
        }
        // if after all calculations no result can be determined then the algorithm defaults to high card
        // the points associated with it are the points of the highest card in the player's hand
        return new Pair<>(PokerHands.HighCard, pointsRanker(PokerHands.HighCard,Collections.max(player.getHand()).getPoints()));
    }
}
