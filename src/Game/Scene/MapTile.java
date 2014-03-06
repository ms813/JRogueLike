package Game.Scene;

import Generic.Actor;
import Player.Player;
import Generic.StaticActor;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

/**
 * Created by Matthew on 05/03/14.
 */
public class MapTile implements StaticActor {

    //contains texture coordinates and passable information
    private MapTileReference tileRef;

    private Vector2f[] corners = new Vector2f[4];

    private VertexArray vertices = new VertexArray(PrimitiveType.QUADS);

    public MapTile(MapTileReference tileRef, Vector2f[] corners){
        this.tileRef = tileRef;
        this.corners = corners;

        for(int i = 0; i < corners.length; i++){
            vertices.add(new Vertex(corners[i],  tileRef.getTextCoords()[i]));
        }
    }

    public MapTileReference getTileRef() {
        return tileRef;
    }

    public Vector2f[] getCorners() {
        return corners;
    }

    public Vector2f getCorner(int i){
        return corners[i];
    }

    public Vector2f[] getTextCoords(){
        return tileRef.getTextCoords();
    }

    public boolean isPassable(){
        return tileRef.isPassable();
    }

    public Vertex getVertex(int i){
        return vertices.get(i);
    }

    public VertexArray getDrawable(){
        return vertices;
    }

    public void onCollision(Actor actor){
    }

    public void draw(RenderWindow window){
        // this should never do anything as the map itself is drawn once by the scene
    }

    public Vector2f getPosition(){
        //return the top left corner
        return corners[0];
    }

    public float getWidth(){
        return (Vector2f.sub(corners[1], corners[0])).x;
    }

    public float getHeight(){
        return (Vector2f.sub(corners[3], corners[0])).y;
    }

    public void update(){

    }

    public FloatRect getCollisionRect(){
        return vertices.getBounds();
    }
}
