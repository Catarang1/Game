
package commons.eventScript;

// @author Jan

import java.io.*;

 
public class Script_SwitchBoard implements EventScript, Serializable{
	
	private String boardCode;

	public Script_SwitchBoard(String boardCode) {
		this.boardCode = boardCode;
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
		return 11;
	}
}
