import org.jsfml.system.Vector2f;

/**
 * Created by Matthew on 16/02/14.
 */
public interface Projectile extends Actor{
    void updatePosition();
    boolean isReadyForDestruction();
    Vector2f getStartPosition();
    Vector2f getTargetPosition();
}
