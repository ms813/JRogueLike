import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by Matthew on 18/02/14.
 */
public class Skeleton implements Enemy {

    private Sprite sprite;

    private float moveSpeed;

    public Skeleton(){

        try{
            Texture texture = new Texture();
            texture.loadFromFile(Paths.get("resources" + File.separatorChar + "skeleton.png"));
            sprite = new Sprite(texture);
            //set the origin to the center of the sprite rather than the top left
            sprite.setOrigin(sprite.getLocalBounds().width / 2, sprite.getLocalBounds().height /2);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void setPosition(Vector2f position){
        sprite.setPosition(position);
    }

    public void setPosition(float x, float y){
        sprite.setPosition(x,y);
    }

    public Sprite getDrawable(){
        return sprite;
    }

    public Vector2f getCurrentPosition(){
        return sprite.getPosition();
    }

    public void onCollision(Actor collider){
        if(collider instanceof Projectile){
            Projectile projectile = (Projectile) collider;
            System.out.println("Skeleton hit by: " + projectile);
        }
    }
}
