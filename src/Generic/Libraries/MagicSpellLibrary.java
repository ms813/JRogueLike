package Generic.Libraries;

import Generic.CSVLoader;
import MagicSpells.SpellInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Matthew on 14/03/14.
 */
public class MagicSpellLibrary {

    private HashMap<String, SpellInfo> magicSpellHash = new HashMap<String, SpellInfo>();

    private static MagicSpellLibrary instance = null;

    protected MagicSpellLibrary() {
        try {
            ArrayList<String[]> fromFile = CSVLoader.load("resources/vals/magicSpellVals");

            //ignore the header row
            fromFile.remove(0);

            for (String[] line : fromFile) {
                magicSpellHash.put(line[0], new SpellInfo(line[0], Float.parseFloat(line[1]), Integer.parseInt(line[2]), Float.parseFloat(line[3])));
            }
        } catch(IOException e){
            e.printStackTrace();
        }

    }

    public static MagicSpellLibrary getInstance() {
        if (instance == null) {
            instance = new MagicSpellLibrary();
        }
        return instance;
    }

    public float getCooldown(String key) {
        return magicSpellHash.get(key).getCoolDown();
    }

    public int getCastsPerReload(String key) {
        return magicSpellHash.get(key).getCastsPerReload();
    }

    public SpellInfo getSpellInfo(String key) {
        return magicSpellHash.get(key);
    }

    public float getReloadManaCost(String key){
        return magicSpellHash.get(key).getReloadManaCost();
    }
}
