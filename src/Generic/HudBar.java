package Generic;

import Game.UI.FontLibrary;
import Game.UI.HUD;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

/**
 * Created by Matthew on 14/03/14.
 */
public abstract class HudBar extends Bar {

    protected Text text = new Text("", FontLibrary.getFont("arial"), 14);
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


        text.setPosition(new Vector2f(bar.getGlobalBounds().left, bar.getGlobalBounds().top));
        text.setColor(Color.BLACK);
        text.setStyle(TextStyle.BOLD);
    }

    @Override
    public void draw(RenderWindow window){
        super.draw(window);
        window.draw(bg);
        window.draw(text);
    }

    public abstract void update();
}
