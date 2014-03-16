package Game.UI.HUD;

import MagicSpells.SpellInfo;
import Player.PlayerManagers.PlayerMagicManager;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import sun.security.krb5.internal.crypto.crc32;

/**
 * Created by Matthew on 16/03/14.
 */
public class HudSpellBox {

    private RectangleShape bg = new RectangleShape(new Vector2f(72,72));
    private RectangleShape reloadBar = new RectangleShape();
    private SpellInfo spell;
    private Sprite icon;

    public HudSpellBox(Vector2f pos){
        bg.setPosition(pos);
        reloadBar.setSize(bg.getSize());
        reloadBar.setPosition(pos);
        reloadBar.setFillColor(new Color(Color.GREEN, 122));
        spell = PlayerMagicManager.getInstance().getCurrentSpellInfo();
        updateIcon();
    }

    public void update(){
        float max = spell.getCastsPerReload();
        float current = spell.getCastsRemaining();
        Vector2f size = new Vector2f(bg.getSize().x, (current/max) * bg.getSize().y);
        reloadBar.setSize(size);
        reloadBar.setPosition(bg.getPosition().x, bg.getPosition().y + bg.getSize().y - reloadBar.getSize().y);
    }

    public void draw(RenderWindow window){
        window.draw(bg);
        window.draw(reloadBar);
        window.draw(icon);
    }
      
    public void setSpell(SpellInfo spell){
        this.spell = spell;
        updateIcon();
    }

    public void updateIcon(){
        icon = spell.getIcon();
        //icon.setScale(0.5f, 0.5f);
        icon.setOrigin(icon.getLocalBounds().width / 2, icon.getLocalBounds().height / 2);
        icon.setPosition(bg.getGlobalBounds().left + bg.getLocalBounds().width/2, bg.getGlobalBounds().top + bg.getLocalBounds().height/2);
    }
}
