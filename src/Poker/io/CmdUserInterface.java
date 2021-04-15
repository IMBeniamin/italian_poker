package Poker.io;

import Poker.components.*;

import java.util.Scanner;

/**
 * Implementation of the generic user interface used for the interaction with the user with the system console
 */
public class CmdUserInterface implements UserInterface<CmdUserInterface> {
    private void print(Player player) {
        System.out.println(player);
    }
    private void print(String string) {
        System.out.println(string);
    }

    /**
     * Function that asks the user for input
     * @return Is true if the user input is y, otherwise is false
     */
    @Override
    public boolean getAction() {
        System.out.println("Do you want to play another round? \n[y] -- yes \n[any key] -- no");
        return new Scanner(System.in).nextLine().equals("y");

    }

    /**
     * Function that is called at each round of the game
     * @param game The game of which the winner shuold be calculated
     */
    @Override
    public void update(Game<CmdUserInterface> game) {
        for (Player player : game.getPlayers()) {
            this.print(player);
        }
        Player winner = game.getWinner();
        this.print("Winner is player: " + winner.getName() + " with " + winner.getHandRank());
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Round End!");
        System.out.println("--------------------------------------------------------------------");
    }

    /**
     * Called when the game comes to an end
     */
    @Override
    public void gameEnd() {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("The game has ended!");
        System.out.println("Hopefully you had a lot of fun.");
        System.out.println("--------------------------------------------------------------------");
    }

}
