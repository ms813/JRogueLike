import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.system.Vector2f;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Matthew
 * Date: 15/02/14
 * Time: 12:56
 * To change this template use File | Settings | File Templates.
 */
public class Scene {

    private List<Actor> mActors = new ArrayList<Actor>();
    private String name;

    private RectangleShape background;

    public Scene(String _name){
        mActors.add(new Actor("blueCircle", 50, Color.BLUE));
        name = _name;
        background = new RectangleShape(new Vector2f(500.0f, 500.0f));
    }

    public RectangleShape getBackground(){
        return background;
    }

    public List<Actor> getActors(){
        return mActors;
    }
}
