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
    protected float maxSpeed;
    protected Vector2f velocity = Vector2f.ZERO;
    protected float friction;
    protected float acceleration;
    protected float mass;

    protected MonsterHPBar hpBar;

    public void onCollision(Actor collider, FloatRect collisionRect) {

        //if collided with another monster move away
       /* if (collider instanceof Monster) {

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
        }*/
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
        velocity = Vector2f.mul(velocity, friction);
        move(velocity);
        hpBar.update();
    }

    public void knockBack(Vector2f dir, float power){
       // velocity = Vector2f.add(velocity, Vector2f.mul(VectorArithmetic.normalize(dir), power/mass));
        velocity = Vector2f.mul(VectorArithmetic.normalize(dir), power/mass);
    }


    public void move(Vector2f dir){
        sprite.move(dir);
    }

    public void changeVelocity(float x, float y){
        changeVelocity(new Vector2f(x, y), 1);
    }

    public void changeVelocity(Vector2f vector){
        changeVelocity(vector, 1);
    }

    public void changeVelocity(Vector2f vector, float scaleFactor){
        velocity = Vector2f.mul(Vector2f.add(Vector2f.mul(vector, acceleration), velocity), scaleFactor);
        if (VectorArithmetic.magnitude(velocity) > maxSpeed) {
            velocity = Vector2f.mul(velocity, maxSpeed/ VectorArithmetic.magnitude(velocity));
        }
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

    public FloatRect getBoundingRect(){
        return sprite.getGlobalBounds();
    }

    public Vector2f getVelocity(){
        return velocity;
    }

    public float getMass() {
        return mass;
    }
}
