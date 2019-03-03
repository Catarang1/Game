
package game.logic;

import javafx.animation.*;
import javafx.beans.property.*;
import javafx.util.*;
 
public class TimeKeeper {
	
	private int hours = 0;
	private int minutes = 0;
	private final StringProperty timeString = new SimpleStringProperty("");
	private Timeline time = new Timeline();
	
	public TimeKeeper() {
		Engine.controller.getTimeText().textProperty().bind(timeString);
		time.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e -> {
			tick();
		}));
		time.setCycleCount(Timeline.INDEFINITE);
		time.play();
	}
	
	public void tick() {		
		minutes++;
		if (minutes == 60) {
			minutes = 0;
			hours++;
		}
		
		if (hours == 24) {
			minutes = 0;
			hours = 0;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(String.valueOf(hours).length()!=2 ? "0" + hours : hours);
		sb.append(":");
		sb.append(String.valueOf(minutes).length()!=2 ? "0" + minutes : minutes);
		
		timeString.setValue(sb.toString());
	}

}
