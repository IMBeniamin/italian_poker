package Poker.components;

import Poker.io.CmdUserInterface;
import Poker.io.UserInterface;

import java.util.*;

/**
 * The game instance
 * All operation of the game and its logic are handled by this class.
 * It accepts a generic type that extends the UserInterface interface which will be used for user input and output
 * @param <UI> Type that extends the UserInterface interface which will be used for instantiating the i/o object
 */
public final class Game <UI extends UserInterface> {
    /**
     * When a game is constructed the user interface is set accordingly to the type passed by the user.
     * @param Ui Class type that should be a possible value of UI that is used for instantiating the local user interface reference
     */
    public Game(Class<UI> Ui) {
        UserInterface GameUi = new CmdUserInterface();
        try { GameUi = Ui.newInstance(); }
        catch (Exception e) { e.printStackTrace(); }
        this.userInterface = GameUi;
    }

    /**
     * When a game is constructed the user interface is set accordingly to the type passed by the user.
     * @param Ui Class type that should be a possible value of UI that is used for instantiating the local user interface reference
     * @param players List of players that will be used added to the game
     *                Note: The maximum number of players allowed with the Html interface is 4
     */
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

    /**
     * @return List of the players that are currently participating in the game
     */
    public List<Player> getPlayers() {
        // should return immutable instance of the players
        return this.players;
    }

    /**
     * @return The player that has won the game.
     */
    public Player getWinner() { return Collections.max(this.players); }

    /**
     * @return Whether the game is actively playing or not
     */
    public boolean isPlaying() { return this.gamePlaying; }


    /**
     * Allows a player to be added to the game
     * @param player The player that should be added to the game
     */
    public void addPlayer(Player player) {
        this.players.add(player);
    }

    /**
     * Allows a list of players to be added to the game
     * @param players The player list that should be added to the game
     */
    public void addPlayer(List<Player> players) {
        this.players.addAll(players);
    }



    // Game mechanics section
    private void giveHands() {
        for (Player player : this.players) {
            this.giveCard(player, 5);
        }
    }

    private void calcHands() {
        for (Player player : this.players) {
            player.calcHandRank();
        }
    }

    public void resetHands() {
        for (Player player : this.players) {
            player.resetHand();
        }
    }

    private void giveCard(Player player) {
        player.giveCard(this.deck.getCard());
    }

    private void giveCard(Player player, int n) {
        player.giveCard(this.deck.getNCards(n));
    }


    // Interface section
    private void updateState() { this.userInterface.update(this); }
    private boolean getUserAction() { return this.userInterface.getAction(); }
    private void gameEnd() { this.userInterface.gameEnd(); }


    // Runtime section

    /**
     * Executes all game mechanics that are required for the game to continue.
     * At the end of the logical part of it the function also updates the game status using the interface specified by the user at the creation of the game session.
     * There is no limit to how many sessions can be played in a game.
     */
    public void nextRound() {
        this.resetHands();
        this.giveHands();
        this.calcHands();
        this.updateState();
    }

    /**
     * Starts executing the game and stops when the user wants to quit
     * The input is received by using the interface that has been selected at the creation of the game instance
     */
    public void play() {
        this.gamePlaying = true;
        while (this.gamePlaying) {
            this.nextRound();
            this.gamePlaying = this.getUserAction();
        }
            this.gameEnd();
    }
}
