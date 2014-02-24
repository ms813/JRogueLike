import org.jsfml.graphics.RenderWindow;

/**
 * Created by Matthew on 24/02/14.
 */
public interface Weapon extends Actor{
    public void update();
    public void draw(RenderWindow window);
}
