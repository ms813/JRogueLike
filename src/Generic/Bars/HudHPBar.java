package Generic.Bars;

import Generic.Libraries.FontLibrary;
import Player.PlayerManagers.PlayerHPManager;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by Matthew on 14/03/14.
 */
public class HudHPBar extends HudBar {

    protected Text regenText = new Text("", FontLibrary.getFont("arial"), 10);

    public HudHPBar(Vector2f offset) {
        super(Color.RED, offset);
        regenText.setColor(Color.BLACK);
    }

    @Override
    public void update() {

        float max = PlayerHPManager.getInstance().getMaxHP();
        float current = PlayerHPManager.getInstance().getCurrentHP();
        float len = (current / max) * maxLen;
        bar.setSize(new Vector2f(len, thickness));

        mainText.setString("HP: " + Math.round(current) + " / " + Math.round(max));
        regenText.setString("+" + new BigDecimal(PlayerHPManager.getInstance().getHPRegen()).round(new MathContext(1)));
        regenText.setPosition(new Vector2f(bar.getGlobalBounds().left +bar.getLocalBounds().width - regenText.getLocalBounds().width, bar.getGlobalBounds().top + 3));
    }

    @Override
    public void draw(RenderWindow window) {
        super.draw(window);
        window.draw(regenText);
    }

}
