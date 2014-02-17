import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

/**
 * Created by Matthew on 16/02/14.
 */
public class VectorArithmetic {

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

    strictfp static float AngleBetweenVectorsDegrees(Vector2f a, Vector2f b){
        float dot = a.x * b.x + a.y * b.y;
        float cosTheta = dot/(magnitude(a) * magnitude(b));

        return new Float(Math.acos(cosTheta) * 180/Math.PI);
    }


}
