import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by Matthew on 16/02/14.
 */
public class MagicDart implements Projectile {

    private String name;
    private Sprite magicDartSprite;
    private Actor belongsTo;
    private float speed = 0.1f;
    private float maxTravelDistance = 350f;
    private Vector2f targetPosition;
    private Vector2f startPosition;
    private boolean readyForDestruction = false;

    public MagicDart(Actor _belongsTo, Vector2f _target){
        name = "magicDart";
        belongsTo = _belongsTo;
        targetPosition = _target;

        startPosition = belongsTo.getCurrentPosition();

        magicDartSprite = new Sprite();

        Texture texture = new Texture();

        try{
            texture.loadFromFile(Paths.get("resources/" + name + ".png"));

            magicDartSprite = new Sprite(texture);
            magicDartSprite.setScale(0.5f, 0.5f);
            magicDartSprite.setPosition(startPosition);

        } catch(IOException e){
            e.printStackTrace();
        }

        System.out.println("Target: " + targetPosition + ", Start: " + startPosition);
    }

    public String getName(){
        return name;
    }

    public Sprite getDrawable(){
        return magicDartSprite;
    }

    public Vector2f getStartPosition(){
        return startPosition;
    }

    public Vector2f getTargetPosition(){
        return targetPosition;
    }

    public Vector2f getCurrentPosition(){
        return magicDartSprite.getPosition();
    }

    public float getSpeed(){
        return speed;
    }

    public void updatePosition(){
        float length = VectorArithmetic.magnitude(Vector2f.sub(startPosition, getCurrentPosition()));
        if(length >= maxTravelDistance){
            System.out.println("Target reached: " + getCurrentPosition());
            readyForDestruction = true;
        } else{
            Vector2f direction = VectorArithmetic.normalize(Vector2f.sub(targetPosition, startPosition));
            magicDartSprite.move(Vector2f.mul(direction, speed));

        }
    }

    public boolean isReadyForDestruction(){
        return readyForDestruction;
    }
}
