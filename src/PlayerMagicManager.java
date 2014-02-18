import org.jsfml.system.Vector2f;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

/**
 * Created by Matthew on 18/02/14.
 */
public class PlayerMagicManager {

    private Player player;

    private ArrayList<String> knownSpells = new ArrayList<String>();
    private String currentSpell;

    public PlayerMagicManager(Player _player){
        player = _player;
    }

    public void learnSpell(String spell){
        knownSpells.add(spell);
        currentSpell = spell;
    }

    public void castCurrentSpell(Vector2f target){

        //use reflection to build an instance of the class from a string
        try{
            Class cl = Class.forName(currentSpell);
            Constructor con = cl.getConstructor(Actor.class);
            Object obj = con.newInstance(player);
            MagicSpell spell = (MagicSpell) obj;

            spell.castSpell(target);
            Game.getCurrentScene().addActor(spell);
        } catch(Exception e){
            //there are like 5 errors to catch here, heedin for puttin them all in one at a time
            e.printStackTrace();
        }
    }

    public void changeCurrentSpell(int wheelTicks){
        int index = knownSpells.indexOf(currentSpell);

        index += wheelTicks;


        if(index < 0) index = 0;
        if(index > knownSpells.size()-1){
            index = knownSpells.size()-1;
        }
        System.out.println("index: " + index + ", size: " + knownSpells.size());
        currentSpell = knownSpells.get(index);
    }
}
