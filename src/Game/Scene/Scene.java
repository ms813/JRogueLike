package Game.Scene;

import Generic.Actor;
import Generic.DynamicActor;
import Generic.StaticActor;
import Items.Consumeables.Potions.HealthPotion;
import Items.Consumeables.Potions.SpeedPotion;
import Monsters.Skeleton;
import Player.Player;
import org.jsfml.graphics.*;

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

    private String sceneName;

    private Map map = new Map();

    public Scene(String _sceneName) {

        dynamicActors.add(new Player());

        sceneName = _sceneName;
    }

    public void generateMap(String tileMap) {
        map.generate(tileMap, 100, 100);

        Random random = new Random();
        int noOfEnemies = random.nextInt(50) + 20;

        for (int i = 0; i < noOfEnemies; i++) {
            Skeleton skeleton = new Skeleton();
            skeleton.setPosition(map.getLocalBounds().width * random.nextFloat(), map.getLocalBounds().height * random.nextFloat());
            dynamicActors.add(skeleton);
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
            Sprite aSprite = (Sprite) a.getDrawable();
            FloatRect aRect = aSprite.getGlobalBounds();

            for (int j = i + 1; j < tempDActors.size(); j++) {
                DynamicActor b = tempDActors.get(j);
                Sprite bSprite = (Sprite) b.getDrawable();
                FloatRect bRect = bSprite.getGlobalBounds();

                if (aRect.intersection(bRect) != null) {
                    a.onCollision(b);
                    b.onCollision(a);
                }
            }
        }

        //second loop checks all static actors (stationary objects can never collide)

        List<StaticActor> tempSActors = new ArrayList<StaticActor>(staticActors);

        for (StaticActor sActor : tempSActors) {

            FloatRect sRect;

            if (sActor instanceof MapTile) {
                sRect = ((MapTile) sActor).getDrawable().getBounds();
            } else {
                sRect = ((Sprite) sActor.getDrawable()).getGlobalBounds();
            }

            for (DynamicActor dActor : tempDActors) {

                Sprite dSprite = (Sprite) dActor.getDrawable();
                FloatRect dRect = dSprite.getGlobalBounds();

                if (sRect.intersection(dRect) != null) {
                    dActor.onCollision(sActor);
                    sActor.onCollision(dActor);
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

        //draw dynamic actors
        for (DynamicActor dActor : dynamicActors) {
            dActor.draw(window);
        }

        for (StaticActor sActor : staticActors) {
            if (!(sActor instanceof MapTile)) {
                sActor.draw(window);
            }
        }
    }


}
