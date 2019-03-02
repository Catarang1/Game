
package game.logic;

// @author Jan

import java.util.*;
import javafx.animation.*;
import javafx.animation.Animation.Status;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.util.*;
 
public class AlertManager {
	
	private List<Alert> queue = new ArrayList<>();
	private Timeline timer = new Timeline();
	private FadeTransition flashAlert = new FadeTransition(Duration.millis(1000));
	private Label alertline = Engine.window.controller.getAlertText();

	public AlertManager() {
		flashAlert.setFromValue(1);
		flashAlert.setToValue(0);
		flashAlert.setAutoReverse(false);
		flashAlert.setNode(alertline);
		flashAlert.setDelay(Duration.seconds(1));
		
		KeyFrame zeroOffset = new KeyFrame(Duration.millis(2100), e -> {});
		KeyFrame tick = new KeyFrame(Duration.ZERO, e -> {
			if (!queue.isEmpty()) showAlert(poll());
			else timer.stop();
		});
		timer.getKeyFrames().addAll(zeroOffset, tick);
		timer.setCycleCount(Timeline.INDEFINITE);
	}
	
	public void showAlert(Alert a) {
		alertline.setText(a.getText());
		alertline.setGraphic(new ImageView(a.getType().getAlertGraphics()));
		alertline.setOpacity(1);
		flashAlert.play();
	}
	
	public void put(Alert a) {
		queue.add(a);
		if (timer.getStatus() != Status.RUNNING) timer.play();
	}
	
	public Alert poll(){
		Alert x = queue.get(0);
		queue.remove(x);
		return x;
	}
	
}
