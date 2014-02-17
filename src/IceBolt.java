import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by Matthew on 17/02/14.
 */
public class IceBolt extends GenericBolt {

    public IceBolt(Actor _belongsTo, Vector2f _target){
        super(_belongsTo, _target);

        speed = 0.1f;
        range = 600f;


        Texture texture = new Texture();
        try{
            texture.loadFromFile(Paths.get("resources" + File.separator + "iceBolt.png"));
            super.buildSprite(texture);
            boltSprite.setScale(0.3f, 0.3f);
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
