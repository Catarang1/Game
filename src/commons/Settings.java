package commons;

import java.awt.*;

public class Settings {

	public static int screenWidth;
	public static int screenHeight;
	
	static {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenHeight = screenSize.height;
		System.out.println(screenHeight);
		screenWidth = screenSize.width;
		System.out.println(screenWidth);
		
	}

}
