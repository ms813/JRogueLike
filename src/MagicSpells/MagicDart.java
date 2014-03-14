package MagicSpells;

import Generic.Libraries.TextureLibrary;
import Generic.Actor;
import org.jsfml.graphics.FloatRect;

/**
 * Created by Matthew on 16/02/14.
 */
public class MagicDart extends GenericBolt {

    public MagicDart(Actor _belongsTo) {
        super(_belongsTo);

        friction = 1.0f;
        maxSpeed = 15f;
        acceleration = 15f;
        range = 350f;
        coolDown = 1f;
        damage = "1d6";
        mass = 1f;

        buildSprite(TextureLibrary.getTexture("magicDart"));

        //System.out.println("Target: " + targetPosition + ", Start: " + startPosition);
    }

    public String getDamage() {
        return damage;
    }

    public void onCollision(Actor collider, FloatRect collisionRect) {
        super.onCollision(collider, collisionRect);
    }
}
