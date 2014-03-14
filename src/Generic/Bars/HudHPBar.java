package Generic.Bars;

import Player.PlayerManagers.PlayerHPManager;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by Matthew on 14/03/14.
 */
public class HudHPBar extends HudBar {

    public HudHPBar(Vector2f offset){
        super(Color.RED, offset);
    }

    public void update(){
        super.update();

        float max =  PlayerHPManager.getInstance().getMaxHP();
        float current = PlayerHPManager.getInstance().getCurrentHP();
        float len = (current/max) * maxLen;
        bar.setSize(new Vector2f(len, thickness));

        mainText.setString("HP: " + Math.round(current) + " / " + Math.round(max));
        regenText.setString("+" +new BigDecimal(PlayerHPManager.getInstance().getHPRegen()).round(new MathContext(1)));
    }
}
