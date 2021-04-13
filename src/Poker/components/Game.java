package Poker.components;

import Poker.io.CmdUserInterface;
import Poker.io.UserInterface;

import java.util.*;

public final class Game <UI extends UserInterface> {
    public Game(Class<UI> Ui) {
        UserInterface GameUi = new CmdUserInterface();
        try { GameUi = Ui.newInstance(); }
        catch (Exception e) { e.printStackTrace(); }
        this.userInterface = GameUi;
    }
    public Game (Class<UI> Ui, List<Player> players) {
        UserInterface GameUi = new CmdUserInterface();
        try { GameUi = Ui.newInstance(); }
        catch (Exception e) { e.printStackTrace(); }
        this.userInterface = GameUi;
        this.players.addAll(players);
    }

    private boolean gamePlaying = false;
    private final Deck deck = new Deck();
    private final List<Player> players = new ArrayList<>();
    private final UserInterface userInterface;

    public List<Player> getPlayers() {
        return this.players;
    }
    public boolean isPlaying() { return this.gamePlaying; }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void addPlayer(List<Player> players) {
        this.players.addAll(players);
    }

    public void giveHands() {
        for (Player player : this.players) {
            this.giveCard(player, 5);
        }
    }

    public void calcHands() {
        for (Player player : this.players) {
            player.calcHandRank();
        }
    }

    public void resetHands() {
        for (Player player : this.players) {
            player.resetHand();
        }
    }

    public void giveCard(Player player) {
        player.giveCard(this.deck.getCard());
    }

    public void giveCard(Player player, int n) {
        player.giveCard(this.deck.getNCards(n));
    }

    public Player calcWinner() {
        return Collections.max(this.players);
    }

    private void updateState() { this.userInterface.update(this); }
    private boolean getUserAction() { return this.userInterface.getAction(); }
    private void gameEnd() { this.userInterface.gameEnd(); }

    public void nextRound() {
        this.resetHands();
        this.giveHands();
        this.calcHands();
        this.updateState();
    }

    public void play() {
        this.gamePlaying = true;
        while (this.gamePlaying) {
            this.nextRound();
            this.gamePlaying = this.getUserAction();
        }
            this.gameEnd();
    }
}
