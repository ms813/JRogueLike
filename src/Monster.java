import org.jsfml.graphics.FloatRect;
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
    protected float moveSpeed;

    protected MonsterHPBar hpBar;

    protected boolean readyForDestruction = false;

    public void onCollision(Actor collider){
        if (collider instanceof Monster) {

            Sprite col = (Sprite) collider.getDrawable();
            FloatRect colRect = sprite.getGlobalBounds().intersection(col.getGlobalBounds());

            if (colRect != null) {
                if (colRect.width > (sprite.getLocalBounds().width / 10)) {

                    if (sprite.getPosition().x < col.getPosition().x) {
                        sprite.move(-colRect.width / 10, 0);
                    } else {
                        sprite.move(colRect.width / 10, 0);
                    }
                }

                if (colRect.height > (sprite.getLocalBounds().height / 10)) {
                    if (sprite.getPosition().y < col.getPosition().y) {
                        sprite.move(0, -colRect.width / 10);
                    } else {
                        sprite.move(0, colRect.width / 10);
                    }
                }
            }
        }
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

        hpBar.update();
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
