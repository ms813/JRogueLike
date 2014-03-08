package Player;

import Game.Scene.MapTile;
import Game.UI.TextureLibrary;
import Generic.Actor;
import Generic.DynamicActor;
import Generic.VectorArithmetic;
import Player.PlayerManagers.PlayerHPManager;
import Player.PlayerManagers.PlayerMagicManager;
import Player.PlayerManagers.PlayerMovementManager;
import Player.PlayerManagers.PlayerXPManager;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import sun.print.resources.serviceui_pt_BR;

/**
 * Created with IntelliJ IDEA.
 * User: Matthew
 * Date: 15/02/14
 * Time: 14:15
 * To change this template use File | Settings | File Templates.
 */
public class Player implements DynamicActor {

    private Sprite playerSprite;

    //set up the various skill managers
    private PlayerMagicManager magicManager = PlayerMagicManager.getInstance();
    private PlayerXPManager xpManager = PlayerXPManager.getInstance();
    //private PlayerMeleeManager meleeManager = PlayerMeleeManager.getInstance();
    private PlayerHPManager hpManager = PlayerHPManager.getInstance();
    private PlayerMovementManager movementManager = PlayerMovementManager.getInstance();

    public Player(float x, float y) {

        playerSprite = new Sprite(TextureLibrary.getTexture("player"));
        playerSprite.setScale(0.5f, 0.5f);
        playerSprite.setPosition(x, y);

        //set the origin to the center of the sprite rather than the top left
        playerSprite.setOrigin(playerSprite.getLocalBounds().width / 2, playerSprite.getLocalBounds().height / 2);

        managerSetup();

        learnSpell("MagicDart");
        learnSpell("IceBolt");
    }

    private void managerSetup() {
        magicManager.setPlayer(this);
        xpManager.setPlayer(this);
        //meleeManager.setPlayer(this);
        hpManager.setPlayer(this);
        movementManager.setPlayer(this);
    }

    public void move(float x, float y) {
        movementManager.move(x, y);
    }

    public void move(Vector2f dir) {
        movementManager.move(dir);
    }

    public void changeVelocity(float x, float y) {
        changeVelocity(new Vector2f(x, y));
    }

    public void changeVelocity(Vector2f vector) {
        movementManager.changeVelocity(vector);
    }

    public void castCurrentSpell(Vector2f mousePos) {
        magicManager.castCurrentSpell(mousePos);
    }

    public Sprite getDrawable() {
        return playerSprite;
    }

    public Vector2f getPosition() {
        //return the center of the sprite (see setOrigin() above)
        return playerSprite.getPosition();
    }

    public void learnSpell(String spell) {
        magicManager.learnSpell(spell);
    }

    public void changeCurrentSpell(int wheelTicks) {
        magicManager.changeCurrentSpell(wheelTicks);
    }

    public void reduceHP(int damage) {
        hpManager.reduceHP(damage);
    }

    public void onCollision(Actor collider, FloatRect collisionRect) {
        if (collisionRect != null) {
            if (collider instanceof MapTile) {

                //TODO collision detection here

            }
        }
    }

    public void update() {
        //meleeManager.update();

        movementManager.update();
        hpManager.update();
    }

    public void draw(RenderWindow window) {
        window.draw(playerSprite);
        hpManager.draw(window);
    }

    /*
    public void meleeAttack(Vector2f pos){
       meleeManager.attack(pos);
    }
    */

    public FloatRect getBoundingRect() {
        return playerSprite.getGlobalBounds();
    }

    public Vector2f getVelocity() {
        return movementManager.getVelocity();
    }

    public void setVelocity(float x, float y) {
        movementManager.setVelocity(x, y);
    }

    public void setVelocity(Vector2f vector){
        movementManager.setVelocity(vector);
    }
}
