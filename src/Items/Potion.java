package Items;

import Game.Game;
import Generic.Actor;
import Player.PlayerManagers.PlayerInventoryManager;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import Player.Player;

/**
 * Created by Matthew on 25/02/14.
 */
public abstract class Potion implements Consumeable {

    protected String name;
    protected Sprite sprite;
    protected Sprite icon;
    protected boolean stackable = true;

    protected float coolDown;

    public abstract void quaff();

    public void update() {

    }

    public void draw(RenderWindow window) {
        window.draw(sprite);
    }

    public void pickUp(Actor actor) {
        if (actor instanceof Player) {
            PlayerInventoryManager.getInstance().addItem(this,1);
            System.out.println("Picked up " + this.getClass());
        }
        Game.getCurrentScene().removeActor(this);
    }

    public void onCollision(Actor actor) {
        if (actor instanceof Player) {
            pickUp(actor);
        }
    }

    public void setPosition(Vector2f _position) {
        sprite.setPosition(_position);
    }

    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }

    public Sprite getIcon(){
        return icon;
    }

    public abstract float getCoolDown();

    public boolean isStackable(){
        return stackable;
    }

    public String getName(){
        return name;
    }
}
