package Player.PlayerManagers;

import Generic.CSVLoader;
import Player.Player;
import Player.Bloodline;
import Generic.Libraries.BloodlineLibrary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Matthew on 19/02/14.
 */
public class PlayerXPManager implements PlayerManager {

    private Player player;

    private int currentLevel = 0;

    private HashMap<Integer, Integer> xpTable = new HashMap<Integer, Integer>();

    private float currentXP;

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
        try {
            ArrayList<String[]> fromFile = CSVLoader.load("resources/vals/levelUp");
            for (String[] line : fromFile) {
                xpTable.put(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void levelUp(){
        currentLevel++;
        PlayerHPManager.getInstance().levelUp();
        System.out.println("[PlayerXPManager.levelUp()] Player level increased to " + currentLevel);
    }

    public void gainXP(float xp){
        currentXP += xp;
        System.out.println("[PlayerXPManager.gainXP()] Player xp increased to: " + currentXP);

        if(currentXP >= xpTable.get(currentLevel + 1)){
            levelUp();
        }
    }

    public void setPlayer(Player _player){
        player = _player;
    }

    public Player getPlayer(){
        return player;
    }

    public void update(){

    }

    public int getXPLevel(){ return currentLevel;}

    public int getXPRequiredForLevel(int level){
        return xpTable.get(level);
    }

    public float getProgressToNextLevel(){
        return (currentXP - xpTable.get(currentLevel)) / (xpTable.get(currentLevel +1) - xpTable.get(currentLevel));
    }

    public float getCurrentXP(){
        return currentXP;
    }
}
