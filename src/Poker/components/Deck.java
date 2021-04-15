package Poker.components;

import Poker.io.CardsReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a placeholder for a cards deck
 * Gives access to an infinite amount of cards that can be drawn and shuffled.
 */
public class Deck {
    /**
     * When a deck is built it is refilled and shuffled
     */
    public Deck() {
        this.refill();
        this.shuffle();
    }

    private ArrayList<Card> cards;

    private void refill() { this.cards = CardsReader.getCards(); }
    private void shuffle() {
        Collections.shuffle(this.cards);
    }
    private void add(final Card card) { this.cards.add(card); }

    /**
     * Allows the extraction of a single card from the deck.
     * @return A card object
     */
    public Card getCard() {
        if (this.cards.isEmpty()) {
            this.refill();
            this.shuffle();
        }
        return this.cards.remove(0);
    }

    /**
     * Allows the extraction of multiple cards from the deck at once.
     * @param n Number of cards to be extracted from the deck
     * @return A list containing the extracted deck cards
     */
    public List<Card> getNCards(int n) {
        if (this.cards.isEmpty() || this.cards.size() < n) {
            this.refill();
            this.shuffle();
        }
        // should extract one card at the time
        List<Card> spliceRangeView = this.cards.subList(0, n);
        List<Card> spliceRange = new ArrayList<>(this.cards.subList(0, n));
        spliceRangeView.clear();
        return spliceRange;
    }

    /**
     * Gives read only access to the deck's cards
     * @return List containing all the deck's cards
     */
    public List<Card> getDeckCards() {
        // should return an immutable list
        return this.cards;
    }
}
