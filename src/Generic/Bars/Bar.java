package Generic.Bars;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;

/**
 * Created by Matthew on 25/02/14.
 */
public abstract class Bar {

    protected RectangleShape bar = new RectangleShape();
    protected Color color;

    protected float maxLen;
    protected float thickness;

    public void draw(RenderWindow window){
        window.draw(bar);
    }

    public abstract void update();

}
