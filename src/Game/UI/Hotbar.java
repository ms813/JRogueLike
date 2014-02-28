package Game.UI;

import Game.Game;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthew on 27/02/14.
 */
public class Hotbar implements UIElement {

    private RectangleShape bg;

    private static Hotbar instance = null;

    private static List<InventoryItemInfo> hotbarEntries = new ArrayList<InventoryItemInfo>();

    protected Hotbar(){}

    public static Hotbar getInstance(){
        if(instance == null){
            instance = new Hotbar();
        }
        return instance;
    }


    public void draw(RenderWindow window){
        window.draw(bg);
    }

    public void update(){
    }

    public void init(){
        bg = new RectangleShape(new Vector2f(Game.screenW*0.9f, Game.screenH * 0.1f));
        bg.setOutlineColor(Color.RED);
        bg.setOutlineThickness(2.5f);
        bg.setPosition(2.5f, Game.screenH - bg.getSize().y-2.5f);
    }

    private InventoryItemInfo getItemAt(int i){
        return hotbarEntries.get(i);
    }
}
