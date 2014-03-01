package Items.Consumeables.Potions;

import Game.UI.TextureLibrary;
import Player.PlayerManagers.PlayerMovementManager;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by Matthew on 26/02/14.
 */
public class SpeedPotion extends Potion {

    public SpeedPotion(){

            sprite = new Sprite(TextureLibrary.getTexture("healthPotion"));
            sprite.setScale(0.4f, 0.4f);
            sprite.setColor(Color.CYAN);

            icon = new Sprite(TextureLibrary.getTexture("healthPotion"));
            icon.setScale(0.4f, 0.4f);
            icon.setColor(Color.CYAN);

            coolDown = 20f;
            name = "Speed Potion";
    }

    public void quaff(){
        System.out.println("Quaffed speed potion");
    }

    public Sprite getDrawable(){
        return sprite;
    }

    public Vector2f getCurrentPosition(){
        return sprite.getPosition();
    }

    public float getCoolDown(){
        return coolDown;
    }

    public void use(){
        PlayerMovementManager.getInstance().buffMoveSpeed(5f, 10);
    }

}
