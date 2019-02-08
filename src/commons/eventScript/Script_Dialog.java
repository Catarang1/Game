
package commons.eventScript;

// @author Jan

import java.io.*;

 
public class Script_Dialog implements EventScript, Serializable{
	
	private String[] dialogLines;

	public Script_Dialog(String ... dialogLines) {
		this.dialogLines = dialogLines;
	}

	@Override
	public void runScript() {
//		int scriptTimer = 0;
//		Engine.getGameLayout().dialogText.setOpacity(1);
//		Timeline dialogScript = new Timeline();
//		for (String line : dialogLines) {
//			KeyFrame k = new KeyFrame(Duration.seconds(scriptTimer), (event) -> {
//				Engine.getGameLayout().dialogText.setText(line);
//			});
//			dialogScript.getKeyFrames().add(k);
//			scriptTimer += 2;
//		}
//		KeyFrame k = new KeyFrame(Duration.seconds(scriptTimer), (event) -> {
//			Engine.getGameLayout().dialogText.setText("");
//		});
//		dialogScript.getKeyFrames().add(k);
//		dialogScript.play();
	}

	@Override
	public boolean isUnique() {
		return true;
	}

	@Override
	public int getOrder() {
		return 10;
	}
	
	

}
