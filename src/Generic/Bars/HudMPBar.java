package Generic.Bars;

import Generic.Libraries.FontLibrary;
import Player.PlayerManagers.PlayerMagicManager;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by Matthew on 16/03/14.
 */
public class HudMPBar extends HudBar {

    protected Text regenText = new Text("", FontLibrary.getFont("arial"), 10);

    public HudMPBar(Vector2f offset){
        super(Color.BLUE, offset);
        regenText.setColor(Color.BLACK);
    }

    @Override
    public void update(){
        float max = PlayerMagicManager.getInstance().getMaxMana();
        float current = PlayerMagicManager.getInstance().getCurrentMana();
        float len = (current/max) * maxLen;
        bar.setSize(new Vector2f(len, thickness));

        mainText.setString("MP: " + Math.round(current) + " / " + Math.round(max));
        regenText.setString("+" + new BigDecimal(PlayerMagicManager.getInstance().getMPRegen()).round(new MathContext(1)));
        regenText.setPosition(new Vector2f(bar.getGlobalBounds().left +bar.getLocalBounds().width - regenText.getLocalBounds().width, bar.getGlobalBounds().top + 3));
    }

    @Override
    public void draw(RenderWindow window){
        super.draw(window);
        window.draw(regenText);
    }

}
