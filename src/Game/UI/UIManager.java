package Game.UI;

import Game.UI.HUD.HUD;
import Game.UI.HUD.Hotbar;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;

/**
 * Created by Matthew on 26/02/14.
 */
public class UIManager implements UIElement {

    private static UIManager instance = null;

    private InventoryUI inventoryUI;
    private Hotbar hotbar;
    private HUD hud;

    protected UIManager() {
    }

    public static UIManager getInstance() {
        if (instance == null) {
            instance = new UIManager();
        }
        return instance;
    }

    public void draw(RenderWindow window) {
        hud.draw(window);
        inventoryUI.draw(window);
        hotbar.draw(window);
    }

    public void update() {
        hud.update();
    }

    public void setInventoryPosition(float x, float y) {
        inventoryUI.setPosition(x, y);
    }

    public void setInventoryPosition(Vector2f pos){
        inventoryUI.setPosition(pos);
    }

    public Vector2f getInventorySize(){
      return inventoryUI.getSize();
    }

    public void init(){
        inventoryUI = InventoryUI.getInstance();
        hotbar = Hotbar.getInstance();
        hud = HUD.getInstance();

        inventoryUI.init();
        hotbar.init();
        hud.init();
    }

    public void toggleInventory(){
        inventoryUI.toggleVisible();
           }

    public void hideAll(){
        inventoryUI.setVisible(false);
    }
}
