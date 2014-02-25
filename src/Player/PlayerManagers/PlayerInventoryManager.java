package Player.PlayerManagers;

import Items.Item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthew on 25/02/14.
 */
public class PlayerInventoryManager {

    private List<Item> carriedItems = new ArrayList<Item>();

    private static PlayerInventoryManager instance = null;

    protected PlayerInventoryManager(){}

    public static PlayerInventoryManager getInstance(){
        if(instance == null){
            instance = new PlayerInventoryManager();
        }
        return instance;
    }

    public void addItem(Item item){
        carriedItems.add(item);
    }

    public List<Item> getCarriedItems(){
        return carriedItems;
    }

}
