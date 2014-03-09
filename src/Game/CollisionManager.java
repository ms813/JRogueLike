package Game;

/**
 * Created by Matthew on 09/03/14.
 */
public class CollisionManager {

    public static boolean collidesByAxis(float o1pos, float o1size, float o2pos, float o2size){
        return ((o1pos + o1size) > o2pos &&
                (o2pos + o2size) > o1pos);
    }
}
