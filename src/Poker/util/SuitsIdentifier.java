package Poker.util;

import java.util.HashMap;

public class SuitsIdentifier {
    private static final HashMap<String, Suits> map = new HashMap<>();
    static {
        SuitsIdentifier.map.put("S", Suits.Spades);
        SuitsIdentifier.map.put("C", Suits.Clubs);
        SuitsIdentifier.map.put("D", Suits.Diamonds);
        SuitsIdentifier.map.put("H", Suits.Hearts);
    }
    public static Suits getSuitTypeFromSuit(String SuitRaw) {
        return map.get(SuitRaw);
    }
}
