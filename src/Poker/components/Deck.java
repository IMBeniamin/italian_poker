package Poker.components;

import Poker.io.CardsReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    public Deck() {
        this.refill();
        this.shuffle();
    }
    private ArrayList<Card> cards;

    private void refill() {
        this.cards = CardsReader.getCards();
    }

    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    public void add(final Card card) {
        this.cards.add(card);
    }

    public Card getCard() {
        if (this.cards.isEmpty()) {
            this.refill();
            this.shuffle();
        }
        return this.cards.remove(0);
    }

    public List<Card> getNCards(int n) {
        if (this.cards.isEmpty() || this.cards.size() < n) {
            this.refill();
            this.shuffle();
        } // eventually should extract one card at the time
        List<Card> spliceRangeView = this.cards.subList(0, n);
        List<Card> spliceRange = new ArrayList<>(this.cards.subList(0, n));
        spliceRangeView.clear();
        return spliceRange;
    }

    public List<Card> getDeckCards() { return this.cards; }
}
