/**
 * Created by Matthew on 16/02/14.
 */
public interface Projectile extends Actor{
    public float getDamage();
    public boolean belongsTo(Actor actor);
}
