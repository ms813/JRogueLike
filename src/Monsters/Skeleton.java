package Monsters;

import Game.Game;
import Generic.Libraries.TextureLibrary;
import Generic.Actor;
import Generic.VectorArithmetic;
import MagicSpells.IceBolt;
import Game.Main;
import MagicSpells.MagicDart;
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
        XP = 3f;
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
            MagicDart spell = new MagicDart(this);
            spell.castSpell(PlayerXPManager.getInstance().getPlayer().getPosition(), 1);
        }

        counter++;
        Vector2f dir = Vector2f.sub(PlayerXPManager.getInstance().getPlayer().getPosition(), sprite.getPosition());
        changeVelocity(Vector2f.mul(VectorArithmetic.normalize(dir), Main.getDeltaSeconds()));
    }

    @Override
    public void onCollision(Actor collider, FloatRect collisionRect) {
        super.onCollision(collider, collisionRect);
    }

    @Override
    public void reduceHP(float damage) {
        super.reduceHP(damage);
    }

    protected void onDeath() {
        super.onDeath();
        System.out.println("[Skeleton.onDeath()] Skeleton died!");
    }
}
