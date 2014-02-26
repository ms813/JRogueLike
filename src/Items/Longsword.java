package Items;

import Game.Game;
import Game.UI.TextureLibrary;
import Generic.Actor;
import Monsters.Monster;
import Player.PlayerManagers.PlayerMeleeManager;
import MeleeCombat.MeleeWeapon;
import MeleeCombat.MeleeWeaponAttackType;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Random;

/**
 * Created by Matthew on 24/02/14.
 */
public class Longsword extends MeleeWeapon implements Actor {

    private Sprite sprite;

    private float damage = 10f;

    private boolean readyForDestruction = false;
    private boolean attacking = false;
    private float startRot;

    //angle of swing arc in degrees
    private float swingArc;

    public Longsword(){

        attackType = MeleeWeaponAttackType.SLASH;
        swingArc = 120;
        name = "Longsword";

        Texture texture = TextureLibrary.getTexture("longsword");

        sprite = new Sprite(texture);
        sprite.setOrigin(sprite.getLocalBounds().width/2, sprite.getLocalBounds().height);
    }


    public void attack(float _startRot){
        startRot = _startRot;
        Game.getCurrentScene().addActor(this);
        sprite.setRotation(startRot - swingArc/2);
        attacking = true;
    }

    @Override
    public float getDamage(){
        return damage;
    }

    public void update(){
//        Vector2f pos = PlayerMeleeManager.getInstance().getPlayer().getCurrentPosition();
        //sprite.setPosition(pos);

        if(attacking){
            sprite.rotate(3);
        }
        if(sprite.getRotation() >= startRot + swingArc/2){
            attacking = false;
            Game.getCurrentScene().removeActor(this);
        }
    }

    public void draw(RenderWindow window){
        if(attacking){
            window.draw(sprite);
        }
    }

    public Sprite getDrawable(){
        return sprite;
    }

    public void onCollision(Actor actor){
        if(actor instanceof Monster){
            Monster monster = (Monster) actor;

            monster.reduceHP(damage);
        }
    }

    public Vector2f getCurrentPosition(){
        return sprite.getPosition();
    }

    public boolean isReadyForDestruction(){
        return readyForDestruction;
    }

    public void pickUp(Actor actor){
    }

    public Sprite getIcon(){
        return sprite;
    }

    public void setPosition(float x, float y){
        sprite.setPosition(x, y);
    }
}
