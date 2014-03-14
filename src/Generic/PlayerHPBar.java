package Generic;

import Player.Player;
import Player.PlayerManagers.PlayerHPManager;
import org.jsfml.graphics.Color;
import org.jsfml.system.Vector2f;

/**
 * Created by Matthew on 25/02/14.
 */
public class PlayerHPBar extends Bar {

    Player parent;

    public PlayerHPBar(Player _parent){
        parent= _parent;
        maxLen = parent.getDrawable().getLocalBounds().width/2;
        color = Color.RED;
        thickness = 5f;

        bar.setSize(new Vector2f(maxLen, thickness));
        bar.setOrigin(new Vector2f(maxLen/2, 0));
        bar.setFillColor(color);
    }

    public void update(){
        float current = PlayerHPManager.getInstance().getCurrentHP();
        float max = PlayerHPManager.getInstance().getMaxHP();

        float len = (current/max) * maxLen;
        bar.setSize(new Vector2f(len, thickness));

        Vector2f pos = Vector2f.add(parent.getPosition(), new Vector2f(0, parent.getDrawable().getLocalBounds().height/4));
        bar.setPosition(pos);
    }
}
