package MagicSpells;

import Generic.Libraries.TextureLibrary;
import org.jsfml.graphics.Sprite;

/**
 * Created by Matthew on 20/02/14.
 */
public class SpellInfo {

    private String spellName;
    private float coolDown;
    private float coolDownRemaining;
    private int level;
    private int castsPerReload;
    private int castsRemaining;
    private float reloadManaCost;
    private Sprite icon;

    public SpellInfo(String spellName, float coolDown, int castsPerReload, float reloadManaCost) {
        this.spellName = spellName;
        this.coolDown = coolDown;
        this.coolDownRemaining = 0;
        this.castsPerReload = castsPerReload;
        castsRemaining = castsPerReload;
        this.reloadManaCost = reloadManaCost;
        this.level = 1;

        icon = new Sprite(TextureLibrary.getTexture(spellName));
    }

    public void reduceCoolDownRemaining(float time) {
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

    public float getReloadManaCost(){
        return reloadManaCost;
    }

    public int getCastsPerReload() {
        return castsPerReload;
    }

    public int getCastsRemaining(){
        return castsRemaining;
    }

    public Sprite getIcon(){
        return icon;
    }

    public void castSpell() {
        coolDownRemaining = coolDown;
        castsRemaining--;
    }

    public boolean isReadyToCast() {
        return coolDownRemaining <= 0 && castsRemaining > 0;
    }

    public void reload() {
        castsRemaining = castsPerReload;
    }
}
