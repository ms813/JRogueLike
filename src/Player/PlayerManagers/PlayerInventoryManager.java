package Player.PlayerManagers;

import Game.UI.InventoryItemInfo;
import Game.UI.InventoryUI;
import Items.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthew on 25/02/14.
 */
public class PlayerInventoryManager {

    private List<InventoryItemInfo> carriedItems = new ArrayList<InventoryItemInfo>();

    private InventoryUI inventoryUI = InventoryUI.getInstance();

    private static PlayerInventoryManager instance = null;

    protected PlayerInventoryManager() {
    }

    public static PlayerInventoryManager getInstance() {
        if (instance == null) {
            instance = new PlayerInventoryManager();
        }
        return instance;
    }

    public void addItem(Item item, int quantity) {
        boolean alreadyCarrying = false;

        for (InventoryItemInfo carriedItem : carriedItems) {
            if (item.isStackable()) {
                if (carriedItem.getItem().getClass() == item.getClass()) {
                    carriedItem.incrementQuantity(quantity);
                    alreadyCarrying = true;
                    System.out.println("[PlayerInventoryManager.addItem()] Stacked " + carriedItem.getQuantity() + " " + item.getName());
                }
            }
        }

        if (!alreadyCarrying) {
            InventoryItemInfo itemInfo = new InventoryItemInfo(item, quantity);
            carriedItems.add(itemInfo);
            System.out.println("[PlayerInventoryManager.addItem()] Added new " + item.getName() +" to inventory");
        }

        inventoryUI.update();
    }

    public List<InventoryItemInfo> getCarriedItems() {
        return carriedItems;
    }

}
