package game.logic;

// @author Jan
import commons.*;
import java.io.*;

public class MapLoader {

	public void load(String code) {
		Engine.console.write("load method echo");
		if (!code.matches("(\\d){4}")) {
			Engine.console.write("code does not match regex for map ID");
			return;
		} else {
			Engine.console.write("code matches regex");
			try {
				FileInputStream fis = new FileInputStream("boards/" + code + ".map");
				ObjectInputStream ois = new ObjectInputStream(fis);
				Board loaded = (Board) ois.readObject();
				Engine.activeBoard = loaded;
				Engine.console.write("loaded and assigned");
				System.out.println(Engine.activeBoard);
			} catch (IOException ex) {
				Engine.console.write("File " + code + ".map not found", Console.ERROR);
			} catch (ClassNotFoundException ex) {
				Engine.console.write("Class not found", Console.ERROR);
			}
		}
	}
}
