import org.jsfml.graphics.RenderWindow;
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

    List<Scene> mSceneList = new ArrayList<Scene>();
    RenderWindow window = new RenderWindow();

    public Game(String gameTitle){

        int screenW = 1080;
        int screenH = 720;
        window.create(new VideoMode(screenW, screenH), gameTitle);

        mSceneList.add(new Scene("DefaultScene"));
    }

    public RenderWindow getWindow(){
        return window;
    }

    public void drawScene(int index){

            Scene scene = mSceneList.get(index);
            window.draw(scene.getBackground());

            for(Iterator<Actor> i = scene.getActors().iterator(); i.hasNext();){
                Actor actor = i.next();
                window.draw(actor);
            }


    }
}
