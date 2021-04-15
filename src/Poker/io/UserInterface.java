package Poker.io;

import Poker.components.Game;

/**
 * This interface represents all possible user interfaces.
 * @param <UI> Type of the user interface.
 */
public interface UserInterface <UI extends UserInterface>
{ // ^_^
    void update(Game<UI> game);
    void gameEnd();
    boolean getAction();
}
