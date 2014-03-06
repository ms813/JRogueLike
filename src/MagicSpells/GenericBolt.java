package MagicSpells;

import Game.Scene.MapTile;
import Generic.Actor;
import Generic.Dice;
import Generic.VectorArithmetic;
import Game.Game;
import Monsters.Monster;
import Player.Player;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

/**
 * Created by Matthew on 17/02/14.
 */
public abstract class GenericBolt implements Projectile, MagicSpell {

    protected Sprite boltSprite;
    protected Actor belongsTo;
    protected float moveSpeed;
    protected float range;
    protected Vector2f targetPosition;
    protected Vector2f startPosition;
    protected Vector2f travelVector;
    protected float coolDown;
    protected String damage;

    protected GenericBolt() {
    }

    protected GenericBolt(Actor _belongsTo) {
        //this should never be implemented as a generic class
        belongsTo = _belongsTo;
        startPosition = belongsTo.getPosition();
    }

    protected void buildSprite(Texture _texture) {
        boltSprite = new Sprite(_texture);

        //set the origin to the center of the sprite rather than the top left
        boltSprite.setOrigin(boltSprite.getLocalBounds().width / 2, boltSprite.getLocalBounds().height / 2);
        boltSprite.setPosition(startPosition);
    }

    public void castSpell(Vector2f _target, int level) {
        targetPosition = _target;
        travelVector = Vector2f.sub(targetPosition, startPosition);
        //define rotation relative to the up axis of the start position
        float rot = VectorArithmetic.AngleBetweenVectorsDegrees(VectorArithmetic.UP, travelVector);
        if (travelVector.x < 0) {
            rot *= -1;
        }
        boltSprite.setRotation(rot);
        Game.getCurrentScene().addDynamicActor(this);
    }

    public void update() {
        //update position
        float distanceTravelled = VectorArithmetic.magnitude(Vector2f.sub(startPosition, getPosition()));
        if (distanceTravelled >= range) {
            //System.out.println("Target reached: " + getPosition());
            Game.getCurrentScene().removeDynamicActor(this);
        } else {
           move(travelVector);
        }
    }

    public void move(float x, float y){
        move(new Vector2f(x,y));
    }

    public void move(Vector2f dir){
        boltSprite.move(Vector2f.mul(VectorArithmetic.normalize(dir), moveSpeed));
    }

    public void onCollision(Actor collider) {
        if (collider != belongsTo) {

            //have I hit a monster?
            if (collider instanceof Monster) {
                //was I cast by another monster?
                if (!(belongsTo instanceof Monster)) {
                    //if not, do some damage!
                    ((Monster) collider).reduceHP(Dice.roll(damage));
                    Game.getCurrentScene().removeDynamicActor(this);
                }
            }

            //have I hit a player?
            if (collider instanceof Player) {
                ((Player) collider).reduceHP(Dice.roll(damage));
                Game.getCurrentScene().removeDynamicActor(this);
            }

            //have I hit some impassable terrain?
            if(collider instanceof MapTile){
                Game.getCurrentScene().removeDynamicActor(this);
            }

        }
    }

    public Sprite getDrawable() {
        return boltSprite;
    }

    public Vector2f getPosition() {
        return boltSprite.getPosition();
    }
    public abstract String getDamage();

    public boolean belongsTo(Actor actor) {
        return actor == belongsTo;
    }

    public void draw(RenderWindow window){
        window.draw(boltSprite);
    }

    public FloatRect getCollisionRect(){
        return boltSprite.getGlobalBounds();
    }
}
