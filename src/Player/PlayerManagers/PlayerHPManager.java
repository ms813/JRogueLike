package Player.PlayerManagers;

import Generic.PlayerHPBar;
import org.jsfml.graphics.RenderWindow;
import Player.Player;

/**
 * Created by Matthew on 25/02/14.
 */
public class PlayerHPManager implements PlayerManager  {

    private Player player;

    private int maxHP;
    private int currentHP;
    private PlayerHPBar hpBar;

    private static PlayerHPManager instance = null;

    protected PlayerHPManager() {}

    public static PlayerHPManager getInstance() {
        if (instance == null) {
            instance = new PlayerHPManager();
        }
        return instance;
    }

    public void setPlayer(Player _player){
        player = _player;
        maxHP = 200;
        currentHP = maxHP;
        hpBar = new PlayerHPBar(player);
    }

    public void update(){
        hpBar.update();
        if(currentHP <= 0){
            onDeath();
        }
    }

    public void draw(RenderWindow window){
        hpBar.draw(window);
    }

    public void reduceHP(int damage){
        currentHP -= damage;
        System.out.println("Player received " + damage + " points of damage!");
    }

    public void increaseHP(int amount){

        int before = currentHP;

        currentHP += amount;
        if(currentHP > maxHP){
            currentHP = maxHP;
        }
        int after = currentHP;
        System.out.println("Player healed " + (after - before) + " hit points!");
    }

    private void onDeath(){
        System.out.println("Oh no, you died!");
        System.exit(0);
    }

    public float getCurrentHP(){
        return currentHP;
    }

    public float getMaxHP(){
        return maxHP;
    }

}


