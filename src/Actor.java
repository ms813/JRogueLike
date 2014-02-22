import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;

/**
 * Created with IntelliJ IDEA.
 * User: Matthew
 * Date: 15/02/14
 * Time: 12:56
 * To change this template use File | Settings | File Templates.
 */
public interface Actor{

    public Drawable getDrawable();

    public Vector2f getCurrentPosition();

    //NOTE projectiles should always use their onCollision method to check for hits on monsters.
    //Monsters/players should use theirs for checking collisions with walls etc
    public void onCollision(Actor collider);

    public boolean isReadyForDestruction();

    public void update();

    public void draw(RenderWindow window);
}
