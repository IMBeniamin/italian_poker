package Poker.components;

import java.util.List;

/**
 * This class is intentionally made for cheating and/or testing the game.
 * It gives direct access to the internal hand and allows the user to directly input cards into it.
 * It also blocks the game from doing any kind of input/output between rounds to the class.
 * WARNING! The objects created remain immutable by the game until manually modified!
 */
public class CheatPlayer extends Player {

    /**
     * The constructor only calls the Player constructor
     * @param name The name of the player
     */
    public CheatPlayer(String name) {
        super(name);
    }

    /**
     * The function that allows the game to reset the player hand is overridden to nothing
     */
    @Override
    public void resetHand() {}

    /**
     * The giveCard functions have been overloaded to nothing.
     * This is to make sure that the game will not automatically refill the player's hand
     */
    @Override
    public void giveCard(Card card) {}

    /**
     * The giveCard functions have been overloaded to nothing.
     * This is to make sure that the game will not automatically refill the player's hand
     */
    @Override
    public void giveCard(List<Card> cards) {}

    /**
     * Wrapper around giveCard that makes add cards to the player manually possible.
     * The cards are appended to the hand.
     * @param cards List of cards to add to the player's hand
     */
    public void cheatGiveCard(List<Card> cards) {
        super.giveCard(cards);
    }

    /**
     * Wrapper around resetHand that resets the player's hand.
     */
    public void cheatResetHand() {
        super.resetHand();
    }
}
