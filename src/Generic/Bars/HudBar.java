package Generic.Bars;

import Generic.Libraries.FontLibrary;
import Game.UI.HUD;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

/**
 * Created by Matthew on 14/03/14.
 */
public abstract class HudBar extends Bar {

    protected Text mainText = new Text("", FontLibrary.getFont("arial"), 14);
    protected Text regenText = new Text("", FontLibrary.getFont("arial"), 10);
    protected RectangleShape bg = new RectangleShape();

    public HudBar(Color color, Vector2f offset){
        this.color = color;
        maxLen = HUD.getInstance().getSize().x - 20;
        thickness = 20f;

        bar.setSize(new Vector2f(maxLen, thickness));
        bar.setFillColor(color);
        bar.setPosition(Vector2f.add(HUD.getInstance().getPosition(), offset));

        bg.setSize(new Vector2f(maxLen, thickness));
        bg.setFillColor(new Color(color, 100));
        bg.setOutlineColor(Color.BLACK);
        bg.setOutlineThickness(2.0f);
        bg.setPosition(bar.getPosition());

        mainText.setPosition(new Vector2f(bar.getGlobalBounds().left, bar.getGlobalBounds().top));
        mainText.setColor(Color.BLACK);
        regenText.setColor(Color.BLACK);
    }

    @Override
    public void draw(RenderWindow window){
        super.draw(window);
        window.draw(bg);
        window.draw(mainText);
        window.draw(regenText);
    }

    public void update(){
        regenText.setPosition(new Vector2f(bar.getGlobalBounds().left +bar.getLocalBounds().width - regenText.getLocalBounds().width, bar.getGlobalBounds().top + 3));
    }
}
