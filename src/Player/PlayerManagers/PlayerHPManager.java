package Player.PlayerManagers;

import Game.Main;
import Generic.PlayerHPBar;
import org.jsfml.graphics.RenderWindow;
import Player.Player;

/**
 * Created by Matthew on 25/02/14.
 */
public class PlayerHPManager implements PlayerManager {

    private Player player;

    private float maxHP;
    private float currentHP;
    private PlayerHPBar hpBar;

    //per second
    private float hpRegen;

    private static PlayerHPManager instance = null;

    protected PlayerHPManager() {
    }

    public static PlayerHPManager getInstance() {
        if (instance == null) {
            instance = new PlayerHPManager();
        }
        return instance;
    }

    public void setPlayer(Player _player) {
        player = _player;
        maxHP = calculateMaxHP();
        currentHP = maxHP;
        hpBar = new PlayerHPBar(player);

        //0.1% of max per second base
        hpRegen = maxHP / (1000);
    }

    public void update() {
        hpBar.update();

        if (currentHP < maxHP) {
            increaseHP(hpRegen * Main.getDeltaSeconds());
        }

        if (currentHP <= 0) {
            onDeath();
        }
    }

    public void draw(RenderWindow window) {
        hpBar.draw(window);
    }

    public void reduceHP(float damage) {
        currentHP -= damage;
        System.out.println("[PlayerHPManager.reduceHP()] Player received " + damage + " points of damage!");
    }

    public void increaseHP(float amount) {
        currentHP += amount;
        if (currentHP > maxHP) {
            currentHP = maxHP;
        }
    }

    private void onDeath() {
        System.out.println("[PlayerHPManager.onDeath()] Oh no, you died!");
        System.exit(0);
    }

    public float getCurrentHP() {
        return currentHP;
    }

    public float getMaxHP() {
        return maxHP;
    }

    public float getHPRegen() {
        return hpRegen;
    }

    public float calculateMaxHP() {
        return Math.round(12 + (5 * player.getXPLevel()) + (float) Math.exp(Math.sqrt(player.getXPLevel())) * player.getBloodline().getHpMultiplier());
    }

    public void levelUp() {
        maxHP = calculateMaxHP();
    }
}


