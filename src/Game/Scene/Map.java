package Game.Scene;

import Game.Game;
import Game.UI.TextureLibrary;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;


/**
 * Created by Matthew on 04/03/14.
 */
public class Map extends BasicTransformable implements Drawable {

    //array of vertices used to actually draw the map (vertex arrays have low performance overhead)
    private VertexArray vertexArray = new VertexArray(PrimitiveType.QUADS);

    //array of MapTiles detailing the corners of each tile and a MapTileReference object
    private MapTile[][] mapTiles;

    private Texture texture = new Texture();

    //tiles are 1 cm by 1 cm at a resolution of 72 pixels/cm
    int tilePixels = 72;

    public Map() {
    }

    public void generate(String tileSet, int noOfTilesX, int noOfTilesY) {

        texture = TextureLibrary.getTexture(tileSet);

        mapTiles = new MapTile[noOfTilesX][noOfTilesY];

        for (int i = 0; i < noOfTilesX; i++) {
            for (int j = 0; j < noOfTilesY; j++) {
                Vector2f[] corners = new Vector2f[4];
                corners[0] = new Vector2f(j * tilePixels, i * tilePixels);
                corners[1] = new Vector2f((j + 1) * tilePixels, i * tilePixels);
                corners[2] = new Vector2f((j + 1) * tilePixels, (i + 1) * tilePixels);
                corners[3] = new Vector2f(j * tilePixels, (i + 1) * tilePixels);


                //TODO update this to pick other tiles

                if (i % 5 == 0 || j % 5 == 0) {
                    mapTiles[i][j] = new MapTile(MapTileReference.WALL, corners);
                } else {
                    mapTiles[i][j] = new MapTile(MapTileReference.GRASS, corners);
                }

                for (int k = 0; k < corners.length; k++) {
                    vertexArray.add(mapTiles[i][j].getVertex(k));
                }


                //add unpassable tiles to the static actors list for collision checking
                if (!mapTiles[i][j].isPassable()) {
                    if (Game.getCurrentScene() != null) {
                        Game.getCurrentScene().addStaticActor(mapTiles[i][j]);
                    } else {
                        throw new NullPointerException("Scene hasn't been made yet!");
                    }
                }
            }
        }
    }

    @Override
    public void draw(RenderTarget target, RenderStates states) {

        RenderStates s = new RenderStates(
                states.blendMode,
                Transform.combine(states.transform, this.getTransform()),
                this.texture,  //this is the key line that applies the texture to the vertex array
                states.shader);

        target.draw(vertexArray, s);
    }

    public FloatRect getLocalBounds() {
        if (vertexArray.size() > 0) {
            FloatRect rect = new FloatRect(vertexArray.get(0).position, vertexArray.get(vertexArray.size() - 1).position);
            return rect;
        } else {
            throw new NullPointerException("[Map.getLocalBounds()] Map has not been generated yet! Run Map.generate() first");
        }

    }

}
