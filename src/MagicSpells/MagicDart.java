package MagicSpells;

import Game.UI.TextureLibrary;
import Generic.Actor;

/**
 * Created by Matthew on 16/02/14.
 */
public class MagicDart extends GenericBolt {

    public MagicDart(Actor _belongsTo) {
        super(_belongsTo);

        moveSpeed = 15f;
        range = 350f;
        coolDown = 1f;
        damage = "1d6";

        buildSprite(TextureLibrary.getTexture("magicDart"));

        //System.out.println("Target: " + targetPosition + ", Start: " + startPosition);
    }

    public String getDamage() {
        return damage;
    }

    public void onCollision(Actor collider) {
        super.onCollision(collider);
    }
}
