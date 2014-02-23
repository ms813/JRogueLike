import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by Matthew on 18/02/14.
 */
public class Skeleton extends Monster {

    public Skeleton() {

        maxHP = 20f;
        currentHP = maxHP;
        XP = 10f;
        moveSpeed = 2f;

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

        hpBar = buildHPBar();
    }

    int counter = 0;

    public void update() {
        super.update();

        if (counter % 180 == 0) {
            IceBolt iceBolt = new IceBolt(this);
            iceBolt.castSpell(PlayerXPManager.getInstance().getPlayer().getCurrentPosition(), 1);
        }

        counter++;
        Vector2f dir = VectorArithmetic.normalize(Vector2f.sub(PlayerXPManager.getInstance().getPlayer().getCurrentPosition(), sprite.getPosition()));
        sprite.move(Vector2f.mul(dir, moveSpeed));
    }

    @Override
    public void onCollision(Actor collider) {
        super.onCollision(collider);
    }

    public void reduceHP(float damage) {
        currentHP -= damage;
    }

    protected void onDeath() {
        System.out.println("Skeleton died!");
        readyForDestruction = true;
        PlayerXPManager.getInstance().gainXP(XP);
    }
}
