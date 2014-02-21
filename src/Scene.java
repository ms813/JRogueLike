import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

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

    private Texture bgTexture = new Texture();
    private Sprite bgSprite;

    public Scene(String _sceneName) {

        actors.add(new Player());

        sceneName = _sceneName;

        try {
            bgTexture.loadFromFile(Paths.get("resources/" + _sceneName + ".png"));


            bgSprite = new Sprite(bgTexture);
            bgSprite.setOrigin(new Vector2f(Game.screenW, Game.screenH));

        } catch (IOException e) {
            e.printStackTrace();
        }

        Random random = new Random();
        int noOfEnemies = random.nextInt(50);

        for (int i = 0; i < noOfEnemies; i++) {
            Skeleton skeleton = new Skeleton();
            skeleton.setPosition(random.nextFloat() * bgSprite.getGlobalBounds().width, random.nextFloat() * bgSprite.getGlobalBounds().width);
            System.out.println("Skeleton added at " + skeleton.getCurrentPosition());
            actors.add(skeleton);
        }
    }

    public Sprite getBackground() {
        return bgSprite;

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

    public void checkProjectileCollisions() {
       //
        List<Actor> tempActors = new ArrayList<Actor>(actors);
        List<Projectile> tempProjectiles = new ArrayList<Projectile>();
        List<Actor> tempCollidables = new ArrayList<Actor>();

        //build list of projectiles and non-projectiles here
        //this ensures projectiles are not colliding against themselves
        for (Actor actor : tempActors) {
            if (actor instanceof Projectile) {
                tempProjectiles.add((Projectile) actor);
            } else {
                tempCollidables.add(actor);
            }
        }

        //check for collisions between projectiles and non-projectiles
        for (Projectile projectile : tempProjectiles) {

            Sprite projSprite = (Sprite) projectile.getDrawable();
            FloatRect projBounds = projSprite.getGlobalBounds();

            for (Actor collidable : tempCollidables) {

                Sprite collidableSprite = (Sprite) collidable.getDrawable();
                FloatRect collidableBounds = collidableSprite.getGlobalBounds();

                if (projBounds.intersection(collidableBounds) != null) {

                    projectile.onCollision(collidable);
                    collidable.onCollision(projectile);
                }
            }
        }
    }


    public void updateActors() {

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
}
