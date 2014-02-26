package Game.UI;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;

/**
 * Created by Matthew on 26/02/14.
 */
public class UIManager implements UIElement {

    private static UIManager instance = null;

    private InventoryUI inventoryUI = InventoryUI.getInstance();

    protected UIManager() {
    }

    public static UIManager getInstance() {
        if (instance == null) {
            instance = new UIManager();
        }
        return instance;
    }

    public void draw(RenderWindow window) {
        inventoryUI.draw(window);
    }

    public void update() {

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

    public void init(RenderWindow window){
        inventoryUI.init(window);
    }


}
