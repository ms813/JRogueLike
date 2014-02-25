package Generic;

import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

import java.util.Random;

/**
 * Created by Matthew on 16/02/14.
 */
public class VectorArithmetic {

    public static final Vector2f UP = new Vector2f(0,-1);
    public static final Vector2f DOWN = new Vector2f(0,1);
    public static final Vector2f LEFT = new Vector2f(-1,0);
    public static final Vector2f RIGHT = new Vector2f(1,0);

    //add any functions in here that the basic Vector2 classes dont include.

    strictfp public static float magnitude(Vector2f vector){
         return new Float(Math.sqrt(Math.pow(vector.x, 2f) + Math.pow(vector.y, 2f)));
    }

    strictfp public static float magnitude(Vector2i vector){
        return new Float(Math.sqrt(Math.pow(vector.x, 2f) + Math.pow(vector.y, 2f)));
    }

    strictfp public static Vector2f normalize(Vector2f vector){
        return Vector2f.div(vector,magnitude(vector));
    }

    strictfp public static float AngleBetweenVectorsRadians(Vector2f a, Vector2f b){
        float dot = a.x * b.x + a.y * b.y;
        float cosTheta = dot/(magnitude(a) * magnitude(b));

        return new Float(Math.acos(cosTheta));
    }

    strictfp public static float AngleBetweenVectorsDegrees(Vector2f a, Vector2f b){
        return new Float(AngleBetweenVectorsRadians(a,b)  * 180/Math.PI);
    }

    strictfp public static Vector2f RandomOnUnitCircle(){
        Random rnd = new Random();

        return normalize(new Vector2f(rnd.nextFloat(), rnd.nextFloat()));
    }
}
