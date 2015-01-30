
package fi.nano.tangential.fileProcessing;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nanofus
 */
public class LevelReader {
    
    ArrayList<String> level;
    
    public LevelReader(String levelName) {
        Scanner in = null;
        
        try {
            in = new Scanner(new FileReader("levels/" + levelName + ".txt"));
        } catch (FileNotFoundException ex) {
            System.out.println("Level file not found! Exiting...");
            System.exit(1);
        }
        
        level = new ArrayList<String>();
        
        while(in.hasNext()) {
            level.add(in.nextLine());
        }
        
    }
}
