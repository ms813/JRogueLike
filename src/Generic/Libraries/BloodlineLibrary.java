package Generic.Libraries;

import Generic.CSVLoader;
import Player.Bloodline;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Matthew on 12/03/14.
 */
public class BloodlineLibrary {

    private static BloodlineLibrary instance = null;

    private HashMap<String, Bloodline> bloodlines = new HashMap<String,Bloodline>();


    protected BloodlineLibrary(){
        try {
            ArrayList<String[]> bloodlineFileContents = CSVLoader.load("resources/vals/bloodlines");

            //remove the file column headers
            bloodlineFileContents.remove(0);

            for (String[] line : bloodlineFileContents) {
                bloodlines.put(line[0], new Bloodline(line));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static BloodlineLibrary getInstance(){
        if(instance == null){
            instance = new BloodlineLibrary();
        }
        return instance;
    }

     public Bloodline getBloodline(String name){
        return bloodlines.get(name);
     }
}
