import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Keyboard;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;


/**
 * Created with IntelliJ IDEA.
 * User: Matthew
 * Date: 15/02/14
 * Time: 12:29
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) {

        Game game = new Game("JRogueLike");
        Player player = game.getPlayer();

        RenderWindow window = game.getWindow();

        while (window.isOpen()) {
            window.clear();

            //event loop
            for (Event event : window.pollEvents()) {

                //executed when the window is closed
                if (event.type == Event.Type.CLOSED) {
                    window.close();
                }
                //look for WASD input for character movement
                if (event.type == Event.Type.KEY_PRESSED) {

                    KeyEvent keyEvent = event.asKeyEvent();

                    if(keyEvent.key == Keyboard.Key.A){
                        MoveDirections.left = true;
                    }
                    if(keyEvent.key == Keyboard.Key.D){
                        MoveDirections.right = true;
                    }
                    if(keyEvent.key == Keyboard.Key.W){
                        MoveDirections.up = true;
                    }
                    if(keyEvent.key == Keyboard.Key.S){
                        MoveDirections.down = true;
                    }
                }

                if (event.type == Event.Type.KEY_RELEASED) {

                    KeyEvent keyEvent = event.asKeyEvent();

                    if(keyEvent.key == Keyboard.Key.A){
                        MoveDirections.left = false;
                    }
                    if(keyEvent.key == Keyboard.Key.D){
                        MoveDirections.right = false;
                    }
                    if(keyEvent.key == Keyboard.Key.W){
                        MoveDirections.up = false;
                    }
                    if(keyEvent.key == Keyboard.Key.S){
                        MoveDirections.down = false;
                    }
                }
            }

            player.move();
            game.setViewCenter(new Vector2i(player.getDrawable().getPosition()));

            game.drawScene(0);
            window.display();
        }

    }
}
