import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
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

    Sprite tileMap;

    private Texture bgTexture = new Texture();

    public Scene(String _sceneName) {

        actors.add(new Player());

        sceneName = _sceneName;

        try {
            bgTexture.loadFromFile(Paths.get("resources" + File.separatorChar + "map.png"));
            tileMap = new Sprite(bgTexture);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Random random = new Random();
        int noOfEnemies = random.nextInt(50)+20;

        for (int i = 20; i < noOfEnemies; i++) {
            Skeleton skeleton = new Skeleton();
            skeleton.setPosition(tileMap.getGlobalBounds().width * random.nextFloat(), tileMap.getGlobalBounds().height * random.nextFloat());
            System.out.println("Skeleton added at " + skeleton.getCurrentPosition());
            actors.add(skeleton);
        }
    }

    public Sprite getMap(){
        return tileMap;
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

        List<Actor> aList = new ArrayList<Actor>(actors);

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

    public void update(){
        List<Actor> tempActors = new ArrayList<Actor>(actors);

        //finally update the projectile positions
        //doing this after detecting collisions prevents 2 collisions being detected
        for (Actor actor : tempActors) {
            if (!actor.isReadyForDestruction()) {
                actor.update();
            } else {
                actors.remove(actor);
            }
        }
    }

    public void addActor(Actor actor) {
        actors.add(actor);
    }

    public void draw(RenderWindow window){

        window.draw(tileMap);

        for(Actor actor : actors){
            actor.draw(window);
        }
    }
}
