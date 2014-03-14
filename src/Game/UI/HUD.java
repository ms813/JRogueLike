package Game.UI;

import Generic.HudHPBar;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;

/**
 * Created by Matthew on 14/03/14.
 */
public class HUD {
    private static HUD instance = null;
    protected HUD(){}
    public static HUD getInstance(){
        if(instance == null){
            instance = new HUD();
        }
        return instance;
    }

    private Vector2f hudPos = new Vector2f(20,20);

    private RectangleShape bg;
    private Vector2f hpBarPos = new Vector2f(10,50);
    private HudHPBar hpBar;

    private Vector2f mpBarPos = new Vector2f(10, 100);


    public void init(){
        bg = new RectangleShape(new Vector2f(250,150));
        bg.setPosition(hudPos);
        hpBar = new HudHPBar(hpBarPos);
    }

    public void draw(RenderWindow window){
        window.draw(bg);
        hpBar.draw(window);
    }

    public void update(){
        hpBar.update();
    }

    public Vector2f getPosition(){
        return hudPos;
    }

    public Vector2f getSize(){
        return bg.getSize();
    }

}
