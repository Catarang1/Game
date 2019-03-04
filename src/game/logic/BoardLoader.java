package game.logic;

// @author Jan
import commons.*;
import java.io.*;
import java.util.logging.*;
import resources.*;

public class BoardLoader {

	public void load(String code) {
		
		//TODO check path of loading ( loading some naughty bois outside of project )
		
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
	
	public void loadTest() {
		try {
			
			ObjectInputStream oos = new ObjectInputStream(DataMap.class.getResourceAsStream("0001.map"));
			Board loaded = (Board) oos.readObject();
			Engine.setActiveBoard(loaded);
			Engine.console.write(Engine.activeBoard.getCode() + " " + Engine.activeBoard.getTitle());
		
		} catch (FileNotFoundException ex) {
			Logger.getLogger(BoardLoader.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException | ClassNotFoundException ex) {
			Logger.getLogger(BoardLoader.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
