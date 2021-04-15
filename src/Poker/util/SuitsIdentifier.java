package Poker.util;

import java.util.HashMap;

/**
 * Utility class that provides a way to map the id of a card read from a file to a value of the Suits type
 */
public class SuitsIdentifier {
    /**
     * Map that contains all the values an id could have and the suits type equivalent
     */
    private static final HashMap<String, Suits> map = new HashMap<>();
    static {
        SuitsIdentifier.map.put("S", Suits.Spades);
        SuitsIdentifier.map.put("C", Suits.Clubs);
        SuitsIdentifier.map.put("D", Suits.Diamonds);
        SuitsIdentifier.map.put("H", Suits.Hearts);
    }

    /**
     * Utility function that uses the Suits map to associate the extracted token from the file identifier to calculate the suits type of the card
     * @param SuitRaw String of the card suit identifier !IMPORTANT This is not the identifier read from file, but a parsed version of it
     * @return A suits type that has been identified through the map
     */
    public static Suits getSuitTypeFromSuit(String SuitRaw) {
        return map.get(SuitRaw);
    }
}
