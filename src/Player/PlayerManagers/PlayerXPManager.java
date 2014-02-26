package Player.PlayerManagers;

import Player.Player;

/**
 * Created by Matthew on 19/02/14.
 */
public class PlayerXPManager {

    private Player player;

    private int currentLevel;

    private float currentXP;
    private float xpToNextLevel;

    private static PlayerXPManager instance = null;

    public static PlayerXPManager getInstance(){
        if(instance == null){
            instance = new PlayerXPManager();
        }

        return instance;
    }

    protected PlayerXPManager(){
        currentLevel = 1;
        currentXP = 0;
    }

    private void levelUp(){

    }

    public void gainXP(float xp){
        currentXP += xp;
        System.out.println("[PlayerXPManager.gainXP()]Player xp increased to: " + currentXP);
    }

    public void setPlayer(Player _player){
        player = _player;
    }

    public Player getPlayer(){
        return player;
    }
}
