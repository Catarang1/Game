package resources;

// @author Jan
import java.io.*;
import java.util.*;
import java.util.jar.*;
import java.util.logging.*;
import javafx.scene.effect.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

public final class DataMap {

	private static DataMap instance;
	private char[] charSet;
	public Map<Character, String> backgroundMap = new HashMap<>();
	public Map<Character, String> groundMap = new HashMap<>();
	public Map<Character, String> actorMapDummy = new HashMap<>();
	public Map<Character, String> objectMap = new HashMap<>();
	public Map<Character, String> collisionMap = new HashMap<>();
	public Map<Character, String> lightMapDummy = new HashMap<>();
	
	public Map<Character, Object> actorMapActual = new HashMap<>();
	public Map<Character, Circle> lightMapActual = new HashMap<>();
	
	/*
	
	add ground carpets
	multiple lightshades on windows
	borders and floors in dungeon
	delete staris w/o background
	chest unite sizes
	market stans multicolor
	multicolor roofs
	multicolor flowers
	multicolor dishes
	
	*/

	private DataMap() {
		initializeCharSet();
		initializeGenericFilePaths();
		initializeActorMaps();
		initializeLightMaps();
	}

	public static DataMap get() {
		if (instance == null) {
			instance = new DataMap();
		}
		return instance;
	}

	void initializeCharSet() {
		List<Character> chars = new ArrayList<>();
		for (int i = 0x0000; i < 0x0512; i++) {
			if ((char) i != '0') {
				chars.add((char) i);
			}
		}
		charSet = new char[chars.size()];
		for (int i = 0; i < chars.size(); i++) {
			charSet[i] = chars.get(i);
		}
	}

	void initializeGenericFilePaths() {
		final ArrayList<String> paths = new ArrayList<>();

		final String path = "resources";
		final File jarFile = new File(DataMap.class.getProtectionDomain().getCodeSource().getLocation().getPath());

		if (jarFile.isFile()) {  // Run with JAR file
			JarFile jar = null;
			try {
				jar = new JarFile(jarFile);
			} catch (IOException ex) {
				Logger.getLogger(DataMap.class.getName()).log(Level.SEVERE, null, ex);
			}
			final Enumeration<JarEntry> entries = jar.entries(); //gives ALL entries in jar
			while (entries.hasMoreElements()) {
				final String name = entries.nextElement().getName();
				if (name.startsWith(path + "/") && name.endsWith(".png") || name.endsWith(".gif")) { //filter according to the path
					paths.add('/' + name);
				}
			}
			try {
				jar.close();
			} catch (IOException ex) {
				Logger.getLogger(DataMap.class.getName()).log(Level.SEVERE, null, ex);
			}

			System.out.println(paths.size() + " paths is present in arraylist");
//			for (String s : paths) {
//				System.out.println(s);
//			}

			for (String p : paths) {
				if (p.contains("/background/")) {
					backgroundMap.put(charSet[backgroundMap.size()], p);
				} else if (p.contains("/ground/")) {
					groundMap.put(charSet[groundMap.size()], p);
				} else if (p.contains("/object/")) {
					objectMap.put(charSet[objectMap.size()], p);
				} else if (p.contains("/collision/")) {
					collisionMap.put(charSet[collisionMap.size()], p);
				}
			}
		}
	}

	private void initializeActorMaps() {
		actorMapDummy.put(charSet[actorMapDummy.size()], "/resources/actor/npcDefault/AA.gif");
		
	}

	private void initializeLightMaps() {
		lightMapDummy.put(charSet[lightMapDummy.size()], "/resources/light/AA.gif");
		Circle aa = new Circle(75); aa.setEffect(new Shadow(BlurType.GAUSSIAN, Color.WHITE, 127));
		lightMapActual.put(charSet[lightMapActual.size()], aa);
		
		lightMapDummy.put(charSet[lightMapDummy.size()], "/resources/light/AB.gif");
		Circle ab = new Circle(150); ab.setEffect(new Shadow(BlurType.GAUSSIAN, Color.WHITE, 127));
		lightMapActual.put(charSet[lightMapActual.size()], ab);
		
		lightMapDummy.put(charSet[lightMapDummy.size()], "/resources/light/AC.gif");
		Circle ac = new Circle(300); ac.setEffect(new Shadow(BlurType.GAUSSIAN, Color.WHITE, 127));
		lightMapActual.put(charSet[lightMapActual.size()], ac);
		
		
		lightMapDummy.put(charSet[lightMapDummy.size()], "/resources/light/BA.gif");
		Circle ba = new Circle(75); ba.setEffect(new Shadow(BlurType.GAUSSIAN, Color.ORANGE, 127));
		lightMapActual.put(charSet[lightMapActual.size()], ba);
		
		lightMapDummy.put(charSet[lightMapDummy.size()], "/resources/light/BB.gif");
		Circle bb = new Circle(150); bb.setEffect(new Shadow(BlurType.GAUSSIAN, Color.ORANGE, 127));
		lightMapActual.put(charSet[lightMapActual.size()], bb);
		
		lightMapDummy.put(charSet[lightMapDummy.size()], "/resources/light/BC.gif");
		Circle bc = new Circle(300); bc.setEffect(new Shadow(BlurType.GAUSSIAN, Color.ORANGE, 127));
		lightMapActual.put(charSet[lightMapActual.size()], bc);
		
		
		lightMapDummy.put(charSet[lightMapDummy.size()], "/resources/light/CA.gif");
		Circle ca = new Circle(75); ca.setEffect(new Shadow(BlurType.GAUSSIAN, Color.GREEN, 127));
		lightMapActual.put(charSet[lightMapActual.size()], ca);
		
		lightMapDummy.put(charSet[lightMapDummy.size()], "/resources/light/CB.gif");
		Circle cb = new Circle(150); cb.setEffect(new Shadow(BlurType.GAUSSIAN, Color.GREEN, 127));
		lightMapActual.put(charSet[lightMapActual.size()], cb);
		
		lightMapDummy.put(charSet[lightMapDummy.size()], "/resources/light/CC.gif");
		Circle cc = new Circle(300); cc.setEffect(new Shadow(BlurType.GAUSSIAN, Color.GREEN, 127));
		lightMapActual.put(charSet[lightMapActual.size()], cc);
		
		
		lightMapDummy.put(charSet[lightMapDummy.size()], "/resources/light/DA.gif");
		Circle da = new Circle(75); da.setEffect(new Shadow(BlurType.GAUSSIAN, Color.PURPLE, 127));
		lightMapActual.put(charSet[lightMapActual.size()], da);
		
		lightMapDummy.put(charSet[lightMapDummy.size()], "/resources/light/DB.gif");
		Circle db = new Circle(150); db.setEffect(new Shadow(BlurType.GAUSSIAN, Color.PURPLE, 127));
		lightMapActual.put(charSet[lightMapActual.size()], db);
		
		lightMapDummy.put(charSet[lightMapDummy.size()], "/resources/light/DC.gif");
		Circle dc = new Circle(300); dc.setEffect(new Shadow(BlurType.GAUSSIAN, Color.PURPLE, 127));
		lightMapActual.put(charSet[lightMapActual.size()], dc);
		
	}

}
