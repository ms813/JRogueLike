package Monsters;

import Generic.Actor;
import Game.Main;
import Generic.VectorArithmetic;
import Player.Player;
import Generic.Libraries.TextureLibrary;
import Player.PlayerManagers.PlayerXPManager;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

import java.util.Random;

/**
 * Created by Matthew on 14/03/14.
 */
public class Rat extends Monster {

    float onTouchAttackCooldown;
    float cooldownRemaining;

    public Rat() {
        maxHP = 5f;
        currentHP = maxHP;
        XP = 1f;
        acceleration = 10f;
        maxSpeed = 6f;
        friction = 0.99f;
        mass = 3f;
        onTouchDamage = 1f;
        onTouchAttackCooldown = 2f;
        cooldownRemaining = onTouchAttackCooldown;

        sprite = new Sprite(TextureLibrary.getTexture("rat"));
        sprite.setOrigin(sprite.getLocalBounds().width / 2, sprite.getLocalBounds().height / 2);
        sprite.setScale(0.2f, 0.2f);
        hpBar = buildHPBar();
    }

    public void update() {
        super.update();

        Vector2f dir = Vector2f.sub(PlayerXPManager.getInstance().getPlayer().getPosition(), sprite.getPosition());
        changeVelocity(Vector2f.mul(VectorArithmetic.normalize(dir), Main.getDeltaSeconds()));

        if(cooldownRemaining > 0){
            cooldownRemaining -= Main.getDeltaSeconds();
        } else if(cooldownRemaining < 0){
            cooldownRemaining = 0;
        }
    }

    public void onCollision(Actor collider, FloatRect collisionRect) {
        super.onCollision(collider, collisionRect);
        if (collider instanceof Player) {
            if (cooldownRemaining == 0) {
                ((Player) collider).reduceHP(onTouchDamage);
                cooldownRemaining = onTouchAttackCooldown;
            } else{
                //bite is on cooldown
            }
        }
    }

    public void onDeath() {
        super.onDeath();
        System.out.println("[Rat.onDeath()] Rat died!");

    }
}
