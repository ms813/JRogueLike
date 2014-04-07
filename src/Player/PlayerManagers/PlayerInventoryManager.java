package Player.PlayerManagers;

import Game.UI.InventoryItemInfo;
import Game.UI.InventoryUI;
import Items.Item;
import Player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthew on 25/02/14.
 */
public class PlayerInventoryManager implements PlayerManager {

    private List<InventoryItemInfo> carriedItems = new ArrayList<InventoryItemInfo>();

    private InventoryUI inventoryUI = InventoryUI.getInstance();

    private static PlayerInventoryManager instance = null;

    private Player player;

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

    public void removeItem(InventoryItemInfo itemInfo, int quantity){

        //if item already sceneExists in the list, decrement its quantity
        if(itemInfo.getItem().isStackable()){
            carriedItems.get(carriedItems.indexOf(itemInfo)).decrementQuantity(quantity);
            if(carriedItems.get(carriedItems.indexOf(itemInfo)).getQuantity() <= 0){
                //if no items in the stack are left, remove it completely
                itemInfo.setReadyForRemoval();
            }
        } else{
            itemInfo.setReadyForRemoval();
        }
    }

    public void tidy(){
        List<InventoryItemInfo> tempItems = new ArrayList<InventoryItemInfo>(carriedItems);
        for(InventoryItemInfo itemInfo : tempItems){
            if(itemInfo.isReadyForRemoval()){
                carriedItems.remove(itemInfo);
            }
        }
    }

    public List<InventoryItemInfo> getCarriedItems() {
        return carriedItems;
    }

    public void setPlayer(Player _player){
        player = _player;
    }

    public void update(){

    }

    public void levelUp(){};
}
