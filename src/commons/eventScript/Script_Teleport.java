
package commons.eventScript;

// @author Jan

import commons.*;
import java.io.*;

 
public class Script_Teleport implements EventScript, Serializable{
	
	private Coords destination;

	public Script_Teleport(Coords destination) {
		this.destination = destination;
	}

	@Override
	public void runScript() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean isUnique() {
		return true;
	}

	@Override
	public int getOrder() {
		return 0;
	}
}
