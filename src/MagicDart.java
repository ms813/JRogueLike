import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by Matthew on 16/02/14.
 */
public class MagicDart extends GenericBolt {

    public MagicDart(Actor _belongsTo, Vector2f _target){
        super(_belongsTo, _target);

        speed = 0.2f;
        range = 350f;

        Texture texture = new Texture();
        try{
            texture.loadFromFile(Paths.get("resources" + File.separator + "magicDart.png"));
             super.buildSprite(texture);
        } catch(IOException e){
            e.printStackTrace();
        }
        //System.out.println("Target: " + targetPosition + ", Start: " + startPosition);
    }


}
