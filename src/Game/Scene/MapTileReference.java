package Game.Scene;

import org.jsfml.system.Vector2f;

/**
 * Created by Matthew on 04/03/14.
 */
public enum MapTileReference {

    //TODO extend to include more tiles

    GRASS(new Vector2f[]{
            new Vector2f(0, 0),  //top left
            new Vector2f(72, 0), //top right
            new Vector2f(72, 72),//bottom right
            new Vector2f(0, 72)},//bottom left
            true),

    WALL(new Vector2f[]{
            new Vector2f(72, 0),  //top left
            new Vector2f(144, 0), //top right
            new Vector2f(144, 72),//bottom right
            new Vector2f(72, 72)}, //bottom left
            false);

    private Vector2f[] textCoords;
    private boolean passable;

    MapTileReference(Vector2f[] _vertexArray, boolean _passable) {
        textCoords = _vertexArray;
        passable = _passable;
    }

    public Vector2f[] getTextCoords() {
        return textCoords;
    }

    public boolean isPassable() {
        return passable;
    }
}
