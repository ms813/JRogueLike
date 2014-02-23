import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;

/**
 * Created by Matthew on 22/02/14.
 */
public class MonsterHPBar {
    RectangleShape bar = new RectangleShape();
    Color color = Color.RED;
    Monster parent;
    float maxLen;
    float thickness = 5f;

    public MonsterHPBar(Monster _parent){
        parent= _parent;
        maxLen = parent.sprite.getLocalBounds().width/2;


        bar.setSize(new Vector2f(maxLen, thickness));
        bar.setOrigin(new Vector2f(maxLen/2, 0));
        bar.setFillColor(color);
    }

    public void update(){
        float len = (parent.currentHP/parent.maxHP) * maxLen;
        bar.setSize(new Vector2f(len, thickness));


        Vector2f pos = Vector2f.add(parent.sprite.getPosition(), new Vector2f(0, parent.sprite.getLocalBounds().height/4));
        bar.setPosition(pos);
    }

    public void draw(RenderWindow window){
        window.draw(bar);
    }

}
