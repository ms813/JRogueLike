package Player;

import Generic.CSVLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Matthew on 12/03/14.
 */
public class BloodlineLibrary {

    private static BloodlineLibrary instance = null;

    private HashMap<String, Bloodline> bloodlines = new HashMap<String,Bloodline>();


    protected BloodlineLibrary(){
        ArrayList<String[]> bloodlineFileContents = CSVLoader.load("resources/vals/bloodlines");

        //remove the file column headers
        bloodlineFileContents.remove(0);

        for(String[] line : bloodlineFileContents){
            bloodlines.put(line[0], new Bloodline(line));
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
