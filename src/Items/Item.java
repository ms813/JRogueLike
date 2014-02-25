package Items;

import Generic.Actor;
import org.jsfml.graphics.Sprite;

/**
 * Created by Matthew on 25/02/14.
 */
public interface Item extends Actor {

    public void pickUp(Actor byActor);
    //public Sprite getIcon();

}
