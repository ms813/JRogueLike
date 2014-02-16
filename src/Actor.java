import org.jsfml.graphics.Drawable;
import org.jsfml.system.Vector2f;

/**
 * Created with IntelliJ IDEA.
 * User: Matthew
 * Date: 15/02/14
 * Time: 12:56
 * To change this template use File | Settings | File Templates.
 */
public interface Actor{

    Drawable getDrawable();

    Vector2f getCurrentPosition();

    String getName();

}
