package Poker.components;

import Poker.util.Suits;
import Poker.util.SuitsIdentifier;

/**
 * Class that represents a playing card.
 */
public class Card {
    private final String identifier;
    private final String fancyName;
    private final Suits suit;
    private final int points;
    private final int value;

    /**
     * @param identifier Specifies how the card should be identified. The standard notation is value_Type
     * @param fancyName User presentable name of the card
     * @param points Points used for evaluating the winner and the type of hand.
     */
    public Card(String identifier, String fancyName, int points) {
        this.identifier = identifier;
        this.fancyName = fancyName;
        this.points = points;

        this.value = Integer.parseInt(identifier.split("_")[0]);
        this.suit = this.getSuitFromId(identifier);
    }

    /**
     * @param identifier Identifier of the card value_type (See constructor)
     * @return Type based on enum that contains all the suits according to the poker documentation
     */
    private Suits getSuitFromId(String identifier) {
        String[] parts = identifier.split("_");
        return SuitsIdentifier.getSuitTypeFromSuit(parts[parts.length - 1]);
    }

    public String getName() { return this.fancyName; }
    public String getID() { return this.identifier; }
    public int getValue() { return this.value; }
    public int getPoints() { return this.points; }
    public Suits getSuit() {
        return this.suit;
    }

    /**
     * @return Returns the user presentable name.
     */
    public String toString() {
        return this.fancyName;
    }

    /**
     * @return Returns a string that can be inserted in a html file.
     */
    public String toHtml() {
        return "<div class=\"card\">\n" +
                "<img src=\"" + getClass().getResource("../res/cards/" + this.identifier + ".jpg") +
                "\" alt=\"" + this.identifier + "\">\n" +
                "<p>" + this.fancyName + "</p>\n" +
                "</div>\n";
    }
}
