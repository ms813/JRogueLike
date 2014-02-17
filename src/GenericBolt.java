import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

/**
 * Created by Matthew on 17/02/14.
 */
public abstract class GenericBolt implements Projectile {

    protected Sprite boltSprite;
    protected Actor belongsTo;
    protected float speed;
    protected float range;
    protected Vector2f targetPosition;
    protected Vector2f startPosition;
    protected Vector2f travelVector;
    protected boolean readyForDestruction = false;

    //protected GenericBolt(){;}

    protected GenericBolt( Actor _belongsTo, Vector2f _target){
        //this should never be implemented as a generic class
        belongsTo = _belongsTo;

        targetPosition = _target;
        startPosition = belongsTo.getCurrentPosition();
        travelVector = Vector2f.sub(targetPosition, startPosition);
    }

    protected void buildSprite(Texture texture){
        boltSprite = new Sprite(texture);
        boltSprite.setPosition(startPosition);

        //define rotation relative to the up axis of the start position
        float rot = VectorArithmetic.AngleBetweenVectorsDegrees(new Vector2f(0, -1), travelVector);
        if(travelVector.x < 0){
            rot *= -1;
        }
        boltSprite.setRotation(rot);
        System.out.println(rot);
    }

    public Sprite getDrawable(){
        return boltSprite;
    }

    public Vector2f getStartPosition(){
        return startPosition;
    }

    public Vector2f getTargetPosition(){
        return targetPosition;
    }

    public Vector2f getCurrentPosition(){
        return boltSprite.getPosition();
    }

    public float getSpeed(){
        return speed;
    }

    public void updatePosition(){
        float distanceTravelled = VectorArithmetic.magnitude(Vector2f.sub(startPosition, getCurrentPosition()));
        if(distanceTravelled >= range){
            //System.out.println("Target reached: " + getCurrentPosition());
            readyForDestruction = true;
        } else{
            Vector2f direction = VectorArithmetic.normalize(travelVector);
            boltSprite.move(Vector2f.mul(direction, speed));

        }
    }

    public boolean isReadyForDestruction(){
        return readyForDestruction;
    }
}
