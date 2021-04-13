import Poker.components.Card;
import Poker.components.CheatPlayer;
import Poker.components.Game;
import Poker.components.Player;
import Poker.io.CmdUserInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EntryPoint {
    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();

        CheatPlayer cheater = new CheatPlayer("Scimmia");
        Card D10 = new Card("10_D", "Ten of Diamonds", 13);
        Card D11 = new Card("11_D", "Jack of Diamonds", 14);
        Card D12 = new Card("12_D", "Queen of Diamonds", 15);
        Card D13 = new Card("13_D", "King of Diamonds", 16);
        Card D14 = new Card("14_D", "Ace of Diamonds", 17);
        cheater.cheatGiveCard(Arrays.asList(D10, D11, D12, D13, D14));

        players.add(new Player("Derpi"));
        players.add(new Player("Solare"));
        players.add(new Player("Mora"));
        players.add(cheater); // todo non funziona, da flush

        Game<CmdUserInterface> game1 = new Game<>(CmdUserInterface.class, players);
        game1.play();
    }
}
