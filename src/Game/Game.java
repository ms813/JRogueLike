package Game;

import Game.Scene.Scene;
import Game.Scene.SceneManager;
import Game.UI.UIManager;
import Player.Player;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.View;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Matthew
 * Date: 15/02/14
 * Time: 12:56
 * To change this template use File | Settings | File Templates.
 */
public class Game {

    public static int screenW = 1500;
    public static int screenH = 900;

    private RenderWindow window;

    private SceneManager sceneManager = SceneManager.getInstance();
    private Player player = GameManager.getInstance().getPlayer();

    private static View mainView;
    private View defaultView;

    private UIManager uiManager = UIManager.getInstance();

    public Game(RenderWindow window) {

        this.window = window;

        //Create the main game view
        mainView = new View(new Vector2f(screenW / 2, screenH / 2), new Vector2f(screenW, screenH));
        window.setView(mainView);
        defaultView = new View(new FloatRect(0, 0, window.getSize().x, window.getSize().y));

        //Add scenes to the game
        if(!sceneManager.sceneExists("level 1")) {
            sceneManager.createNewScene("level 1", "MapTiles", "level1");
        }
        uiManager.init();
    }

    public void draw(RenderWindow window) {
        //draw the scene
        window.setView(mainView);
        sceneManager.getCurrentScene().draw(window);

        //switch to the default view (relative to the window, not the world)
        //meaning we dont have to move the UI every frame
        window.setView(defaultView);
        uiManager.draw(window);

        //return the view to the one that follows the character
        window.setView(mainView);
    }

    public void update() {
        setViewCenter(new Vector2i(player.getDrawable().getPosition()));
        sceneManager.update();
        uiManager.update();
    }

    public void moveView(float x, float y) {
        mainView.move(x, y);
    }

    public void setViewCenter(Vector2i pos) {
        mainView.setCenter(new Vector2f(pos));
    }

}
