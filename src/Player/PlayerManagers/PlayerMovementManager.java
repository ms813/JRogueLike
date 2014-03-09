package Player.PlayerManagers;

import Generic.VectorArithmetic;
import Player.Player;
import org.jsfml.system.Vector2f;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Matthew on 01/03/14.
 */
public class PlayerMovementManager implements PlayerManager {

    private static PlayerMovementManager instance = null;

    private Player player;

    private float acceleration = 100f;
    private float maxSpeed = 7f;
    private float friction = 0.9f;
    private float mass = 75f;
    private Vector2f velocity = Vector2f.ZERO;

    protected PlayerMovementManager() {
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

    public void changeVelocity(Vector2f vector, float scaleFactor){
        velocity = Vector2f.mul(Vector2f.add(Vector2f.mul(vector, acceleration), velocity), scaleFactor);
        if (VectorArithmetic.magnitude(velocity) > maxSpeed) {
            velocity = Vector2f.mul(velocity, maxSpeed / VectorArithmetic.magnitude(velocity));
        }
    }

    public void buffMoveSpeed(float speedChange, int timeMillis) {
        acceleration += speedChange;

        class ReduceSpeed extends TimerTask {
            float amount;

            public ReduceSpeed(float amount) {
                this.amount = amount;
            }

            @Override
            public void run() {
                acceleration -= amount;
                System.out.println("timer run reached");
            }
        }

        Timer timer = new Timer();
        timer.schedule(new ReduceSpeed(speedChange), timeMillis);
    }

    public void update(){
        velocity = Vector2f.mul(velocity, friction);
        move(velocity);
    } 
    public Vector2f getVelocity(){
        return velocity;
    }
    public void setVelocity(float x, float y){
        velocity = new Vector2f(x,y);
    }

    public void setVelocity(Vector2f vel){
        velocity = vel;
    }

    public float getMass(){
        return mass;
    }

    public void knockBack(Vector2f dir, float power){
        //velocity = Vector2f.add(velocity, Vector2f.mul(VectorArithmetic.normalize(dir), power/mass));
    }
}
