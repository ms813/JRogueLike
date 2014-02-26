package MagicSpells;

import Game.UI.TextureLibrary;
import Generic.Actor;
import org.jsfml.graphics.Texture;

import javax.xml.soap.Text;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by Matthew on 16/02/14.
 */
public class MagicDart extends GenericBolt {

    public MagicDart(Actor _belongsTo) {
        super(_belongsTo);

        speed = 15f;
        range = 350f;
        coolDown = 1f;
        damage = 1f;

        buildSprite(TextureLibrary.getTexture("magicDart"));

        //System.out.println("Target: " + targetPosition + ", Start: " + startPosition);
    }

    public float getDamage() {
        return damage;
    }

    public void onCollision(Actor collider) {
        super.onCollision(collider);
    }
}
