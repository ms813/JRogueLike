package Player;

import Game.Scene.MapTile;
import Game.UI.TextureLibrary;
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

    private boolean readyForDestruction = false;

    //set up the various skill managers
    private PlayerMagicManager magicManager = PlayerMagicManager.getInstance();
    private PlayerXPManager xpManager = PlayerXPManager.getInstance();
    //private PlayerMeleeManager meleeManager = PlayerMeleeManager.getInstance();
    private PlayerHPManager hpManager = PlayerHPManager.getInstance();
    private PlayerMovementManager movementManager = PlayerMovementManager.getInstance();

    public Player(float x, float y) {

        playerSprite = new Sprite(TextureLibrary.getTexture("player"));
        playerSprite.setScale(0.5f, 0.5f);
        playerSprite.setPosition(x,y);

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

    public void onCollision(Actor collider) {
        if (collider instanceof MapTile) {

            FloatRect tileRect = ((MapTile) collider).getDrawable().getBounds();
            FloatRect intersectRect = playerSprite.getGlobalBounds().intersection(tileRect);

            if (intersectRect != null) {

                if (playerSprite.getPosition().x < collider.getPosition().x) {
                    //approaching from the left
                    move(new Vector2f(-intersectRect.width, 0));

                } else if (playerSprite.getPosition().x > collider.getPosition().x + ((MapTile) collider).getWidth()) {
                    //approaching from the right
                    move(new Vector2f(intersectRect.width, 0));
                }

                if (playerSprite.getPosition().y < collider.getPosition().y) {
                    //approaching from the top
                    move(new Vector2f(0, -intersectRect.height));
                } else if (playerSprite.getPosition().y > collider.getPosition().y + ((MapTile) collider).getHeight()) {
                    //approaching from the bottom
                    move(new Vector2f(0, intersectRect.height));
                }

                System.out.println(intersectRect);
            }
        }
    }

    public void update() {

        //meleeManager.update();
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

    public FloatRect getCollisionRect(){
        return new FloatRect(playerSprite.getGlobalBounds().left, playerSprite.getGlobalBounds().top, playerSprite.getGlobalBounds().width/2, playerSprite.getGlobalBounds().height/2);
    }
}
