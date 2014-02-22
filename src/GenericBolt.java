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
    protected float speed;
    protected float range;
    protected Vector2f targetPosition;
    protected Vector2f startPosition;
    protected Vector2f travelVector;
    protected boolean readyForDestruction = false;
    protected float coolDown;
    protected float damage;

    protected GenericBolt() {
    }

    protected GenericBolt(Actor _belongsTo) {
        //this should never be implemented as a generic class
        belongsTo = _belongsTo;
        startPosition = belongsTo.getCurrentPosition();
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
        float rot = VectorArithmetic.AngleBetweenVectorsDegrees(new Vector2f(0, -1), travelVector);
        if (travelVector.x < 0) {
            rot *= -1;
        }
        boltSprite.setRotation(rot);
        Game.getCurrentScene().addActor(this);
    }

    public void update() {
        //update position
        float distanceTravelled = VectorArithmetic.magnitude(Vector2f.sub(startPosition, getCurrentPosition()));
        if (distanceTravelled >= range) {
            //System.out.println("Target reached: " + getCurrentPosition());
            readyForDestruction = true;
        } else {
            Vector2f direction = VectorArithmetic.normalize(travelVector);
            boltSprite.move(Vector2f.mul(direction, speed));

        }
    }

    public void onCollision(Actor collider) {
        if (collider != belongsTo) {

            //have I hit a monster?
            if (collider instanceof Monster) {
                //was I cast by another monster?
                if (!(belongsTo instanceof Monster)) {
                    //if not, do some damage!
                    ((Monster) collider).reduceHP(damage);
                    readyForDestruction = true;
                }
            }

            //have I hit a player?
            if (collider instanceof Player) {
                ((Player) collider).reduceHP(damage);
                readyForDestruction = true;
            }

        }
    }

    public Sprite getDrawable() {
        return boltSprite;
    }

    public Vector2f getCurrentPosition() {
        return boltSprite.getPosition();
    }

    public boolean isReadyForDestruction() {
        return readyForDestruction;
    }

    public abstract float getDamage();

    public boolean belongsTo(Actor actor) {
        return actor == belongsTo;
    }

    public void draw(RenderWindow window){
        window.draw(boltSprite);
    }
}
