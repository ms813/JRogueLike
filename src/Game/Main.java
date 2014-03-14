package Game;

import Player.Player;
import Player.PlayerManagers.PlayerMagicManager;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Clock;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2i;
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

    private static Clock frameClock = new Clock();
    private static Time deltaTime;
    private static float deltaSeconds;

    private static boolean paused = false;

    public static void main(String[] args) {

        RenderWindow window = new RenderWindow();
        window.create(new VideoMode(Game.screenW, Game.screenH), "JRogueLike");
        Game game = new Game(window);
        Player player = game.getPlayer();

        InputManager inputManager = InputManager.getInstance();
        inputManager.setPlayer(player);
        inputManager.setWindow(window);

        window.setFramerateLimit(60);
        window.setVerticalSyncEnabled(true);


        while (window.isOpen()) {

            window.clear();
            deltaTime = frameClock.restart();
            deltaSeconds = deltaTime.asSeconds();

            //event loop
            for (Event event : window.pollEvents()) {
                inputManager.processEvent(event);
            }

            inputManager.checkForContinuousInput();

            if (!paused) {

                Game.getCurrentScene().checkCollisions();

                game.update();
            }

            game.draw(window);
            window.display();
        }
    }

    public static float getDeltaSeconds() {
        return deltaSeconds;
    }

    public static void setPaused(boolean _paused) {
        paused = _paused;
        if (paused) {
            System.out.println("[Main.setPaused()] Game paused");
        } else {
            System.out.println("[Main.setPaused()] Game unpaused");
        }
    }

    public static boolean isPaused() {
        return paused;
    }
}
