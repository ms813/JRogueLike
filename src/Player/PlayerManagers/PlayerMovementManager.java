package Player.PlayerManagers;

import Game.CollisionManager;
import Game.Scene.MapTile;
import Game.Scene.MapTileLibrary;
import Game.Scene.Passable;
import Generic.Actor;
import Generic.VectorArithmetic;
import Player.Player;
import org.jsfml.graphics.FloatRect;
import org.jsfml.system.Vector2f;

/**
 * Created by Matthew on 01/03/14.
 */
public class PlayerMovementManager implements PlayerManager {

    private static PlayerMovementManager instance = null;

    private Player player;

    private float acceleration = 100f;
    private float currentMaxSpeed = 7f;
    private float idealMaxSpeed;
    private float friction = 0.9f;
    private float mass = 75f;
    private Vector2f velocity = Vector2f.ZERO;

    private boolean flying = false;

    protected PlayerMovementManager() {
        idealMaxSpeed = currentMaxSpeed;
    }

    public static PlayerMovementManager getInstance() {
        if (instance == null) {
            instance = new PlayerMovementManager();
        }
        return instance;
    }

    public void setPlayer(Player _player) {
        player = _player;
    }

    public void move(float x, float y) {
        move(new Vector2f(x, y));
    }

    public void move(Vector2f dir) {
        player.getDrawable().move(dir);
    }

    public void changeVelocity(Vector2f vector, float scaleFactor) {
        velocity = Vector2f.mul(Vector2f.add(Vector2f.mul(vector, acceleration), velocity), scaleFactor);
        if (VectorArithmetic.magnitude(velocity) > currentMaxSpeed) {
            velocity = Vector2f.mul(velocity, currentMaxSpeed / VectorArithmetic.magnitude(velocity));
        }
    }

    public void buffMoveSpeed(float speedChange, int timeMillis) {
    }

    public void update() {
        velocity = Vector2f.mul(velocity, friction);
        move(velocity);
    }

    public Vector2f getVelocity() {
        return velocity;
    }

    public void setVelocity(float x, float y) {
        velocity = new Vector2f(x, y);
    }

    public void setVelocity(Vector2f vel) {
        velocity = vel;
    }

    public float getMass() {
        return mass;
    }

    public void knockBack(Vector2f dir, float power) {
        //velocity = Vector2f.add(velocity, Vector2f.mul(VectorArithmetic.normalize(dir), power/mass));
    }

    public void levelUp() {

    }

    public boolean isFlying() {
        return flying;
    }

    public void setFlying(boolean flying) {
        this.flying = flying;
    }

    public void onCollision(Actor collider, FloatRect collisionRect) {
        if (collisionRect != null) {
            if (collider instanceof MapTile) {
                MapTile tile = (MapTile) collider;

                if (tile.getTileRef() == MapTileLibrary.WATER_SHALLOW) {
                    currentMaxSpeed = idealMaxSpeed / 2;
                    System.out.println("[PlayerMovementManager.onCollision()] You are slowed by the shallow water");
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

                    FloatRect playerBoundingRect = player.getBoundingRect();
                    FloatRect colliderBoundingRect = tile.getBoundingRect();

                    boolean pp_collides_x = CollisionManager.collidesByAxis(playerBoundingRect.left, playerBoundingRect.width, colliderBoundingRect.left, colliderBoundingRect.width);
                    boolean pp_collides_y = CollisionManager.collidesByAxis(playerBoundingRect.top, playerBoundingRect.height, colliderBoundingRect.top, colliderBoundingRect.height);

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


                    if (collisionRect.left <= playerBoundingRect.left) {
                        //collided on the right hand side
                        xOverlap = collisionRect.width;
                    } else {
                        //collided on the left
                        xOverlap = -collisionRect.width;
                    }

                    if (collisionRect.top <= playerBoundingRect.top) {
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
}
