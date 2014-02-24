/**
 * Created by Matthew on 24/02/14.
 */
public abstract class MeleeWeapon implements Weapon {

    protected float damage;

    protected MeleeWeaponAttackType attackType;

    public abstract float getDamage();

    public MeleeWeaponAttackType getAttackType(){
        return attackType;
    }

    public abstract void attack(float rot);
}
