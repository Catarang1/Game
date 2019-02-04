package commons;

// @author Jan

import java.io.*;
import java.util.*;
 
public class Board implements Serializable {
	
	private static final long serialVersionUID = 11L;
	private String code;
	private String title;
	private String subTitle;
	private DayCycle cycle;	public enum DayCycle {DARK, CYCLE;}	
	
	private char[][][] description = new char[8][22][40];
	private HashMap<Coords, List<GameEvent>> events = new HashMap<>();
	
	public Board() {
		title = "Default Title";
		subTitle = "Default SubTitle";
		cycle = DayCycle.CYCLE;
		for (char[] row: description[0]) Arrays.fill(row, '#');
		for (char[] row: description[1]) Arrays.fill(row, '0');
		for (char[] row: description[2]) Arrays.fill(row, '0');
		for (char[] row: description[3]) Arrays.fill(row, '0');
		for (char[] row: description[4]) Arrays.fill(row, '0');
		for (char[] row: description[5]) Arrays.fill(row, '0');	
		for (char[] row: description[6]) Arrays.fill(row, '0');	
		for (char[] row: description[7]) Arrays.fill(row, '0');	
	}
	
	public char[][] getBackgroundLayer()	{return description[0];}
	public char[][] getGroundLayer()		{return description[1];}
	public char[][] getGroundLayer2()		{return description[2];}
	public char[][] getActorLayer()			{return description[3];}
	public char[][] getObjectLayer()		{return description[4];}
	public char[][] getObjectLayer2()		{return description[5];}
	public char[][] getCollisionLayer()		{return description[6];}
	public char[][] getLightLayer()			{return description[7];}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Map ").append(title).append("\n");
		sb.append(subTitle).append("\n");
		sb.append("code: ").append(code).append("\n");
		
		sb.append("\n\n");
		sb.append("Background Layer: \n");
		for (char[] row:getBackgroundLayer()) sb.append(Arrays.toString(row)).append("\n");
		
		sb.append("\n\n");
		sb.append("Ground Layer: \n");
		for (char[] row:getGroundLayer()) sb.append(Arrays.toString(row)).append("\n");
		
		sb.append("\n\n");
		sb.append("Actor Layer: \n");
		for (char[] row:getActorLayer()) sb.append(Arrays.toString(row)).append("\n");
		
		sb.append("\n\n");
		sb.append("Object Layer: \n");
		for (char[] row:getObjectLayer()) sb.append(Arrays.toString(row)).append("\n");
		
		sb.append("\n\n");
		sb.append("Collision Layer: \n");
		for (char[] row:getCollisionLayer()) sb.append(Arrays.toString(row)).append("\n");
		
		sb.append("\n\n");
		sb.append("Light Layer: \n");
		for (char[] row:getLightLayer()) sb.append(Arrays.toString(row)).append("\n");
		return sb.toString();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		if (code.matches("[0-9]{4}")) this.code = code;
		else {
		/* TODO fix
			
			show some kind of error in Editor (game engine does not assign board codes)
			
		*/
		
			
		}
	}

	public String getName() {
		return title;
	}

	public void setName(String name) {
		this.title = name;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public DayCycle getCycle() {
		return cycle;
	}

	public char[][][] getDescription() {
		return description;
	}

	public HashMap<Coords, List<GameEvent>> getEvents() {
		return events;
	}

	public void setCycle(DayCycle cycle) {
		this.cycle = cycle;
	}
}
