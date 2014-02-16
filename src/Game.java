import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.View;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.VideoMode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Matthew
 * Date: 15/02/14
 * Time: 12:56
 * To change this template use File | Settings | File Templates.
 */
public class Game {

    public static int screenW = 1080;
    public static int screenH = 720;

    private List<Scene> mSceneList = new ArrayList<Scene>();
    private static Scene currentScene;

    private static Player player;

    private static RenderWindow window = new RenderWindow();

    private static View mainView;

    public Game(String gameTitle){

        //Create the game window
        window.create(new VideoMode(screenW, screenH), gameTitle);


        //Create the main game view
        mainView = new View(new Vector2f(screenW/2, screenH/2), new Vector2f(screenW, screenH));
        window.setView(mainView);

        //Add scenes to the game
        mSceneList.add(new Scene("map"));
        currentScene = mSceneList.get(0);

        player = getPlayer();
    }

    public RenderWindow getWindow(){
        return window;
    }

    public void drawScene(int index){

        Scene scene = mSceneList.get(index);
        window.draw(scene.getBackground());

        for(Iterator<Actor> i = scene.getActors().iterator(); i.hasNext();){
            Actor actor = i.next();
            window.draw(actor.getDrawable());
        }
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

    public Scene getCurrentScene(){
        return currentScene;
    }
}
