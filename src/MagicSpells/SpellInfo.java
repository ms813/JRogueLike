package MagicSpells;

/**
 * Created by Matthew on 20/02/14.
 */
public class SpellInfo {

    private String spellName;
    private float coolDown;
    private float coolDownRemaining;
    private int level;

    public SpellInfo(String _spellName, float _cd, float _cdRemaining, int _level){
        spellName = _spellName;
        coolDown = _cd;
        coolDownRemaining =_cdRemaining;
        level = _level;
    }

    public void reduceCoolDownRemaining(float time){
        coolDownRemaining -= time;
    }

    public String getSpellName() {
        return spellName;
    }

    public float getCoolDown() {
        return coolDown;
    }

    public void setCoolDown(float coolDown) {
        this.coolDown = coolDown;
    }

    public float getCoolDownRemaining() {
        return coolDownRemaining;
    }

    public void setCoolDownRemaining(float coolDownRemaining) {
        this.coolDownRemaining = coolDownRemaining;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
