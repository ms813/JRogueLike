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

    private List<Scene> mSceneList = new ArrayList<Scene>();
    private static Scene currentScene;

    private static RenderWindow window;

    private static View mainView;

    public Game(RenderWindow _window){

        window = _window;

        //Create the main game view
        mainView = new View(new Vector2f(screenW/2, screenH/2), new Vector2f(screenW, screenH));
        window.setView(mainView);

        //Add scenes to the game
        currentScene = new Scene("map");

        mSceneList.add(currentScene);
    }

    public RenderWindow getWindow(){
        return window;
    }

    public void draw(RenderWindow window){
        currentScene.draw(window);
    }

    public void update(){
        currentScene.update();
    }

    public void moveView(float x, float y){
        mainView.move(x, y);
    }

    public void setViewCenter(Vector2i pos){
        mainView.setCenter(new Vector2f(pos));
        window.setView(mainView);
    }

    public Player getPlayer(){
        return currentScene.getPlayer();
    }

    public static Scene getCurrentScene(){
        return currentScene;
    }
}
