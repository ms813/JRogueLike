import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Clock;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Mouse;
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

    public static void main(String[] args) {

        RenderWindow window = new RenderWindow();
        window.create(new VideoMode(Game.screenW, Game.screenH), "JRogueLike");
        Game game = new Game(window);
        Player player = game.getPlayer();

        window.setFramerateLimit(60);
        window.setVerticalSyncEnabled(true);

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
                    player.castCurrentSpell(worldPos);
            }

            if(Mouse.isButtonPressed(Mouse.Button.RIGHT)){
                Vector2f worldPos = window.mapPixelToCoords(Mouse.getPosition(window));
                System.out.println("Right mouse clicked: " + worldPos);
            }

            //event loop
            for (Event event : window.pollEvents()) {

                //executed when the window is closed
                if (event.type == Event.Type.CLOSED) {
                    window.close();
                }

                if(event.type == Event.Type.MOUSE_WHEEL_MOVED){
                    player.changeCurrentSpell(event.asMouseWheelEvent().delta);
                }
            }

            Game.getCurrentScene().checkCollisions();

            game.update();

            game.setViewCenter(new Vector2i(player.getDrawable().getPosition()));
            PlayerMagicManager.getInstance().reduceCoolDownsRemaining(deltaSeconds);

            game.draw(window);
            window.display();
        }

    }
}
