import org.jsfml.system.Vector2f;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Matthew on 18/02/14.
 */
public class PlayerMagicManager {

    private Player player;

    private HashMap coolDownLibrary = new HashMap();

    private List<SpellInfo> knownSpells = new ArrayList<SpellInfo>();
    private SpellInfo currentSpell;

    private static PlayerMagicManager instance = null;

    public static PlayerMagicManager getInstance(){
        if(instance == null){
            instance = new PlayerMagicManager();
        }

        return instance;
    }

    protected PlayerMagicManager(){
        coolDownLibrary.put("MagicDart", 0.10f);
        coolDownLibrary.put("IceBolt", 2.0f);
    }

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
                Class cl = Class.forName(currentSpell.getSpellName());
                Constructor con = cl.getConstructor(Actor.class);
                Object obj = con.newInstance(player);
                MagicSpell spell = (MagicSpell) obj;

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
            }

            if (value.getCoolDownRemaining() <= 0) {
                value.setCoolDownRemaining(0);
            }
        }
    }

    private float getSpellCoolDown(String spellName){
        return (Float) coolDownLibrary.get(spellName);
    }
}
