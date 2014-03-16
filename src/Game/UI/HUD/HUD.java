package Game.UI.HUD;

import Generic.Bars.HudHPBar;
import Generic.Bars.HudMPBar;
import Generic.Bars.HudXPBar;
import MagicSpells.SpellInfo;
import com.sun.jndi.url.corbaname.corbanameURLContextFactory;
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

    private Vector2f mpBarPos = new Vector2f(10, 80);
    private HudMPBar mpBar;

    private Vector2f xpBarPos = new Vector2f(10, 120);
    private HudXPBar xpBar;

    private Vector2f currentSpellBoxPos = new Vector2f(20, 190);
    private HudSpellBox currentSpellBox = new HudSpellBox(currentSpellBoxPos);

    public void init(){
        bg = new RectangleShape(new Vector2f(250,150));
        bg.setPosition(hudPos);
        hpBar = new HudHPBar(hpBarPos);
        xpBar = new HudXPBar(xpBarPos);
        mpBar = new HudMPBar(mpBarPos);
    }

    public void draw(RenderWindow window){
        window.draw(bg);
        hpBar.draw(window);
        xpBar.draw(window);
        mpBar.draw(window);
        currentSpellBox.draw(window);
    }

    public void update(){
        hpBar.update();
        xpBar.update();
        mpBar.update();
        currentSpellBox.update();
    }

    public Vector2f getPosition(){
        return hudPos;
    }

    public Vector2f getSize(){
        return bg.getSize();
    }

    public void setCurrentSpell(SpellInfo currentSpell){
        currentSpellBox.setSpell(currentSpell);
    }
}
