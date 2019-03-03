package game.logic;

// @author Jan

import javafx.scene.image.*;

public class Alert {

	private AlertType type;
	private String text;

	public Alert(AlertType type, String text) {
		this.type = type;
		this.text = text;
	}

	public AlertType getType() {
		return type;
	}

	public String getText() {
		return text;
	}
	
	public enum AlertType {
		Herb(			new Image("/resources/alertTypes/leaf.png")),
		Crystal(		new Image("/resources/alertTypes/crystal.png")),
		Teleport(		new Image("/resources/alertTypes/portal.png")),
		Exclamation(	new Image("/resources/alertTypes/exclamation.png")),
		Kill(			new Image("/resources/alertTypes/skull.png")),
		Health(			new Image("/resources/alertTypes/heart.png")),
		Beast(			new Image("/resources/alertTypes/tentacle.png")),
		Stopwatch(		new Image("/resources/alertTypes/time.png")),
		Question(		new Image("/resources/alertTypes/question.png"));

		private Image alertGraphics;

		private AlertType(Image alertGraphics) {
			this.alertGraphics = alertGraphics;
		}

		public Image getAlertGraphics() {
			return alertGraphics;
		}
	}
}
