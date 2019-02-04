package editor;

import java.util.*;
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

	public Map<Character, String> getLayerMap() {
		return layerMap;
	}
	
	
	
}
