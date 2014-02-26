package Game.UI;

import org.jsfml.graphics.Font;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by Matthew on 26/02/14.
 */
public class FontLibrary {

    public static Font arial = null;

    public static Font getFont(String font){
        if(font.equals("arial")){
             if(arial == null){
                 try{
                     arial = new Font();
                     arial.loadFromFile(Paths.get("resources" + File.separatorChar + "fonts" + File.separatorChar + "arial.ttf"));
                 } catch(IOException e){
                     e.printStackTrace();
                 }
             }
            return arial;

        }
        else return null;
    }

}
