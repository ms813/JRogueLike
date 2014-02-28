package MagicSpells;

import Generic.Actor;

/**
 * Created by Matthew on 16/02/14.
 */
public interface Projectile extends Actor {
    public String getDamage();
    public boolean belongsTo(Actor actor);
}
