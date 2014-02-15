import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;


/**
 * Created with IntelliJ IDEA.
 * User: Matthew
 * Date: 15/02/14
 * Time: 12:29
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main (String[] args){
        RenderWindow window = new RenderWindow();
        window.create(new VideoMode(1080, 720), "Hello JSMFL!");

        window.setFramerateLimit(60);

        CircleShape circle = new CircleShape(100);
        circle.setFillColor(Color.BLUE);

        while(window.isOpen()){
            window.clear();

            window.draw(circle);
            window.display();

            for(Event event : window.pollEvents()){
                if(event.type == Event.Type.CLOSED){
                    window.close();
                }
            }
        }

    }
}
