package Poker.components;

import Poker.util.Suits;
import Poker.util.SuitsIdentifier;

public class Card {
    private final String identifier;
    private final String fancyName;
    private final int points;
    private transient final String mediaPath;

    private final Suits suit;
    private final int value;

    public Card(String identifier, String fancyName, int points) {
        this.identifier = identifier;
        this.fancyName = fancyName;
        this.points = points;

        this.value = Integer.parseInt(identifier.split("_")[0]);
        this.suit = this.getSuitFromId(identifier);
        this.mediaPath = "src/Game/res/cards/" + identifier + ".jpg";
    }

    private Suits getSuitFromId(String identifier) {
        String[] parts = identifier.split("_");
        return SuitsIdentifier.getSuitTypeFromSuit(parts[parts.length - 1]);
    }

    public String getName() { return this.fancyName; }
    public String getID() { return this.identifier; }
    public int getValue() { return this.value; }
    public int getPoints() { return this.points; }
    public String getImage() { return this.mediaPath; }

    public String toString() {
        return this.fancyName;
        // maybe improve in the future
    }

    public Suits getSuit() {
        return this.suit;
    }
}
