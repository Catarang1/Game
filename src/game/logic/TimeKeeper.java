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
		time.getKeyFrames().add(new KeyFrame(Duration.seconds(5), e -> tick()));
		time.setCycleCount(Timeline.INDEFINITE);
		time.play();
	}	

	public void setTime(int h, int m) {
		if (h < 0 || h > 23 || m < 0 || m > 59) {
			Engine.console.write("hour or minute input is not valid", Console.ERROR);
			return;
		}
		hours = h;
		minutes = m;
		updateShownTime();
		checkCycle();
	}
	
	public void setTime(String s) {
		if (s.matches("(\\d){2}:(\\d){2}")) {
			String[] parsedString = s.split(":");
			setTime(Integer.parseInt(parsedString[0]), Integer.parseInt(parsedString[1]));
		}
	}

	public void tick() {
		minutes++;
		if (minutes == 60) {
			minutes = 0;
			hours++;
			checkCycle();
		}

		if (hours == 24) {
			minutes = 0;
			hours = 0;
		}
		updateShownTime();
	}

	private void updateShownTime() {
		timeString.setValue(String.format("%02d:%02d", hours, minutes));
	}

	private void checkCycle() {
		if (isNightTime())
			Engine.controller.dawn();
		else
			Engine.controller.sunrise();
	}
	
	private boolean isNightTime(){
		return hours < 6 || hours > 18;
	}

}
