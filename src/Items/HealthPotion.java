package Items;

import Game.Game;
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
        try{
            Texture texture = new Texture();
            texture.loadFromFile(Paths.get("resources" + File.separatorChar + "healthPotion.png"));
            sprite = new Sprite(texture);
            sprite.setScale(0.4f, 0.4f);

        }   catch(IOException e){
            e.printStackTrace();
        }
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
}
