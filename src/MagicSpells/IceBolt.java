package MagicSpells;

import Generic.Actor;
import org.jsfml.graphics.Texture;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by Matthew on 17/02/14.
 */
public class IceBolt extends GenericBolt {

    public IceBolt(Actor _belongsTo) {

        super(_belongsTo);

        speed = 10f;
        range = 600f;
        coolDown = 5f;
        damage = 20f;

        Texture texture = new Texture();
        try {
            texture.loadFromFile(Paths.get("resources" + File.separator + "iceBolt.png"));
            buildSprite(texture);
            boltSprite.setScale(0.3f,0.3f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public float getDamage() {
        return damage;
    }

    public void onCollision(Actor collider){
        super.onCollision(collider);
    }
}
