package Game;

import Game.UI.UIManager;
import Player.PlayerManagers.PlayerInventoryManager;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Mouse;
import org.jsfml.window.event.Event;
import Player.Player;
import org.jsfml.window.event.KeyEvent;
import org.jsfml.window.event.MouseButtonEvent;

/**
 * Created by Matthew on 28/02/14.
 */
public class InputManager {

    private static InputManager instance = null;

    private String currentFocus = "game";
    private Player player;
    private RenderWindow window;

    protected InputManager() {
    }

    public static InputManager getInstance() {
        if (instance == null) {
            instance = new InputManager();
        }
        return instance;
    }

    public void processEvent(Event event) {

        if (event.type == Event.Type.CLOSED) {
            window.close();
        }

        if (event.type == Event.Type.KEY_PRESSED) {
            KeyEvent evt = event.asKeyEvent();

            if (evt.key == Keyboard.Key.SPACE) {
                if (Main.isPaused()) {
                    //remove all inventory windows etc
                    UIManager.getInstance().hideAll();
                }
                Main.setPaused(!Main.isPaused());
            }

            if (evt.key == Keyboard.Key.I) {
                Main.setPaused(!Main.isPaused());
                //display inventory
                UIManager.getInstance().toggleInventory();
            }
        }

        if (!Main.isPaused()) {

            if (event.type == Event.Type.MOUSE_WHEEL_MOVED) {
                player.changeCurrentSpell(event.asMouseWheelEvent().delta);
            }

            if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
                MouseButtonEvent mouseEvt = event.asMouseButtonEvent();

                if (mouseEvt.button == Mouse.Button.RIGHT) {
                    //if right button pressed
                    Vector2f worldPos = window.mapPixelToCoords(Mouse.getPosition(window));
                    //player.meleeAttack(worldPos);
                }

                if (mouseEvt.button == Mouse.Button.MIDDLE) {
                    System.out.println(PlayerInventoryManager.getInstance().getCarriedItems());
                }
            }
        }
    }

    public void checkKeyboard() {

        if (!Main.isPaused()) {

            float deltaSeconds = Main.getDeltaSeconds();
            //look for WASD input to control player movement
            if (Keyboard.isKeyPressed(Keyboard.Key.W)) {
                player.move(0, -deltaSeconds);
            }
            if (Keyboard.isKeyPressed(Keyboard.Key.S)) {
                player.move(0, deltaSeconds);
            }
            if (Keyboard.isKeyPressed(Keyboard.Key.A)) {
                player.move(-deltaSeconds, 0);
            }
            if (Keyboard.isKeyPressed(Keyboard.Key.D)) {
                player.move(deltaSeconds, 0);
            }

            //look for mouse input for firing projectiles
            if (Mouse.isButtonPressed(Mouse.Button.LEFT)) {
                //if left mouse held down
                Vector2f worldPos = window.mapPixelToCoords(Mouse.getPosition(window));
                player.castCurrentSpell(worldPos);
            }

            if (Mouse.isButtonPressed(Mouse.Button.RIGHT)) {
                //if right mouse held down
            }
        }
    }

    public void setPlayer(Player _player) {
        player = _player;
    }

    public void setWindow(RenderWindow _window) {
        window = _window;
    }


}
