package Game;

import Player.Player;
import org.jsfml.graphics.RenderWindow;

/**
 * Created by Matthew on 07/04/2014.
 */
public class GameManager {

    private static GameManager instance = null;
    private Game game = null;
    private Player player;

    protected GameManager(){}

    public static GameManager getInstance(){
        if(instance == null){
            instance = new GameManager();
        }
        return instance;
    }

    public Game createNewGame(RenderWindow window){
        if(game == null){
            game = new Game(window);
        } else{
            System.err.println("A game has already been created. Returning that instance instead");
        }

        return game;
    }

    public Game getGame(){
        return game;
    }

    public Player getPlayer(){
        if(player == null) {
            player = new Player(150, 150);
        }
        return player;
    }
}
