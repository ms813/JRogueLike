package Generic;

import org.omg.CosNaming._NamingContextExtStub;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Matthew on 12/03/14.
 */
public class CSVLoader {

    public static ArrayList<String[]> load(String path) {
        String contents = "";
        try{
            contents = readFile(path + ".csv");
        } catch(IOException e){
            e.printStackTrace();
        }

        ArrayList<String[]> toReturn = new ArrayList<String[]>();

        String[] lines = contents.split("\n");

        for(String line : lines){
            line = line.substring(0, line.length() -1);
            toReturn.add(line.split(","));
        }

        return toReturn;
    }

    static String readFile(String path) throws IOException

    {
        Charset encoding = Charset.defaultCharset();
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return encoding.decode(ByteBuffer.wrap(encoded)).toString();
    }
}
