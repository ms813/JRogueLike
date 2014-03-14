package Monsters;

import Game.Game;
import Generic.Libraries.TextureLibrary;
import Generic.Actor;
import MagicSpells.IceBolt;
import Player.PlayerManagers.PlayerXPManager;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

/**
 * Created by Matthew on 18/02/14.
 */
public class Skeleton extends Monster {

    public Skeleton() {

        maxHP = 20f;
        currentHP = maxHP;
        XP = 10f;
        acceleration = 75f;
        maxSpeed = 3f;
        friction = 0.9f;
        mass = 50f;

        sprite = new Sprite(TextureLibrary.getTexture("skeleton"));
        sprite.setScale(0.5f, 0.5f);
        //set the origin to the center of the sprite rather than the top left
        sprite.setOrigin(sprite.getLocalBounds().width / 2, sprite.getLocalBounds().height / 2);


        hpBar = buildHPBar();
    }

    int counter = 0;

    public void update() {
        super.update();

        if (counter % 180 == 0) {
            IceBolt iceBolt = new IceBolt(this);
            iceBolt.castSpell(PlayerXPManager.getInstance().getPlayer().getPosition(), 1);
        }

        counter++;
        Vector2f dir = Vector2f.sub(PlayerXPManager.getInstance().getPlayer().getPosition(), sprite.getPosition());
        changeVelocity(dir);
    }

    @Override
    public void onCollision(Actor collider, FloatRect collisionRect) {
        super.onCollision(collider, collisionRect);
    }

    public void reduceHP(float damage) {
        currentHP -= damage;
    }

    protected void onDeath() {
        System.out.println("[Skeleton.onDeath()] Skeleton died!");
        Game.getCurrentScene().removeDynamicActor(this);
        PlayerXPManager.getInstance().gainXP(XP);
    }
}
