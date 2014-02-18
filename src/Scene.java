import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Matthew
 * Date: 15/02/14
 * Time: 12:56
 * To change this template use File | Settings | File Templates.
 */
public class Scene{

    //by convention, the player should always be actors.get(0);
    private List<Actor> actors = new ArrayList<Actor>();
    private String sceneName;

    private Texture bgTexture = new Texture();
    private Sprite bgSprite;

    public Scene(String _sceneName){

        actors.add(new Player());
        Skeleton skeleton = new Skeleton();
        skeleton.setPosition(500,500);
        actors.add(skeleton);

        sceneName = _sceneName;

        try{
            bgTexture.loadFromFile(Paths.get("resources/" + _sceneName + ".png"));


            bgSprite = new Sprite(bgTexture);
            bgSprite.setOrigin(new Vector2f(Game.screenW, Game.screenH));
            
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public Sprite getBackground(){
        return bgSprite;

    }

    public List<Actor> getActors(){
        return actors;
    }

    public Player getPlayer(){
        Player player = null;

        for(Iterator<Actor> i = actors.iterator(); i.hasNext();){
            Actor actor = i.next();
            if(actor instanceof Player){
                player = (Player) actor;
            }
        }

        if(player == null){
            System.err.println("Player is undefined!");
        }

        return player;
    }

    public void updateProjectiles(){

        //build 2 lists, one of projectiles and one of collidables
        //TODO this will need refined as enemy projectiles need to be blocked with shields etc

        List<Projectile> tempProjectiles = new ArrayList<Projectile>();
        List<Actor> tempCollidables = new ArrayList<Actor>();
        List<Actor> tempActors = new ArrayList<Actor>(actors);

        for(Actor actor : tempActors){
            if(actor instanceof Projectile){

                if(!((Projectile) actor).isReadyForDestruction()){
                    ((Projectile) actor).updatePosition();
                } else{
                    actors.remove(actor);
                }

                tempProjectiles.add((Projectile) actor);
            } else if(!(actor instanceof  Player)) {
                tempCollidables.add(actor);
            }
        }

        for(Projectile projectile : tempProjectiles){
            for(Actor collidable : tempCollidables){

                Sprite projSprite = (Sprite) projectile.getDrawable();
                FloatRect projBounds = projSprite.getGlobalBounds();

                Sprite collidableSprite = (Sprite) collidable.getDrawable();
                FloatRect collidableBounds = collidableSprite.getGlobalBounds();

                if(projBounds.intersection(collidableBounds) != null){

                    projectile.onCollision(collidable);
                    collidable.onCollision(projectile);
                }
            }
        }

    }

    public void addActor(Actor actor){
        actors.add(actor);
    }


}
