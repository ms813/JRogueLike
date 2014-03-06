package MagicSpells;

import Game.UI.TextureLibrary;
import Generic.Actor;

/**
 * Created by Matthew on 17/02/14.
 */
public class IceBolt extends GenericBolt {

    public IceBolt(Actor _belongsTo) {

        super(_belongsTo);

        moveSpeed = 10f;
        range = 600f;
        coolDown = 5f;
        damage = "5d4";

        buildSprite(TextureLibrary.getTexture("iceBolt"));
        boltSprite.setScale(0.3f, 0.3f);

    }

    public String getDamage() {
        return damage;
    }

    public void onCollision(Actor collider) {
        super.onCollision(collider);
    }
}
