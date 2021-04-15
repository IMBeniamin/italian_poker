package Poker.components;

import Poker.logic.PlayerHandLogic;
import Poker.util.PokerHands;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the virtual representation of a player. It implements a comparable which is used by java to do comparisons
 * It contains all the necessary information a players should have during a match, such as his hand, his name and the rank of the current hand
 */
public class Player implements Comparable<Player>{
    private final List<Card> hand;
    private final String name;
    private PokerHands handRank;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getName() { return this.name; }
    public PokerHands getHandRank() { return this.handRank; }

    public List<Card> getHand() { return this.hand; }

    /**
     * Clears the hand of the player
     */
    public void resetHand() {
        this.hand.clear();
    }

    /**
     * @param card Card object that should be added to the player's hand
     */
    public void giveCard(Card card) {
        this.hand.add(card);
    }

    /**
     * @param cards List of card objects that should be added to the player's hand
     */
    public void giveCard(List<Card> cards) {
        this.hand.addAll(cards);
    }

    /**
     * Calculates the rank of the player's hand
     * This function should be called only after the hand is final
     */
    public void calcHandRank() {
        this.handRank = PlayerHandLogic.calcHandRank(this); // calc rank
    }

    /**
     * This function is an overload of a function that is always available in the Object class that is used for object serialization
     * @return The serialized player object
     */
    public String toString() {
        StringBuilder outData = new StringBuilder(this.name);
        outData.append("\n");
        for (Card card : this.hand) {
            outData.append(card.toString()).append("\n");
        }
        outData.append(this.handRank).append("\n");
        return outData.toString();
    }

    /**
     * Creates a string that can be directly inserted in a Html file
     * @return serialized player data for the html file
     */
    public String toHtml() {
        StringBuilder htmlData = new StringBuilder();
        htmlData.append("<div class=\"player-container\">\n");
        htmlData.append("<div class=\"header\">");
        htmlData.append("<p>").append(this.name).append("</p>\n");
        htmlData.append("</div>\n\n");

        htmlData.append("<div class=\"cards-container\">\n");
        for (Card card : this.hand) {
            htmlData.append(card.toHtml());
        }
        htmlData.append("</div>\n");

        htmlData.append("<div class=\"header\">");
        htmlData.append("<p>").append(this.handRank).append("</p>\n");
        htmlData.append("</div>\n\n");

        htmlData.append("</div>\n");
        return htmlData.toString();
    }

    /**
     * Override of the compare function which allows 2 players to be compared.
     * @param o Player to compare to.
     * @return 0 if the comparison is true.
     */
    @Override
    public int compareTo(Player o) {
        return Integer.compare(this.getHandRank().ordinal(), o.getHandRank().ordinal());
    }
}
