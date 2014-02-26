package MeleeCombat;

/**
 * Created by Matthew on 24/02/14.
 */
public abstract class MeleeWeapon implements Weapon {

    protected float damage;
    protected String name;

    protected boolean stackable = false;

    protected MeleeWeaponAttackType attackType;

    public abstract float getDamage();

    public MeleeWeaponAttackType getAttackType(){
        return attackType;
    }

    public abstract void attack(float rot);

    public boolean isStackable(){
        return stackable;
    }

    public String getName(){
        return name;
    }
}
