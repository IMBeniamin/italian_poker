package Poker.io;

import Poker.components.Game;

public interface UserInterface <UI extends UserInterface>
{ // ^_^
    void update(Game<UI> game);
    void gameEnd();
    boolean getAction();
}
