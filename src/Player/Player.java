package Player;

import Game.Scene.Passable;
import Game.Scene.MapTile;
import Generic.Libraries.BloodlineLibrary;
import Generic.Libraries.TextureLibrary;
import Generic.Actor;
import Generic.DynamicActor;
import Player.PlayerManagers.PlayerHPManager;
import Player.PlayerManagers.PlayerMagicManager;
import Player.PlayerManagers.PlayerMovementManager;
import Player.PlayerManagers.PlayerXPManager;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

/**
 * Created with IntelliJ IDEA.
 * User: Matthew
 * Date: 15/02/14
 * Time: 14:15
 * To change this template use File | Settings | File Templates.
 */
public class Player implements DynamicActor {

    private Sprite playerSprite;
    private Bloodline bloodline = BloodlineLibrary.getInstance().getBloodline("Abhorsen");

    //set up the various skill managers
    //private PlayerMeleeManager meleeManager = PlayerMeleeManager.getInstance();
    private PlayerMagicManager magicManager = PlayerMagicManager.getInstance();
    private PlayerXPManager xpManager = PlayerXPManager.getInstance();
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
        changeVelocity(new Vector2f(x, y), 1);
    }

    public void changeVelocity(Vector2f vector) {
        changeVelocity(vector, 1);
    }

    public void changeVelocity(Vector2f vector, float scaleFactor) {
        movementManager.changeVelocity(vector, scaleFactor);
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

    public void reduceHP(float damage) {
        hpManager.reduceHP(damage);
    }

    public void onCollision(Actor collider, FloatRect collisionRect) {
        if (collider instanceof MapTile) {
            movementManager.onCollision(collider, collisionRect);
        }
    }

    public void update() {
        //meleeManager.update();

        movementManager.update();
        hpManager.update();
        magicManager.update();
        xpManager.update();
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

    public void setVelocity(Vector2f vector) {
        movementManager.setVelocity(vector);
    }

    public float getMass() {
        return movementManager.getMass();
    }

    public void knockBack(Vector2f dir, float power) {
        movementManager.knockBack(dir, power);
    }

    public Bloodline getBloodline() {
        return bloodline;
    }

    public int getXPLevel() {
        return xpManager.getXPLevel();
    }

    public boolean isFlying(){
        return movementManager.isFlying();
    }
}
