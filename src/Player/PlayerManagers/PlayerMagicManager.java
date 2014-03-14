package Player.PlayerManagers;

import Game.Main;
import Generic.Actor;
import Generic.Libraries.CooldownLibrary;
import MagicSpells.MagicSpell;
import MagicSpells.SpellInfo;
import MagicSpells.*;
import Player.Player;
import org.jsfml.system.Vector2f;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Matthew on 18/02/14.
 */
public class PlayerMagicManager implements PlayerManager {

    private Player player;

    private List<SpellInfo> knownSpells = new ArrayList<SpellInfo>();
    private SpellInfo currentSpell;

    private static PlayerMagicManager instance = null;

    private float maxMana;
    private float currentMana;

    public static PlayerMagicManager getInstance(){
        if(instance == null){
            instance = new PlayerMagicManager();
        }

        return instance;
    }

    protected PlayerMagicManager(){}

    public void setPlayer(Player _player){
        player = _player;
    }

    public void learnSpell(String spellName) {
        knownSpells.add(new SpellInfo(spellName, getSpellCoolDown(spellName), 0, 1));
        currentSpell = knownSpells.get(knownSpells.size()-1);
    }

    public void castCurrentSpell(Vector2f target) {
        if (currentSpell.getCoolDownRemaining() <= 0) {

            //use reflection to build an instance of the class from a string
            try {
                //the Class.forName method must specify the full package address
                Class cl = Class.forName("MagicSpells." + currentSpell.getSpellName());
                Constructor<Actor> con = cl.getConstructor(Generic.Actor.class);
                Object obj = con.newInstance(player);
                MagicSpell spell = (MagicSpell) obj;

                //set the cooldown to max
                currentSpell.setCoolDownRemaining(currentSpell.getCoolDown());

                spell.castSpell(target, currentSpell.getLevel());
            } catch (Exception e) {
                //there are like 5 errors to catch here, heedin for puttin them all in one at a time
                e.printStackTrace();
            }
        }  else{
            //spell is on cooldown
        }
    }

    public void changeCurrentSpell(int wheelTicks) {
        int index = knownSpells.indexOf(currentSpell);

        index += wheelTicks;

        if (index < 0) {
            index = knownSpells.size() - 1;
        } else if (index > knownSpells.size() - 1) {
            index = 0;
        }
        currentSpell = knownSpells.get(index);
    }

    public void reduceCoolDownsRemaining(float time) {
        for (SpellInfo value : knownSpells) {
            if (value.getCoolDownRemaining() > 0) {
                value.reduceCoolDownRemaining(time);
            } else if (value.getCoolDownRemaining() <= 0) {
                value.setCoolDownRemaining(0);
            }
        }
    }

    private float getSpellCoolDown(String spellName){
        return CooldownLibrary.getInstance().getCooldown(spellName);
    }

    public void update(){
        reduceCoolDownsRemaining(Main.getDeltaSeconds());
    }

    public float getMaxMana() {
        return maxMana;
    }

    public float getCurrentMana() {
        return currentMana;
    }

    public void levelUp(){

    }
}
