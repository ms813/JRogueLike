package Generic;

import org.jsfml.system.Vector2f;

/**
 * Created by Matthew on 05/03/14.
 */
public interface DynamicActor extends Actor {

    //used for moving game objects such as monsters and the player


    public void move(float x, float y);
    public void move(Vector2f dir);

}
