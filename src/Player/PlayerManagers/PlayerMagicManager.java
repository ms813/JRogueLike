package Player.PlayerManagers;

import Game.Main;
import Game.UI.HUD.HUD;
import Generic.Actor;
import Generic.Libraries.MagicSpellLibrary;
import MagicSpells.MagicSpell;
import MagicSpells.SpellInfo;
import Player.Player;
import org.jsfml.system.Vector2f;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthew on 18/02/14.
 */
public class PlayerMagicManager implements PlayerManager {

    private Player player;

    private List<SpellInfo> knownSpellsInfo = new ArrayList<SpellInfo>();
    private SpellInfo currentSpellInfo;

    private static PlayerMagicManager instance = null;

    private float maxMana;
    private float currentMana;
    private float mpRegen;

    public static PlayerMagicManager getInstance(){
        if(instance == null){
            instance = new PlayerMagicManager();
        }

        return instance;
    }

    protected PlayerMagicManager(){}

    public void setPlayer(Player _player){

        player = _player;
        maxMana = 10f;
        currentMana = maxMana;

        mpRegen = maxMana/1000; //0.1% max mana per sec
    }

    public void learnSpell(String spellName) {
        knownSpellsInfo.add(MagicSpellLibrary.getInstance().getSpellInfo(spellName));
        currentSpellInfo = knownSpellsInfo.get(knownSpellsInfo.size()-1);
    }

    public void castCurrentSpell(Vector2f target) {
        if (currentSpellInfo.isReadyToCast()) {

            //use reflection to build an instance of the class from a string
            try {
                //the Class.forName method must specify the full package address
                Class cl = Class.forName("MagicSpells." + currentSpellInfo.getSpellName());
                Constructor<Actor> con = cl.getConstructor(Generic.Actor.class);
                Object obj = con.newInstance(player);
                MagicSpell spell = (MagicSpell) obj;

                //set the cooldown to max
                currentSpellInfo.castSpell();

                spell.castSpell(target, currentSpellInfo.getLevel());
            } catch (Exception e) {
                //there are like 5 errors to catch here, heedin for puttin them all in one at a time
                e.printStackTrace();
            }
        }  else{
            //spell is on cooldown
        }
    }

    public void changeCurrentSpell(int wheelTicks) {
        int index = knownSpellsInfo.indexOf(currentSpellInfo);

        index += wheelTicks;

        if (index < 0) {
            index = knownSpellsInfo.size() - 1;
        } else if (index > knownSpellsInfo.size() - 1) {
            index = 0;
        }
        currentSpellInfo = knownSpellsInfo.get(index);
        HUD.getInstance().setCurrentSpell(currentSpellInfo);
    }

    public void reduceCoolDownsRemaining(float time) {
        for (SpellInfo value : knownSpellsInfo) {
            if (value.getCoolDownRemaining() > 0) {
                value.reduceCoolDownRemaining(time);
            } else if (value.getCoolDownRemaining() <= 0) {
                value.setCoolDownRemaining(0);
            }
        }
    }

    public void update(){
        reduceCoolDownsRemaining(Main.getDeltaSeconds());

        if (currentMana < maxMana) {
            increaseMana(mpRegen * Main.getDeltaSeconds());
        }
    }

    public void increaseMana(float amount){
        currentMana += amount;

        if(currentMana > maxMana){
            currentMana = maxMana;
        }
    }

    public void decreaseMana(float amount){
        currentMana -= amount;

        if(currentMana < 0){
            currentMana = 0;
        }
    }

    public float getMaxMana() {
        return maxMana;
    }

    public float getCurrentMana() {
        return currentMana;
    }

    public void levelUp(){

    }

    public float getMPRegen(){
        return mpRegen;
    }

    public SpellInfo getCurrentSpellInfo(){
        return currentSpellInfo;
    }

    public void reload(){
        float manaCost = currentSpellInfo.getReloadManaCost();

        if(currentMana > manaCost){
            currentSpellInfo.reload();
            decreaseMana(manaCost);
            System.out.println("[PlayerMagicManager.reload()] " + currentSpellInfo.getSpellName() + " reloaded");
        } else{
            System.out.println("[PlayerMagicManager.reload()] You don't have enough mana to reload that spell!");
        }
        System.out.println("[PlayerMagicManager.reload()] Current mana: " + currentMana + " / " + maxMana);
    }
}
