package MagicSpells;

import Game.UI.TextureLibrary;
import Generic.Actor;
import org.jsfml.graphics.FloatRect;

/**
 * Created by Matthew on 17/02/14.
 */
public class IceBolt extends GenericBolt {

    public IceBolt(Actor _belongsTo) {

        super(_belongsTo);

        friction = 1.0f;
        maxSpeed = 10f;
        acceleration = 10f;
        range = 600f;
        coolDown = 5f;
        damage = "5d4";

        buildSprite(TextureLibrary.getTexture("iceBolt"));
        boltSprite.setScale(0.3f, 0.3f);
    }

    public String getDamage() {
        return damage;
    }

    public void onCollision(Actor collider, FloatRect collisionRect) {
        super.onCollision(collider,collisionRect);
    }
}
