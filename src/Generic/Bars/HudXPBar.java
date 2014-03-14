package Generic.Bars;

import Game.UI.HUD;
import Generic.Libraries.FontLibrary;
import Generic.VectorArithmetic;
import Player.PlayerManagers.PlayerXPManager;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.TextStyle;
import org.jsfml.system.Vector2f;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by Matthew on 14/03/14.
 */
public class HudXPBar extends HudBar {

    private Text levelText;

    public HudXPBar(Vector2f offset){
        super(new Color(166,166,166), offset);
        levelText = new Text("X", FontLibrary.getFont("arial"), 14);
        levelText.setPosition(Vector2f.add(HUD.getInstance().getPosition(), offset));
        levelText.move(0, -levelText.getLocalBounds().height - 7);
        levelText.setColor(Color.BLACK);
        levelText.setStyle(TextStyle.BOLD);
    }

    @Override
    public void update(){
        float p = PlayerXPManager.getInstance().getProgressToNextLevel();

        bar.setSize(new Vector2f(p*maxLen, thickness));
        mainText.setString(new BigDecimal(p * 100).round(new MathContext(2)) + "% to level " + (PlayerXPManager.getInstance().getXPLevel() + 1));
        levelText.setString("XP Level: " + PlayerXPManager.getInstance().getXPLevel());
    }

    public void draw(RenderWindow window){
        super.draw(window);
        window.draw(levelText);
    }
}
