package Generic;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;

/**
 * Created by Matthew on 25/02/14.
 */
public class HPBar {

    RectangleShape bar = new RectangleShape();
    Color color = Color.RED;

    float maxLen;
    float thickness = 5f;

    public void draw(RenderWindow window){
        window.draw(bar);
    }

}
