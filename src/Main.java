import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Clock;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Mouse;
import org.jsfml.window.event.Event;


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

        Clock frameClock = new Clock();

        while (window.isOpen()) {


            window.clear();
            Time deltaTime = frameClock.restart();
            float deltaSeconds = deltaTime.asSeconds();


            //look for WASD input to control player movement
            if(Keyboard.isKeyPressed(Keyboard.Key.W)){
                player.move(0, -deltaSeconds);
            }
            if(Keyboard.isKeyPressed(Keyboard.Key.S)){
                player.move(0, deltaSeconds);
            }
            if(Keyboard.isKeyPressed(Keyboard.Key.A)){
                player.move(-deltaSeconds, 0);
            }
            if(Keyboard.isKeyPressed(Keyboard.Key.D)){
                player.move(deltaSeconds, 0);
            }


            //look for mouse input for firing projectiles
            if(Mouse.isButtonPressed(Mouse.Button.LEFT)){
                Vector2f worldPos = window.mapPixelToCoords(Mouse.getPosition(window));
                game.getCurrentScene().addActor(new MagicDart(player, worldPos));
            }

            if(Mouse.isButtonPressed(Mouse.Button.RIGHT)){
                Vector2f worldPos = window.mapPixelToCoords(Mouse.getPosition(window));
                game.getCurrentScene().addActor(new IceBolt(player, worldPos));
            }

            //event loop
            for (Event event : window.pollEvents()) {

                //executed when the window is closed
                if (event.type == Event.Type.CLOSED) {
                    window.close();
                }
            }

            game.getCurrentScene().updateProjectiles();
            game.setViewCenter(new Vector2i(player.getDrawable().getPosition()));

            game.drawScene(0);
            window.display();
        }

    }
}
