package Game.UI;

import Items.Item;
import Player.PlayerManagers.PlayerInventoryManager;
import Game.Game;
import com.sun.java.swing.plaf.windows.resources.windows;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;


/**
 * Created by Matthew on 26/02/14.
 */
public class UIInventory implements UIElement {

    private static UIInventory instance = null;

    private static PlayerInventoryManager inventoryManager = PlayerInventoryManager.getInstance();

    private RectangleShape outline;
    private Sprite icons;

    protected UIInventory() {

    }

    public static UIInventory getInstance() {
        if (instance == null) {
            instance = new UIInventory();
        }
        return instance;
    }

    public void setPosition(float x, float y) {
        outline.setPosition(x, y);
    }

    public void setPosition(Vector2f pos) {
        outline.setPosition(pos);
    }

    public void draw(RenderWindow window) {
        window.draw(outline);
        if(icons != null){
        window.draw(icons);
        }
    }

    public void update() {
        for(InventoryItemInfo itemInfo : inventoryManager.getCarriedItems()){
            icons = itemInfo.getIcon();
            icons.setPosition(outline.getPosition());
        }
    }

    public Vector2f getPosition() {
        return outline.getPosition();
    }

    public Vector2f getSize() {
        return outline.getSize();
    }

    public void init(RenderWindow window) {
        outline = new RectangleShape(Vector2f.mul(new Vector2f(window.getSize()), 0.1f));
        outline.setPosition(window.getSize().x - outline.getSize().x, window.getSize().y - outline.getSize().y);
    }
}
