
package game.logic;

import commons.*;
import game.gui.GameGUIController;
import game.gui.GameWindow;
import java.util.*;

 
public class Engine {
	
	public static GameWindow window = new GameWindow();
	public static GameGUIController controller = window.controller;
	
	public static InputSlot inputAction = new InputSlot();
	public static InputSlot inputDirection = new InputSlot();
	
	public static List<Actor> actors = new ArrayList<>();
	public static Console console = new Console();
	public static Board activeBoard;
	public static AlertManager alertManager = new AlertManager();
	public static MapLoader maploader = new MapLoader();

}
