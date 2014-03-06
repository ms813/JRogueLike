package Monsters;

import Game.Scene.MapTile;
import Generic.Actor;
import Generic.DynamicActor;
import Generic.MonsterHPBar;
import Generic.VectorArithmetic;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

/**
 * Created by Matthew on 18/02/14.
 */
public abstract class Monster implements DynamicActor {

    protected Sprite sprite;

    protected float maxHP;
    protected float currentHP;
    protected float XP;
    protected float moveSpeed;

    protected MonsterHPBar hpBar;

    public void onCollision(Actor collider) {
        //if collided with another monster move away
        if (collider instanceof Monster) {

            Sprite col = (Sprite) collider.getDrawable();
            FloatRect colRect = sprite.getGlobalBounds().intersection(col.getGlobalBounds());

            if (colRect != null) {


                    if (sprite.getPosition().x < col.getPosition().x) {
                        move(-colRect.width / 10, 0);
                    } else {
                        move(colRect.width / 10, 0);
                    }



                    if (sprite.getPosition().y < col.getPosition().y) {
                        move(0, -colRect.height / 10);
                    } else {
                        move(0, colRect.height / 10);
                    }

            }
        }

        //if monster collides with an impassable terrain tile
        if(collider instanceof MapTile){
            FloatRect tileRect = ((MapTile) collider).getDrawable().getBounds();
            FloatRect intersectRect = sprite.getGlobalBounds().intersection(tileRect);

            if (intersectRect != null) {

                if (sprite.getPosition().x < collider.getPosition().x) {
                    move(new Vector2f(-intersectRect.width, 0));
                } else if (sprite.getPosition().x > collider.getPosition().x + ((MapTile) collider).getWidth()) {
                    move(new Vector2f(intersectRect.width, 0));
                }

                if (sprite.getPosition().y < collider.getPosition().y) {
                    move(new Vector2f(0, -intersectRect.height));
                } else if (sprite.getPosition().y > collider.getPosition().y + ((MapTile) collider).getHeight()) {
                    move(new Vector2f(0, intersectRect.height));
                }
            }
        }
    }

    public Sprite getDrawable() {
        return sprite;
    }

    public Vector2f getPosition() {
        return sprite.getPosition();
    }

    public void setPosition(Vector2f position) {
        sprite.setPosition(position);
    }

    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }

    public void update() {
        if (currentHP <= 0) {
            onDeath();
        }

        hpBar.update();
    }

    public void move(float x, float y){
        move(new Vector2f(x,y));
    }

    public void move(Vector2f dir){
        getDrawable().move(Vector2f.mul(VectorArithmetic.normalize(dir), moveSpeed));
    }

    protected abstract void onDeath();

    public abstract void reduceHP(float damage);

    public void draw(RenderWindow window) {
        window.draw(sprite);
        hpBar.draw(window);
    }

    public MonsterHPBar buildHPBar() {
        MonsterHPBar bar = new MonsterHPBar(this);
        return bar;
    }

    public float getCurrentHP() {
        return currentHP;
    }

    public float getMaxHP() {
        return maxHP;
    }

}
