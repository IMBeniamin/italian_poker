package Poker.components;

import Poker.logic.PlayerHandLogic;
import Poker.util.PokerHands;

import java.util.ArrayList;
import java.util.List;

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

    public void resetHand() {
        this.hand.clear();
    }
    public void giveCard(Card card) {
        this.hand.add(card);
    }
    public void giveCard(List<Card> cards) {
        this.hand.addAll(cards);
    }

    public void calcHandRank() {
        this.handRank = PlayerHandLogic.calcHandRank(this); // calc rank
    }

    public String toString() {
        StringBuilder outData = new StringBuilder(this.name);
        outData.append("\n");
        for (Card card : this.hand) {
            outData.append(card.toString()).append("\n");
        }
        outData.append(this.handRank).append("\n");
        return outData.toString();
    }

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

    @Override
    public int compareTo(Player o) {
        return Integer.compare(this.getHandRank().ordinal(), o.getHandRank().ordinal());
    }
}
