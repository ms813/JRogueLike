/*
package Player.PlayerManagers;

import Generic.VectorArithmetic;
import Player.Player;
import Items.Longsword;
import MeleeCombat.MeleeWeapon;
import MeleeCombat.MeleeWeaponAttackType;
import org.jsfml.system.Vector2f;

*/
/**
 * Created by Matthew on 24/02/14.
 *//*

public class PlayerMeleeManager {

    private Player player;

    private static PlayerMeleeManager instance = null;

    MeleeWeapon currentWeapon;

    protected PlayerMeleeManager(){
        currentWeapon = new Longsword();
    }

    public static PlayerMeleeManager getInstance(){
        if(instance == null){
            instance = new PlayerMeleeManager();
        }

        return instance;
    }

    public void changeWeapon(){

    }

    public void attack(Vector2f pos){
        Vector2f dir = VectorArithmetic.normalize(Vector2f.sub(pos, player.getCurrentPosition()));

        if(currentWeapon.getAttackType() == MeleeWeaponAttackType.SLASH){
            float startRot = VectorArithmetic.AngleBetweenVectorsDegrees(VectorArithmetic.UP, dir);
            if (dir.x < 0) {
                startRot *= -1;
            }

            currentWeapon.attack(startRot);
        }

    }

    public void setPlayer(Player _player){
        player = _player;
    }

    public Player getPlayer(){
        return player;
    }

    public void update(){
        currentWeapon.update();
    }

}
*/
