package commons.eventScript;

import javafx.scene.image.*;

public class Script_Alert implements EventScript {

	private Type type;
	private String text;

	public Script_Alert(Type type, String text) {
		this.type = type;
		this.text = text;
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
		return 0;
	}

	@Override
	public String toString() {
		return "Alert " + type + " text: " + text;
	}

	private static final String path = "/resources/alertTypes/";

	public enum Type {

		Crystal(new Image(path + "crystal.png")),
		Exclamation(new Image(path + "exclamation.png")),
		Heart(new Image(path + "heart.png")),
		Leaf(new Image(path + "leaf.png")),
		Portal(new Image(path + "portal.png")),
		Question(new Image(path + "question.png")),
		Scroll(new Image(path + "scroll.png")),
		Skull(new Image(path + "skull.png")),
		Tentacle(new Image(path + "tentacle.png")),
		Time(new Image(path + "time.png"));

		public Image alertImage;

		private Type(Image alertImage) {
			this.alertImage = alertImage;
		}

		public Image getAlertImage() {
			return alertImage;
		}
	}
}
