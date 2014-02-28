package Game.UI;

import Game.Game;
import Player.PlayerManagers.PlayerInventoryManager;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;


/**
 * Created by Matthew on 26/02/14.
 */
public class InventoryUI implements UIElement {

    private static InventoryUI instance = null;

    private static PlayerInventoryManager inventoryManager = PlayerInventoryManager.getInstance();

    private RectangleShape bg;

    private boolean visible = false;

    protected InventoryUI() {

    }

    public static InventoryUI getInstance() {
        if (instance == null) {
            instance = new InventoryUI();
        }
        return instance;
    }

    public void setPosition(float x, float y) {
        bg.setPosition(x, y);
    }

    public void setPosition(Vector2f pos) {
        bg.setPosition(pos);
    }

    public void draw(RenderWindow window) {
        if (visible) {
            window.draw(bg);

            for (InventoryItemInfo itemInfo : inventoryManager.getCarriedItems()) {
                itemInfo.draw(window);
            }
        }

    }

    public void update() {
        float increment = 0;
        for (InventoryItemInfo itemInfo : inventoryManager.getCarriedItems()) {
            itemInfo.setIconPosition(Vector2f.add(bg.getPosition(), new Vector2f(increment, 0)));
            increment += itemInfo.getIcon().getLocalBounds().width * itemInfo.getIcon().getScale().x;
        }
    }

    public Vector2f getPosition() {
        return bg.getPosition();
    }

    public Vector2f getSize() {
        return bg.getSize();
    }

    public void init() {
        bg = new RectangleShape(new Vector2f(Game.screenW * 0.7f, Game.screenH * 0.7f));
        bg.setOutlineColor(Color.BLUE);
        bg.setOutlineThickness(5f);
        bg.setPosition(Game.screenW * 0.15f, Game.screenH*0.15f);
    }

    public void toggleVisible() {
        visible = !visible;
    }

    public void setVisible(boolean val){
        visible = val;
    }
}
