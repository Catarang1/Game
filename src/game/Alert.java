package game;

// @author Jan

import javafx.scene.image.*;

public class Alert {

	public enum AlertType {
		Herb(1, new Image("/resources/alertTypes/leaf.png")),
		Crystal(1, new Image("/resources/alertTypes/crystal.png")),
		Teleport(1, new Image("/resources/alertTypes/portal.png")),
		Exclamation(1, new Image("/resources/alertTypes/exclamation.png")),
		Kill(1, new Image("/resources/alertTypes/skull.png")),
		Health(1, new Image("/resources/alertTypes/heart.png")),
		Beast(1, new Image("/resources/alertTypes/tentacle.png")),
		Stopwatch(1, new Image("/resources/alertTypes/time.png")),
		Question(1, new Image("/resources/alertTypes/question.png"));

		private Image alertGraphics;
		private int order;

		private AlertType(int order, Image alertGraphics) {
			this.alertGraphics = alertGraphics;
			this.order = order;
		}

		public Image getAlertGraphics() {
			return alertGraphics;
		}

		public int getOrder() {
			return order;
		}
	}

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

}
