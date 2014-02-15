import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.Color;

/**
 * Created with IntelliJ IDEA.
 * User: Matthew
 * Date: 15/02/14
 * Time: 12:56
 * To change this template use File | Settings | File Templates.
 */
public class Actor extends CircleShape{

    private String name;

    public Actor(String _name, int radius, Color color){
        super(radius);
        setFillColor(color);
        name = _name;
    }


}
