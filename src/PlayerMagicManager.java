import org.jsfml.system.Vector2f;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Matthew on 18/02/14.
 */
public class PlayerMagicManager {

    private static Player player;

    private static HashMap coolDownLibrary = new HashMap();

    private static List<SpellInfo> knownSpells = new ArrayList<SpellInfo>();
    private static SpellInfo currentSpell;

    public PlayerMagicManager(Player _player) {
        player = _player;

        coolDownLibrary.put("MagicDart", 0.10f);
        coolDownLibrary.put("IceBolt", 2.0f);

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

                spell.castSpell(target);
                Game.getCurrentScene().addActor(spell);
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

    public static void reduceCoolDownsRemaining(float time) {
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
