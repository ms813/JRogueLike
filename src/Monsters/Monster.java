package Monsters;

import Game.CollisionManager;
import Game.Game;
import Game.Scene.MapTile;
import Game.Scene.MapTileLibrary;
import Game.Scene.Passable;
import Game.Scene.SceneManager;
import Generic.Actor;
import Generic.DynamicActor;
import Generic.Bars.MonsterHPBar;
import Generic.VectorArithmetic;
import Player.PlayerManagers.PlayerXPManager;
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
    protected float currentMaxSpeed;
    protected float idealMaxSpeed;
    protected Vector2f velocity = Vector2f.ZERO;
    protected float friction;
    protected float acceleration;
    protected float mass;
    protected float onTouchDamage;

    protected boolean flying = false;

    protected MonsterHPBar hpBar;

    public void onCollision(Actor collider, FloatRect collisionRect) {
        if (collisionRect != null) {
            if (collider instanceof MapTile) {
                MapTile tile = (MapTile) collider;

                if (tile.getTileRef() == MapTileLibrary.WATER_SHALLOW) {
                    currentMaxSpeed = idealMaxSpeed / 2;
                } else {
                    if(currentMaxSpeed < idealMaxSpeed) {
                        currentMaxSpeed = idealMaxSpeed;
                    }
                }

                if (tile.passable() == Passable.FALSE || (tile.passable() == Passable.FLYING && !flying)) {

                    boolean axis_x = false;
                    boolean axis_y = false;
                    float xOverlap;
                    float yOverlap;

                    boolean pp_collides_x = CollisionManager.collidesByAxis(getBoundingRect().left, getBoundingRect().width, collider.getBoundingRect().left, collider.getBoundingRect().width);
                    boolean pp_collides_y = CollisionManager.collidesByAxis(getBoundingRect().top, getBoundingRect().height, collider.getBoundingRect().top, collider.getBoundingRect().height);

                    if (pp_collides_x || pp_collides_y) {
                        if (pp_collides_x) {
                            axis_y = true;
                        }
                        if (pp_collides_y) {
                            axis_x = true;
                        }
                    } else {
                        axis_x = true;
                        axis_y = true;
                    }


                    if (collisionRect.left <= getBoundingRect().left) {
                        //collided on the right hand side
                        xOverlap = collisionRect.width;
                    } else {
                        //collided on the left
                        xOverlap = -collisionRect.width;
                    }

                    if (collisionRect.top <= getBoundingRect().top) {
                        yOverlap = collisionRect.height;
                    } else {
                        yOverlap = -collisionRect.height;
                    }

                    if (!(axis_x && axis_y)) {
                        if (axis_x) {
                            move(xOverlap, 0);
                        } else if (axis_y) {
                            move(0, yOverlap);
                        } else {
                            //should never reach here
                        }
                    }
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
        velocity = Vector2f.mul(velocity, friction);
        move(velocity);
        hpBar.update();
    }

    public void knockBack(Vector2f dir, float power) {
        // velocity = Vector2f.add(velocity, Vector2f.mul(VectorArithmetic.normalize(dir), power/mass));
        velocity = Vector2f.mul(VectorArithmetic.normalize(dir), power / mass);
    }


    public void move(Vector2f dir) {
        sprite.move(dir);
    }

    public void move(float x, float y) {
        sprite.move(x, y);
    }

    public void changeVelocity(float x, float y) {
        changeVelocity(new Vector2f(x, y), 1);
    }

    public void changeVelocity(Vector2f vector) {
        changeVelocity(vector, 1);
    }

    public void changeVelocity(Vector2f vector, float scaleFactor) {
        velocity = Vector2f.mul(Vector2f.add(Vector2f.mul(vector, acceleration), velocity), scaleFactor);
        if (VectorArithmetic.magnitude(velocity) > currentMaxSpeed) {
            velocity = Vector2f.mul(velocity, currentMaxSpeed / VectorArithmetic.magnitude(velocity));
        }
    }

    protected void onDeath() {
        SceneManager.getInstance().getCurrentScene().removeDynamicActor(this);
        PlayerXPManager.getInstance().gainXP(XP);
    }
    public void reduceHP(float damage) {
        currentHP -= damage;
    }

    public void draw(RenderWindow window) {
        window.draw(sprite);
        hpBar.draw(window);
    }

    public MonsterHPBar buildHPBar() {
        return new MonsterHPBar(this);
    }

    public float getCurrentHP() {
        return currentHP;
    }

    public float getMaxHP() {
        return maxHP;
    }

    public FloatRect getBoundingRect() {
        return sprite.getGlobalBounds();
    }

    public Vector2f getVelocity() {
        return velocity;
    }

    public float getMass() {
        return mass;
    }
}
