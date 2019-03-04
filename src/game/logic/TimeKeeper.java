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
		if (h < 0 || h > 23 || m < 0 || m > 59) return;
		hours = h;
		minutes = m;
		updateShownTime();
		checkCycle();
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
		if (hours == 7) {
			Engine.controller.sunrise();
		} else if (hours == 19) {
			Engine.controller.dawn();
		}
	}

}
