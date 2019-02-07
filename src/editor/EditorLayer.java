package editor;

import editor.fxml.*;
import java.util.*;
import javafx.scene.control.*;
import resources.*;

/**
 *
 * @author Jan
 */
public enum EditorLayer {
	
	Background(DataMap.get().backgroundMap),
	Ground1(DataMap.get().groundMap),
	Ground2(DataMap.get().groundMap),
	Actor(DataMap.get().actorMapDummy),
	Object1(DataMap.get().objectMap),
	Object2(DataMap.get().objectMap),
	Collision(DataMap.get().collisionMap),
	Light(DataMap.get().lightMapDummy);
	
	private Map<Character, String> layerMap;

	EditorLayer(Map<Character, String> layerMap) {
		this.layerMap = layerMap;
	}
	
	public static void redrawLayer(EditorLayer layer) {
		switch(layer) {
			case Background: EditorWindow.getController().redrawBackground(); break;
			case Ground1: EditorWindow.getController().redrawGround0(); break;
			case Ground2: EditorWindow.getController().redrawGround1(); break;
			case Object1: EditorWindow.getController().redrawObjects0(); break;
			case Object2: EditorWindow.getController().redrawObjects1(); break;
			case Collision: EditorWindow.getController().redrawCollisions();
			case Light: EditorWindow.getController().redrawLights(); break;
		}
	}

	public Map<Character, String> getLayerMap() {
		return layerMap;
	}
}
