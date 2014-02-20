import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by Matthew on 18/02/14.
 */
public class Skeleton extends Monster {

    public Skeleton() {

        maxHP = 200f;
        currentHP = maxHP;
        XP = 10f;

        try {
            Texture texture = new Texture();
            texture.loadFromFile(Paths.get("resources" + File.separatorChar + "skeleton.png"));
            sprite = new Sprite(texture);
            sprite.setScale(0.5f, 0.5f);
            //set the origin to the center of the sprite rather than the top left
            sprite.setOrigin(sprite.getLocalBounds().width / 2, sprite.getLocalBounds().height / 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCollision(Actor collider) {
        if (collider instanceof Projectile) {
            Projectile projectile = (Projectile) collider;

            currentHP -= projectile.getDamage();

            System.out.println("Skeleton HP: " + currentHP);

            if(currentHP <= 0){
                onDeath();
            }
        }
    }

    protected void onDeath(){
        System.out.println("Skeleton died!");
        readyForDestruction = true;
        PlayerXPManager.gainXP(XP);
    }
}
