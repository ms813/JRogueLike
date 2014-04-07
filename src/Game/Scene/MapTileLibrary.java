package Game.Scene;

import org.jsfml.system.Vector2f;

/**
 * Created by Matthew on 04/03/14.
 */
public enum MapTileLibrary {

    //TODO extend to include more tiles

    //tile (0,0)
    GRASS(new Vector2f[]{
            new Vector2f(0, 0),  //top left
            new Vector2f(72, 0), //top right
            new Vector2f(72, 72),//bottom right
            new Vector2f(0, 72)},//bottom left
            Passable.TRUE
    ),

    //tile (1,0)
    WALL(new Vector2f[]{
            new Vector2f(72, 0),  //top left
            new Vector2f(144, 0), //top right
            new Vector2f(144, 72),//bottom right
            new Vector2f(72, 72)},//bottom left
            Passable.FALSE
    ),

    //tile(2,0)
    TREE(new Vector2f[]{
            new Vector2f(144, 0),  //top left
            new Vector2f(216, 0),  //top right
            new Vector2f(216, 72), //bottom right
            new Vector2f(144, 72)},//bottom left
            Passable.FALSE
    ),

    //tile(3,0)
    BOULDER(new Vector2f[]{
            new Vector2f(216, 0),  //top left
            new Vector2f(288, 0),  //top right
            new Vector2f(288, 72), //bottom right
            new Vector2f(216, 72)},//bottom left
            Passable.FLYING
    ),

    //tile(4,0)
    SAND(new Vector2f[]{
            new Vector2f(288, 0),  //top left
            new Vector2f(360, 0),  //top right
            new Vector2f(360, 72), //bottom right
            new Vector2f(288, 72)},//bottom left
            Passable.TRUE
    ),

    //tile(5,0)
    WATER_SHALLOW(new Vector2f[]{
            new Vector2f(360, 0),  //top left
            new Vector2f(432, 0),  //top right
            new Vector2f(432, 72), //bottom right
            new Vector2f(360, 72)},//bottom left
            Passable.TRUE
    ),

    //tile(6,0)
    WATER_DEEP(new Vector2f[]{
            new Vector2f(432, 0),  //top left
            new Vector2f(504, 0),  //top right
            new Vector2f(504, 72), //bottom right
            new Vector2f(432, 72)},//bottom left
            Passable.FLYING
    );


    private final Vector2f[] textCoords;
    private final Passable passableState;

    MapTileLibrary(Vector2f[] vertexArray, Passable passable) {
        this.textCoords = vertexArray;
        this.passableState = passable;
    }

    public Vector2f[] getTextCoords() {
        return textCoords;
    }

    public Passable passable() {
        return passableState;
    }
}
