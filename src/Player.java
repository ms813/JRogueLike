import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created with IntelliJ IDEA.
 * User: Matthew
 * Date: 15/02/14
 * Time: 14:15
 * To change this template use File | Settings | File Templates.
 */
public class Player implements Actor {

    private String playerName;
    private Sprite playerSprite;

    //used for movement
    private float velX = 0;
    private float velY = 0;
    private float speed = 0.05f;
    private float friction = 0.9f;

    public Player(String _playerName){
        playerName = _playerName;
        playerSprite = new Sprite();

        Texture playerTexture = new Texture();

        try{
            playerTexture.loadFromFile(Paths.get("resources/" + _playerName + ".png"));

            playerSprite = new Sprite(playerTexture);
            playerSprite.setScale(0.5f, 0.5f);

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void move(){

        if(InputBooleans.up){
            if(velY > -speed){
                velY--;
            }
        }
        if(InputBooleans.down){
            if(velY < speed){
                velY++;
            }
        }
        if(InputBooleans.left){
            if(velX > -speed){
                velX--;
            }
        }
        if(InputBooleans.right){
            if(velX < speed){
                velX++;
            }
        }

        velY *= friction;
        velX *= friction;

        playerSprite.move(new Vector2f(velX, velY));
    }

    public Sprite getDrawable(){
        return playerSprite;
    }

    public String getName(){
        return playerName;
    }

    public Vector2f getCurrentPosition(){
        //return the top left corner
        return playerSprite.getPosition();
    }

    public float getSpeed(){
        return VectorArithmetic.magnitude(new Vector2f(velX, velY));
    }

    public Vector2f getVelocity(){
        return new Vector2f(velX, velY);
    }
}
