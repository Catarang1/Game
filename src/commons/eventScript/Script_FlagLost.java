
package commons.eventScript;

// @author Jan

import commons.*;
import java.io.*;

 
public class Script_FlagLost implements EventScript, Serializable{
	
	private Flag toBeLost;

	public Script_FlagLost(Flag toBeLost) {
		this.toBeLost = toBeLost;
	}

	@Override
	public void runScript() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean isUnique() {
		return false;
	}

	@Override
	public int getOrder() {
		return 2;
	}
	
	@Override
	public String toString() {
		return "Flag Lost " + toBeLost;
	}
}
