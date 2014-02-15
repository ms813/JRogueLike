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

    public Player(String _playerName){
        playerName = _playerName;
        playerSprite = new Sprite();

        Texture playerTexture = new Texture();

        try{
            playerTexture.loadFromFile(Paths.get("resources/" + _playerName + ".png"));

            playerSprite = new Sprite(playerTexture);

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public Sprite getDrawable(){
       return playerSprite;
    }

    public String getName(){
        return playerName;
    }

    float velX = 0;
    float velY = 0;
    float speed = 0.05f;
    float friction = 0.9f;

    boolean[] directions = new boolean[3];

    public void move(){

        if(MoveDirections.up){
            if(velY > -speed){
                velY--;
            }
        }
        if(MoveDirections.down){
            if(velY < speed){
                velY++;
            }
        }
        if(MoveDirections.left){
            if(velX > -speed){
                velX--;
            }
        }
        if(MoveDirections.right){
            if(velX < speed){
                velX++;
            }
        }

        velY *= friction;
        velX *= friction;

        playerSprite.move(new Vector2f(velX, velY));
    }
}
