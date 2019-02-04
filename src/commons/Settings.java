package commons;

// @author Jan
import commons.eventScript.EventScript;
import java.awt.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.logging.*;
import org.reflections.*;

public class Settings {

	public static int screenWidth;
	public static int screenHeight;
	public static HashSet<String> flags = new HashSet<>();
	public static HashMap<String, Class<? extends EventScript>> eventScripts = new HashMap<>();
	
	static {

		Reflections reflections = new Reflections("commons.eventScript");
		Set<Class<? extends EventScript>> scriptsToParse = reflections.getSubTypesOf(EventScript.class);
		
		for (Class<? extends EventScript> c : scriptsToParse) {
			try {
				String s = (String) c.getMethod("getMenuBoxName", (Class<?>[]) null).invoke(null, (Object[]) null);
				eventScripts.put(s, c);

			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
				Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		flags.add("Default");
		flags.add("isStarving");
		flags.add("isAlive");

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenHeight = screenSize.height;
		System.out.println(screenHeight);
		screenWidth = screenSize.width;
		System.out.println(screenWidth);
		
	}

}
