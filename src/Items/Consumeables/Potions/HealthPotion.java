package Items.Consumeables.Potions;

import Game.UI.TextureLibrary;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by Matthew on 25/02/14.
 */
public class HealthPotion extends Potion {

    public HealthPotion(){

            sprite = new Sprite(TextureLibrary.getTexture("healthPotion"));
            sprite.setScale(0.4f, 0.4f);

            icon = new Sprite(TextureLibrary.getTexture("healthPotion"));
            icon.setScale(0.2f, 0.2f);

            coolDown = 20f;
            name = "Health Potion";
    }

    public void quaff(){
        System.out.println("Quaffed hp potion");
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
}
