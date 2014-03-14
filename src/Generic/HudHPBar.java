package Generic;

import Player.PlayerManagers.PlayerHPManager;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

/**
 * Created by Matthew on 14/03/14.
 */
public class HudHPBar extends HudBar {



    public HudHPBar(Vector2f offset){
        super(Color.RED, offset);
    }

    public void update(){

        float max =  PlayerHPManager.getInstance().getMaxHP();
        float current = PlayerHPManager.getInstance().getCurrentHP();
        float len = (current/max) * maxLen;
        bar.setSize(new Vector2f(len, thickness));

        text.setString("HP: " + Math.round(current) + " / " + Math.round(max));
    }
}
