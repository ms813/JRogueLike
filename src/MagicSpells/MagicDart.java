package MagicSpells;

import Generic.Actor;
import org.jsfml.graphics.Texture;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by Matthew on 16/02/14.
 */
public class MagicDart extends GenericBolt {

    public MagicDart(Actor _belongsTo){
        super(_belongsTo);

        speed = 15f;
        range = 350f;
        coolDown = 1f;
        damage = 1f;

        Texture texture = new Texture();
        try{
            texture.loadFromFile(Paths.get("resources" + File.separator + "magicDart.png"));
            buildSprite(texture);
        } catch(IOException e){
            e.printStackTrace();
        }
        //System.out.println("Target: " + targetPosition + ", Start: " + startPosition);
    }

    public float getDamage(){
        return damage;
    }

    public void onCollision(Actor collider){
       super.onCollision(collider);
    }
}
