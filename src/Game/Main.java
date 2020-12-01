package Game;

import Game.data.MenuOption;
import Game.data.Time;

public class Main {

    // Creates the game class to launch and setup the game.
    public static void main(String[] args) {

        Game game = GameSetup.setupGame();

        game.start();
    }
}
