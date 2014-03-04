package Game.Scene;

import Generic.Actor;
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

    //by convention, the player should always be actors.get(0);
    private List<Actor> actors = new ArrayList<Actor>();
    private String sceneName;

    private Map map = new Map();

    public Scene(String _sceneName) {

        actors.add(new Player());

        sceneName = _sceneName;

        map.generate("MapTiles", 50, 50);

        Random random = new Random();
        int noOfEnemies = random.nextInt(50) + 20;

        for (int i = 0; i < noOfEnemies; i++) {
            Skeleton skeleton = new Skeleton();
            skeleton.setPosition(map.getLocalBounds().width * random.nextFloat(), map.getLocalBounds().height * random.nextFloat());
            actors.add(skeleton);
        }

        int noOfPots = random.nextInt(10) + 5;

        for (int i = 0; i < noOfPots; i++) {
            if (random.nextInt(2) == 0) {
                HealthPotion pot = new HealthPotion();
                pot.setPosition(i*random.nextInt(100), i*random.nextInt(100));
                actors.add(pot);
            } else {
                SpeedPotion pot = new SpeedPotion();
                pot.setPosition(i*random.nextInt(100), i*random.nextInt(100));
                actors.add(pot);
            }
        }
    }

    public List<Actor> getActors() {
        return actors;
    }

    public Player getPlayer() {
        Player player = null;

        for (Actor actor : actors) {
            if (actor instanceof Player) {
                player = (Player) actor;
            }
        }

        if (player == null) {
            System.err.println("Player is undefined!");
        }

        return player;
    }

    public void checkCollisions() {

        List<Actor> aList = actors = new ArrayList<Actor>(actors);


        for (int i = 0; i < aList.size(); i++) {

            Actor a = aList.get(i);
            Sprite aSprite = (Sprite) a.getDrawable();
            FloatRect aRect = aSprite.getGlobalBounds();

            for (int j = i + 1; j < aList.size(); j++) {
                Actor b = aList.get(j);
                Sprite bSprite = (Sprite) b.getDrawable();
                FloatRect bRect = bSprite.getGlobalBounds();

                if (aRect.intersection(bRect) != null) {
                    a.onCollision(b);
                    b.onCollision(a);
                }
            }
        }
    }

    public void update() {
        List<Actor> tempActors = new ArrayList<Actor>(actors);

        //update actor positions
        //doing this after detecting collisions prevents 2 collisions being detected
        for (Actor actor : tempActors) {
            actor.update();

        }
    }

    public void addActor(Actor actor) {
        actors.add(actor);
    }

    public void removeActor(Actor actor) {
        actors.remove(actor);
    }

    public void draw(RenderWindow window) {

        //window.draw(tileMap);
        window.draw(map);

        for (Actor actor : actors) {
            actor.draw(window);
        }
    }


}
