package Player.PlayerManagers;

import Generic.VectorArithmetic;
import Player.Player;
import org.jsfml.system.Vector2f;

/**
 * Created by Matthew on 01/03/14.
 */
public class PlayerMovementManager implements PlayerManager {

    private static PlayerMovementManager instance = null;

    private Player player;

    private float moveSpeed = 5f;

    protected PlayerMovementManager(){}

    public static PlayerMovementManager getInstance(){
        if(instance == null){
            instance = new PlayerMovementManager();
        }
        return instance;
    }

    public void setPlayer(Player _player){
        player = _player;
    }

    public void move(float x, float y){

        Vector2f vel = new Vector2f(x, y);

        player.getDrawable().move(Vector2f.mul(VectorArithmetic.normalize(vel), moveSpeed));

    }
}
