package Game.Scene;

import Game.Game;
import Game.GameManager;
import Generic.Actor;
import Generic.DynamicActor;
import Generic.StaticActor;
import Generic.VectorArithmetic;
import Items.Consumeables.Potions.HealthPotion;
import Items.Consumeables.Potions.SpeedPotion;
import Monsters.Rat;
import Monsters.Skeleton;
import Player.Player;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Matthew
 * Date: 15/02/14
 * Time: 12:56
 * To change this template use File | Settings | File Templates.
 */
public class Scene {

    //a list of all mobile actors in the scene
    private List<DynamicActor> dynamicActors = new ArrayList<DynamicActor>();

    //a list of all immobile actors in the scene
    private List<StaticActor> staticActors = new ArrayList<StaticActor>();

    private String title;

    private Map map;

    public Scene(String title) {
        dynamicActors.add(GameManager.getInstance().getPlayer());
        this.title = title;
        System.out.println("[Scene()] Welcome to the game");
        map = new Map();
    }

    public void generateMap(String tileMap, String level) {


        map.generateFromFile(tileMap, level);


        Random random = new Random();
        int noOfEnemies = random.nextInt(10) + 10;

        for (int i = 0; i < noOfEnemies; i++) {
            if (random.nextInt(2) == 0) {
                Skeleton skeleton = new Skeleton();
                skeleton.setPosition(map.getLocalBounds().width * random.nextFloat(), map.getLocalBounds().height * random.nextFloat());
                dynamicActors.add(skeleton);
            } else {
                Rat rat = new Rat();
                rat.setPosition(map.getLocalBounds().width * random.nextFloat(), map.getLocalBounds().height * random.nextFloat());
                dynamicActors.add(rat);
            }
        }


        int noOfPots = random.nextInt(10) + 5;

        for (int i = 0; i < noOfPots; i++) {
            if (random.nextInt(2) == 0) {
                HealthPotion pot = new HealthPotion();
                pot.setPosition(i * random.nextInt(100), i * random.nextInt(100));
                staticActors.add(pot);
            } else {
                SpeedPotion pot = new SpeedPotion();
                pot.setPosition(i * random.nextInt(100), i * random.nextInt(100));
                staticActors.add(pot);
            }
        }

    }

    public List<DynamicActor> getDynamicActors() {
        return dynamicActors;
    }

    public List<StaticActor> getStaticActors() {
        return staticActors;
    }

    public Player getPlayer() {
        Player player = null;

        for (Actor actor : dynamicActors) {
            if (actor instanceof Player) {
                player = (Player) actor;
            }
        }

        if (player == null) {
            throw new NullPointerException("[Scene.checkCollisions()] Player is undefined!");
        }

        return player;
    }

    public void checkCollisions() {

        //first loop to check dynamic actors against all other dynamic actors
        List<DynamicActor> tempDActors = new ArrayList<DynamicActor>(dynamicActors);

        for (int i = 0; i < tempDActors.size(); i++) {

            DynamicActor a = tempDActors.get(i);
            FloatRect aCollRect = VectorArithmetic.moveRect(a.getBoundingRect(), a.getVelocity());

            for (int j = i + 1; j < tempDActors.size(); j++) {

                DynamicActor b = tempDActors.get(j);
                FloatRect bCollRect = VectorArithmetic.moveRect(b.getBoundingRect(), ((DynamicActor) b).getVelocity());

                if (aCollRect.intersection(bCollRect) != null) {
                    a.onCollision(b, aCollRect.intersection(bCollRect));
                    b.onCollision(a, aCollRect.intersection(bCollRect));
                }
            }
        }

        //second loop checks all static actors against dynamic actors(stationary objects can never collide)

        List<StaticActor> tempSActors = new ArrayList<StaticActor>(staticActors);

        for (StaticActor sActor : tempSActors) {

            FloatRect sRect = sActor.getBoundingRect();

            for (DynamicActor dActor : tempDActors) {

                FloatRect dRect = VectorArithmetic.moveRect(dActor.getBoundingRect(), dActor.getVelocity());

                if (sRect.intersection(dRect) != null) {
                    dActor.onCollision(sActor, sRect.intersection(dRect));
                    sActor.onCollision(dActor, sRect.intersection(dRect));
                }
            }
        }
    }

    public void update() {

        List<DynamicActor> tempDActors = new ArrayList<DynamicActor>(dynamicActors);

        //update actor positions
        //doing this after detecting collisions prevents 2 collisions being detected
        for (DynamicActor actor : tempDActors) {
            actor.update();
        }
    }

    public String getTitle(){return title;}

    public void addDynamicActor(DynamicActor dynamicActor) {
        dynamicActors.add(dynamicActor);
    }

    public void removeDynamicActor(DynamicActor dynamicActor) {
        dynamicActors.remove(dynamicActor);
    }

    public void addStaticActor(StaticActor staticActor) {
        staticActors.add(staticActor);
    }

    public void removeStaticActor(StaticActor staticActor) {
        staticActors.remove(staticActor);
    }

    public void draw(RenderWindow window) {

        //draw the tile map
        window.draw(map);

        //get a floating rectangle that represents the edge of the visible screen
        FloatRect playerRect = new FloatRect(Vector2f.sub(getPlayer().getPosition(), new Vector2f(Game.screenW / 2, Game.screenH / 2)), new Vector2f(window.getSize()));

        //draw dynamic actors
        for (DynamicActor dActor : dynamicActors) {

            if (((Sprite) dActor.getDrawable()).getGlobalBounds().intersection(playerRect) != null) {
                //only draw actors that are on the screen
                dActor.draw(window);
            }
        }

        for (StaticActor sActor : staticActors) {
            if (!(sActor instanceof MapTile)) {
                sActor.draw(window);
            }
        }
    }
}
