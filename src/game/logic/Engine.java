
package game.logic;

import commons.*;
import game.gui.GameGUIController;
import game.gui.GameWindow;
import java.util.*;

 
public class Engine {
	
	public static GameWindow window;
	public static GameGUIController controller;
	
	public static InputSlot inputDirection;
	public static InputSlot inputAction;
	
	public static List<Actor> actors;
	public static Console console;
	public static Board activeBoard;
	public static AlertManager alertManager;
	public static MapLoader maploader;

	static {
		window = new GameWindow();
		controller = window.controller;
		
		inputAction = new InputSlot();
		inputDirection = new InputSlot();
		
		actors = new ArrayList<>();
		console = new Console();
		alertManager = new AlertManager();
		maploader = new MapLoader();
	}

}
