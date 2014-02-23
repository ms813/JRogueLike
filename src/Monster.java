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

    protected MonsterHPBar hpBar;

    protected boolean readyForDestruction = false;

    public void onCollision(Actor collider){
        hpBar.update();
    }

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

    public void update(){
        if(currentHP <= 0){
            onDeath();
        }
    }

    protected abstract void onDeath();

    protected abstract void reduceHP(float damage);

    public void draw(RenderWindow window){
        window.draw(sprite);
        hpBar.draw(window);
    }

    public MonsterHPBar buildHPBar(){
        MonsterHPBar bar = new MonsterHPBar(this);
        return bar;
    }
}
