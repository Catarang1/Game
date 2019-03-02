
package game.logic;

// @author Jan

import commons.*;
import java.io.*;
import javafx.scene.paint.*;

class MapLoader {
	
	public void loadMap(String code) {
		if (!code.matches("[\n]{4}")) return;
		
		try {
			FileInputStream fis = new FileInputStream("boards/"+ code + ".map");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Board loaded = (Board) ois.readObject();
			Engine.activeBoard = loaded;
		} catch (IOException ex) {
			Engine.console.write("File not found", Color.RED);
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			Engine.console.write("Class not found", Color.RED);
			ex.printStackTrace();
		}
	}

}
