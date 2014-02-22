import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

/**
 * Created by Matthew on 18/02/14.
 */
public abstract class Monster implements Actor{

    protected Sprite sprite;

    protected float maxHP;
    protected float currentHP;
    protected float XP;

    protected boolean readyForDestruction = false;

    public abstract void onCollision(Actor collider);

    public Sprite getDrawable(){
        return sprite;
    }

    public Vector2f getCurrentPosition() {
        return sprite.getPosition();
    }

    public void setPosition(Vector2f position) {
        sprite.setPosition(position);
    }

    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }

    public boolean isReadyForDestruction(){
        return readyForDestruction;
    }

    public abstract void update();

    protected abstract void onDeath();

    protected abstract void reduceHP(float damage);

    public void draw(RenderWindow window){
        window.draw(sprite);
    }
}
