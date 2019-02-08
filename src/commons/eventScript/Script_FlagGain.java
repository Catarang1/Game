
package commons.eventScript;

// @author Jan

import commons.*;
import java.io.*;

 
public class Script_FlagGain implements EventScript, Serializable{
	
	private Flag toBeGained;

	public Script_FlagGain(Flag toBeGained) {
		this.toBeGained = toBeGained;
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
		return 1;
	}
	
	

}
