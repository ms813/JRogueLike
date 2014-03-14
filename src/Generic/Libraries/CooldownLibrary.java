package Generic.Libraries;

import Generic.CSVLoader;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Matthew on 14/03/14.
 */
public class CooldownLibrary {

    private HashMap<String, Float> cooldownHash = new HashMap<String, Float>();

    private static CooldownLibrary instance = null;

    protected CooldownLibrary(){
        ArrayList<String[]> fromFile = CSVLoader.load("resources/vals/cooldowns");

        //ignore the header row
        fromFile.remove(0);

        for(String[] line : fromFile){
            cooldownHash.put(line[0], Float.parseFloat(line[1]));
        }
    }

    public static CooldownLibrary getInstance(){
        if(instance == null){
            instance = new CooldownLibrary();
        }
        return instance;
    }

    public float getCooldown(String key){
        return cooldownHash.get(key);
    }


}
