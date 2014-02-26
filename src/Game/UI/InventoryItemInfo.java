package Game.UI;

import Items.Consumeable;
import Items.Item;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;


/**
 * Created by Matthew on 26/02/14.
 */
public class InventoryItemInfo {

    private Item item;
    private float coolDown;
    private float coolDownRemaining;
    boolean stackable;
    private int quantity;
    private Text quantityText;


    public InventoryItemInfo(Item _item, int _quantity) {
        item = _item;
        if (item instanceof Consumeable) {
            coolDown = ((Consumeable) item).getCoolDown();
        } else {
            coolDown = -1f;
        }
        coolDownRemaining = 0;
        stackable = item.isStackable();
        quantity = _quantity;


        quantityText = new Text("", FontLibrary.getFont("arial"), 14);
        quantityText.setColor(Color.BLACK);
        quantityText.setStyle(TextStyle.BOLD);
        if (quantity > 1) {
            quantityText.setString(Integer.toString(quantity));
        }
    }

    public Item getItem() {
        return item;
    }

    public String getItemName() {
        return item.getName();
    }

    public void reduceCoolDownRemaining(float time) {
        coolDownRemaining -= time;
    }

    public float getCoolDown() {
        return coolDown;
    }

    public void setCoolDown(float coolDown) {
        this.coolDown = coolDown;
    }

    public float getCoolDownRemaining() {
        return coolDownRemaining;
    }

    public void setCoolDownRemaining(float coolDownRemaining) {
        this.coolDownRemaining = coolDownRemaining;
    }

    public Sprite getIcon() {
        return item.getIcon();
    }

    public int getQuantity() {
        return quantity;
    }

    public void incrementQuantity() {
        quantity++;
        quantityText.setString(Integer.toString(quantity));
    }

    public void incrementQuantity(int amount) {
        quantity += amount;
        quantityText.setString(Integer.toString(quantity));
    }

    public void decrementQuantity() {
        quantity--;
        quantityText.setString(Integer.toString(quantity));
    }

    public void decrementQuantity(int amount) {
        quantity -= amount;
        quantityText.setString(Integer.toString(quantity));
    }

    public Text getQuantityText() {
        return quantityText;
    }

    public void setIconPosition(Vector2f pos) {
        item.getIcon().setPosition(pos);
        quantityText.setPosition(pos);
    }

    public void draw(RenderWindow window) {
        window.draw(item.getIcon());
        window.draw(quantityText);
    }

}
