package Game.UI;

import Items.Consumeable;
import Items.Item;
import org.jsfml.graphics.Sprite;

/**
 * Created by Matthew on 26/02/14.
 */
public class InventoryItemInfo {

    private Item item;
    private float coolDown;
    private float coolDownRemaining;
    boolean stackable;
    private int quantity;

    public InventoryItemInfo(Item _item,int _quantity){
        item = _item;
        if(item instanceof Consumeable){
            coolDown = ((Consumeable)item).getCoolDown();
        } else{
            coolDown = -1f;
        }
        coolDownRemaining = 0;
        stackable = item.isStackable();
        quantity = _quantity;
    }

    public Item getItem(){
        return item;
    }

    public String getItemName(){
        return item.getName();
    }

    public void reduceCoolDownRemaining(float time){
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

    public Sprite getIcon(){
        return item.getIcon();
    }

    public int getQuantity(){
        return quantity;
    }

    public void incrementQuantity(){
        quantity++;
    }

    public void incrementQuantity(int amount){
        quantity += amount;
    }

    public void decrementQuantity(){
        quantity++;
    }

    public void decrementQuantity(int amount){
        quantity += amount;
    }



}
