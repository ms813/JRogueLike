package MagicSpells;

import Generic.Actor;
import org.jsfml.system.Vector2f;

/**
 * Created by Matthew on 18/02/14.
 */
public interface MagicSpell extends Actor {
    void castSpell(Vector2f target, int level);
}
