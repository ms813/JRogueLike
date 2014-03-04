package Game;

import Game.UI.InventoryItemInfo;
import Game.UI.InventoryUI;
import Game.UI.UIManager;
import Player.PlayerManagers.PlayerInventoryManager;
import com.sun.java_cup.internal.runtime.lr_parser;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Mouse;
import org.jsfml.window.event.Event;
import Player.Player;
import org.jsfml.window.event.KeyEvent;
import org.jsfml.window.event.MouseButtonEvent;

import javax.swing.*;

/**
 * Created by Matthew on 28/02/14.
 */
public class InputManager {

    private static InputManager instance = null;

    private CurrentFocus currentFocus = CurrentFocus.GAME;
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
                    currentFocus = CurrentFocus.GAME;
                }
                Main.setPaused(!Main.isPaused());
            }

            if (evt.key == Keyboard.Key.I) {
                //display inventory when 'i' is pressed
                UIManager.getInstance().toggleInventory();

                if (InventoryUI.getInstance().isVisible()) {
                    currentFocus = CurrentFocus.INVENTORY;
                } else {

                    currentFocus = CurrentFocus.GAME;
                }
                System.out.println("[InputManager.processEvent()] Current focus = " + currentFocus);


            }
        }

        if (currentFocus == CurrentFocus.GAME) {
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
        } else if (currentFocus == CurrentFocus.INVENTORY) {

            //get inputs when we are looking at the inventory

            if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
                MouseButtonEvent mouseEvt = event.asMouseButtonEvent();

                if (mouseEvt.button == Mouse.Button.LEFT) {
                    Vector2f mousePos = new Vector2f(Mouse.getPosition(window));

                    for (InventoryItemInfo itemInfo : PlayerInventoryManager.getInstance().getCarriedItems()) {
                        Sprite icon = itemInfo.getIcon();

                        if (icon.getGlobalBounds().contains(mousePos)) {
                            System.out.println("[InputManager.processEvent()] " + itemInfo.getItemName() + " was left clicked!");
                            itemInfo.use();
                        }
                    }
                    PlayerInventoryManager.getInstance().tidy();

                }

                if (mouseEvt.button == Mouse.Button.RIGHT) {
                    //if right button pressed get the mouse position
                    Vector2f mousePos = new Vector2f(Mouse.getPosition(window));

                    //for each item in the inventory...
                    for (InventoryItemInfo itemInfo : PlayerInventoryManager.getInstance().getCarriedItems()) {
                        Sprite icon = itemInfo.getIcon();

                        //...check if the mouse pos is over the icon
                        if (icon.getGlobalBounds().contains(mousePos)) {
                            System.out.println("[InputManager.processEvent()] " + itemInfo.getItemName() + " was right clicked!");
                            //TODO display a context menu?
                            itemInfo.displayContextMenu();
                        }
                    }
                }

                if (mouseEvt.button == Mouse.Button.MIDDLE) {
                }
            }
        }

    }

    public void checkForContinuousInput() {


        //input for the main game
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

            if (currentFocus == CurrentFocus.GAME) {

                //look for mouse input for firing projectiles
                if (Mouse.isButtonPressed(Mouse.Button.LEFT)) {
                    //if left mouse held down
                    Vector2f worldPos = window.mapPixelToCoords(Mouse.getPosition(window));
                    player.castCurrentSpell(worldPos);
                }

                if (Mouse.isButtonPressed(Mouse.Button.RIGHT)) {
                    //if right mouse held down
                }
            } else if (currentFocus == CurrentFocus.INVENTORY) {

                //input controls for when the inventory is being looked at
                Vector2f mousePos = new Vector2f(Mouse.getPosition(window));

                for (InventoryItemInfo item : PlayerInventoryManager.getInstance().getCarriedItems()) {
                    Sprite icon = item.getIcon();

                    if (icon.getGlobalBounds().contains(mousePos)) {
                        InventoryUI.getInstance().highlight(item, true);
                    } else {
                        InventoryUI.getInstance().highlight(item, false);
                    }
                }
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
