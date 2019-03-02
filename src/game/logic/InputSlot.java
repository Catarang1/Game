
package game.logic;

// @author Jan

import java.util.*;
import javafx.scene.input.*;

 
public class InputSlot {
	
	public static HashMap<KeyCode, Moving> moveMap;
	public static HashMap<KeyCode, Facing> faceMap;
	
	static{
		moveMap = new HashMap<>();
		faceMap = new HashMap<>();
		
		moveMap.put(KeyCode.UP, Moving.UP);
		moveMap.put(KeyCode.DOWN, Moving.DOWN);
		moveMap.put(KeyCode.LEFT, Moving.LEFT);
		moveMap.put(KeyCode.RIGHT, Moving.RIGHT);
		moveMap.put(null, Moving.IDLE);
		
		faceMap.put(KeyCode.UP, Facing.UP);
		faceMap.put(KeyCode.DOWN, Facing.DOWN);
		faceMap.put(KeyCode.LEFT, Facing.LEFT);
		faceMap.put(KeyCode.RIGHT, Facing.RIGHT);
	}
	
	private KeyCode input = null;

	public KeyCode getInput() {
		if (input == null)  return null;
		return input;
	}

	public void setInput(KeyCode key) {
		if (input == null) input = key;
	}
	


}
