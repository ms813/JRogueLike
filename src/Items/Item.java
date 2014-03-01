package Items;

import Generic.Actor;
import org.jsfml.graphics.Sprite;

/**
 * Created by Matthew on 25/02/14.
 */
public interface Item extends Actor {

    public void pickUp(Actor byActor);

    //the icon is a sprite used for displaying on the GUI
    //c.f. "sprite" being used to display in the game world
    public Sprite getIcon();

    public boolean isStackable();

    public String getName();

    public void setPosition(float x, float y);

    public void use();

}
