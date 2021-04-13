package Poker.components;

import java.util.List;

public class CheatPlayer extends Player {

    public CheatPlayer(String name) {
        super(name);
    }

    @Override
    public void resetHand() {}

    @Override
    public void giveCard(Card card) {}

    @Override
    public void giveCard(List<Card> cards) {}

    public void cheatGiveCard(List<Card> cards) {
        super.giveCard(cards);
    }
}
