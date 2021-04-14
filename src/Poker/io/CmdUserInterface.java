package Poker.io;

import Poker.components.*;

import java.util.Scanner;

public class CmdUserInterface implements UserInterface<CmdUserInterface> {
    private void print(Player player) {
        System.out.println(player);
    }
    private void print(String string) {
        System.out.println(string);
    }

    public boolean getAction() {
        System.out.println("Do you want to play another round? \n[y] -- yes \n[any key] -- no");
        return new Scanner(System.in).nextLine().equals("y");
    }

    public void update(Game<CmdUserInterface> game) {
        for (Player player : game.getPlayers()) {
            this.print(player);
        }
        Player winner = game.calcWinner();
        this.print("Winner is player: " + winner.getName() + " with " + winner.getHandRank());
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Round End!");
        System.out.println("--------------------------------------------------------------------");
    }

    public void gameEnd() {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("The game has ended!");
        System.out.println("Hopefully you had a lot of fun.");
        System.out.println("--------------------------------------------------------------------");
    }

}
