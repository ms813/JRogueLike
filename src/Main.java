import org.jsfml.graphics.RenderWindow;
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

        Game game = new Game("JRogueLike");

        RenderWindow _window = game.getWindow();

        while(_window.isOpen()){
            _window.clear();

            game.drawScene(0);
            _window.display();

            for(Event event : _window.pollEvents()){
                if(event.type == Event.Type.CLOSED){
                    _window.close();
                }
            }
        }

    }
}
