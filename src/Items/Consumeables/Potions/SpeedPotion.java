package Items.Consumeables.Potions;

import Generic.Libraries.TextureLibrary;
import Player.PlayerManagers.PlayerMovementManager;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

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
        System.out.println("Quaffed moveSpeed potion");
    }

    public Sprite getDrawable(){
        return sprite;
    }

    public Vector2f getPosition(){
        return sprite.getPosition();
    }

    public float getCoolDown(){
        return coolDown;
    }

    public void use(){
        PlayerMovementManager.getInstance().buffMoveSpeed(5f, 10000);
    }

}
