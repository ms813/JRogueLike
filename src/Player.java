import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

import java.io.File;
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

    private Sprite playerSprite;

    private int maxHP;
    private int currentHP;
    private int XP;

    private boolean readyForDestruction = false;

    private float moveSpeed = 5f;

    //manager that deals with spell learning, selection and casting
    private PlayerMagicManager magicManager = PlayerMagicManager.getInstance();

    private PlayerXPManager xpManager = PlayerXPManager.getInstance();

    public Player(){

        Texture playerTexture = new Texture();

        try{
            playerTexture.loadFromFile(Paths.get("resources"+ File.separatorChar + "player.png"));

            playerSprite = new Sprite(playerTexture);
            playerSprite.setScale(0.5f, 0.5f);

            //set the origin to the center of the sprite rather than the top left
            playerSprite.setOrigin(playerSprite.getLocalBounds().width/2, playerSprite.getLocalBounds().height/2);

        } catch(IOException e){
            e.printStackTrace();
        }

        magicManager.setPlayer(this);
        xpManager.setPlayer(this);

        learnSpell("MagicDart");
        learnSpell("IceBolt");

        maxHP = 200;
        currentHP = maxHP;
    }

    public void move(float x, float y){

        Vector2f vel = new Vector2f(x,y);

        playerSprite.move(Vector2f.mul(VectorArithmetic.normalize(vel), moveSpeed));
    }

    public void castCurrentSpell(Vector2f mousePos){
        magicManager.castCurrentSpell(mousePos);
    }

    public Sprite getDrawable(){
        return playerSprite;
    }

    public Vector2f getCurrentPosition(){
        //return the center of the sprite (see setOrigin() above)
        return playerSprite.getPosition();
    }

    public void learnSpell(String spell){
        magicManager.learnSpell(spell);
    }

    public void changeCurrentSpell(int wheelTicks){
        magicManager.changeCurrentSpell(wheelTicks);
    }

    public void reduceHP(float damage){
        currentHP -= damage;
    }

    public void onCollision(Actor collider){

    }

    public boolean isReadyForDestruction(){
        return readyForDestruction;
    }

    public void update(){
        if(currentHP <= 0){
            onDeath();
        }
    }

    public void onDeath(){
        //System.out.println("Oh no, you died!");
        //System.exit(0);
    }

    public void draw(RenderWindow window){
        window.draw(playerSprite);
    }
}
