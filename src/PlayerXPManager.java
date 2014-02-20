/**
 * Created by Matthew on 19/02/14.
 */
public class PlayerXPManager {

    private Player player;

    private int currentLevel;

    private static float currentXP;

    public PlayerXPManager(Player _player){
        player = _player;

        //might have to move this, but seems sensible that only 1 should be created per game
        currentLevel = 1;
        currentXP = 0;
    }


    public static void gainXP(float xp){
        currentXP += xp;
        System.out.println("Player xp increased to: " + currentXP);

    }
}
