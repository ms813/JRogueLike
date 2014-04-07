package Game.Scene;

import Game.Game;
import Generic.CSVLoader;
import Generic.Libraries.TextureLibrary;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by Matthew on 04/03/14.
 */
public class Map extends BasicTransformable implements Drawable {

    //array of vertices used to actually draw the map (vertex arrays have low performance overhead)
    private VertexArray vertexArray = new VertexArray(PrimitiveType.QUADS);

    //array of MapTiles detailing the corners of each tile and a MapTileLibrary object
    private MapTile[][] mapTiles;

    private Texture texture = new Texture();
    private Random rnd = new Random();

    private SceneManager sceneManager = SceneManager.getInstance();

    //tiles are 1 cm by 1 cm at a resolution of 72 pixels/cm
    int tilePixels = 72;

    public Map(){}

    public void generateRandom(String tileSet, int noOfTilesX, int noOfTilesY){
        //use this constructor to generate a random map of dimensions (x,y)

    }

    public void generateFromFile(String tileSet, String mapFile){
        //use this constructor to create a map from a file

        texture = TextureLibrary.getTexture(tileSet);
        int noOfTilesY = 0;
        int noOfTilesX = 0;
        List<String[]> mapFromFile = new ArrayList<String[]>();

        if(!mapFile.equals("")) {
            try {
                mapFromFile = CSVLoader.load("resources/maps/" + mapFile);
                noOfTilesY = mapFromFile.get(0).length;
                noOfTilesX = mapFromFile.size();
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        mapTiles = new MapTile[noOfTilesX][noOfTilesY];

        for (int i = 0; i < noOfTilesX; i++) {
            for (int j = 0; j < noOfTilesY; j++) {
                Vector2f[] corners = new Vector2f[4];
                corners[0] = new Vector2f(j * tilePixels, i * tilePixels);
                corners[1] = new Vector2f((j + 1) * tilePixels, i * tilePixels);
                corners[2] = new Vector2f((j + 1) * tilePixels, (i + 1) * tilePixels);
                corners[3] = new Vector2f(j * tilePixels, (i + 1) * tilePixels);


                //TODO update this to pick other tiles

                mapTiles[i][j] = new MapTile(MapTileLibrary.valueOf(mapFromFile.get(i)[j]), corners);

                for (int k = 0; k < corners.length; k++) {
                    vertexArray.add(mapTiles[i][j].getVertex(k));
                }


                //add unpassable tiles to the static actors list for collision checking
               // if (mapTiles[i][j].passable() == Passable.FALSE || mapTiles[i][j].passable() == Passable.FLYING) {
                    if (sceneManager.getCurrentScene() != null) {
                        sceneManager.getCurrentScene().addStaticActor(mapTiles[i][j]);
                    } else {
                        throw new NullPointerException("Scene hasn't been made yet!");
                    }
                //}
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
