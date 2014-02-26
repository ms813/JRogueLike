package Game.UI;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;

/**
 * Created by Matthew on 26/02/14.
 */
public class UIManager implements UIElement {

    private static UIManager instance = null;

    private UIInventory uiInventory = UIInventory.getInstance();

    protected UIManager() {
    }

    public static UIManager getInstance() {
        if (instance == null) {
            instance = new UIManager();
        }
        return instance;
    }

    public void draw(RenderWindow window) {
        uiInventory.draw(window);
    }

    public void update() {
        uiInventory.update();
    }

    public void setInventoryPosition(float x, float y) {
        uiInventory.setPosition(x, y);
    }

    public void setInventoryPosition(Vector2f pos){
        uiInventory.setPosition(pos);
    }

    public Vector2f getInventorySize(){
      return uiInventory.getSize();
    }

    public void init(RenderWindow window){
        uiInventory.init(window);
    }


}
