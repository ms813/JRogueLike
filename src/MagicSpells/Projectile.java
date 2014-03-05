package MagicSpells;

import Generic.Actor;
import Generic.DynamicActor;

/**
 * Created by Matthew on 16/02/14.
 */
public interface Projectile extends DynamicActor {
    public String getDamage();
    public boolean belongsTo(Actor actor);
}
