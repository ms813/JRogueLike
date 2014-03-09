package Generic;

import org.jsfml.graphics.FloatRect;
import org.jsfml.system.Vector2f;

/**
 * Created by Matthew on 05/03/14.
 */
public interface DynamicActor extends Actor {

    //used for moving game objects such as monsters and the player

    public void move(Vector2f dir);

    public void changeVelocity(float x, float y);
    public void changeVelocity(Vector2f vector);
    public void changeVelocity(Vector2f vector, float scaleFactor);
    public void knockBack(Vector2f direction, float power);

    public Vector2f getVelocity();

}
