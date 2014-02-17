import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Mouse;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;
import org.jsfml.window.event.MouseButtonEvent;


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
                        InputBooleans.left = true;
                    }
                    if(keyEvent.key == Keyboard.Key.D){
                        InputBooleans.right = true;
                    }
                    if(keyEvent.key == Keyboard.Key.W){
                        InputBooleans.up = true;
                    }
                    if(keyEvent.key == Keyboard.Key.S){
                        InputBooleans.down = true;
                    }
                }

                if (event.type == Event.Type.KEY_RELEASED) {
                    KeyEvent keyEvent = event.asKeyEvent();

                    if(keyEvent.key == Keyboard.Key.A){
                        InputBooleans.left = false;
                    }
                    if(keyEvent.key == Keyboard.Key.D){
                        InputBooleans.right = false;
                    }
                    if(keyEvent.key == Keyboard.Key.W){
                        InputBooleans.up = false;
                    }
                    if(keyEvent.key == Keyboard.Key.S){
                        InputBooleans.down = false;
                    }
                }

                if(event.type == Event.Type.MOUSE_BUTTON_PRESSED){
                    MouseButtonEvent mbEvent = event.asMouseButtonEvent();

                    Vector2f mousePos = window.mapPixelToCoords(mbEvent.position);

                    if(mbEvent.button == Mouse.Button.LEFT){
                        InputBooleans.lmb = true;
                        game.getCurrentScene().addActor(new MagicDart(player, mousePos));
                        //System.out.println("MousePos: " + mousePos);
                    }
                    if(mbEvent.button == Mouse.Button.RIGHT){
                        InputBooleans.rmb = true;
                        game.getCurrentScene().addActor(new IceBolt(player, mousePos));
                    }

                    if(mbEvent.button == Mouse.Button.MIDDLE){
                        InputBooleans.mmb = true;
                    }
                }

                if(event.type == Event.Type.MOUSE_BUTTON_RELEASED){
                    MouseButtonEvent mbEvent = event.asMouseButtonEvent();

                    if(mbEvent.button == Mouse.Button.LEFT){
                        InputBooleans.lmb = true;
                    }
                    if(mbEvent.button == Mouse.Button.RIGHT){
                        InputBooleans.rmb = true;
                    }

                    if(mbEvent.button == Mouse.Button.MIDDLE){
                        InputBooleans.mmb = true;
                    }
                }
            }

            game.getCurrentScene().updateProjectiles();
            player.move();
            game.setViewCenter(new Vector2i(player.getDrawable().getPosition()));

            game.drawScene(0);
            window.display();
        }

    }
}
