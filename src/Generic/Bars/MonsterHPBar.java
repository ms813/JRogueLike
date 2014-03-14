package Generic.Bars;

import Monsters.Monster;
import org.jsfml.graphics.Color;
import org.jsfml.system.Vector2f;

/**
 * Created by Matthew on 22/02/14.
 */
public class MonsterHPBar extends Bar {

    Monster parent;

    public MonsterHPBar(Monster _parent){
        parent= _parent;
        maxLen = parent.getDrawable().getLocalBounds().width * parent.getDrawable().getScale().x;
        color = Color.RED;
        thickness = 5f;

        bar.setSize(new Vector2f(maxLen, thickness));
        bar.setOrigin(new Vector2f(maxLen/2, 0));
        bar.setFillColor(color);
    }

    public void update(){
        float len = (parent.getCurrentHP()/parent.getMaxHP()) * maxLen;
        bar.setSize(new Vector2f(len, thickness));
        Vector2f pos = Vector2f.add(parent.getDrawable().getPosition(), new Vector2f(0, parent.getDrawable().getLocalBounds().height/2 * parent.getDrawable().getScale().y));
        bar.setPosition(pos);
    }
}
