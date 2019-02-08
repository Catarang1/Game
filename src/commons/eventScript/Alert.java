
package commons.eventScript;

// @author Jan

import editor.fxml.*;
import java.io.*;
import java.util.logging.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.stage.*;

 
public class Alert implements EventScript{
	
	private static final String path = "/resources/alertTypes/";
	private Type type;
	private String text;

	public Alert(Type type, String text) {
		this.type = type;
		this.text = text;
	}

	public static void showEditorWindow() {
		new AddAlertWindow().show();
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
	
	private static class AddAlertWindow{
		static FXMLLoader loader = new FXMLLoader();
		static Controller_AddAlert controller;
		static Parent root;
		static Scene scene;
		static Stage stage = new Stage();
		
		public AddAlertWindow() {
			loader = new FXMLLoader(Controller_AddEvent.class.getResource("editor_create_alert.fxml"));
			try {
				root = loader.load();
			} catch (IOException ex) {
				Logger.getLogger(Alert.class.getName()).log(Level.SEVERE, null, ex);
			}
			controller = loader.getController();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Add Alert Script");
			stage.show();
		}
		
		public void show() {
			stage.show();
		}
		
	}
	
	public enum Type{
		
		Crystal			(new Image(path + "crystal.png")),
		Exclamation		(new Image(path + "exclamation.png")),
		Heart			(new Image(path + "heart.png")),
		Leaf			(new Image(path + "leaf.png")),
		Portal			(new Image(path + "portal.png")),
		Question		(new Image(path + "question.png")),
		Scroll			(new Image(path + "scroll.png")),
		Skull			(new Image(path + "skull.png")),
		Tentacle		(new Image(path + "tentacle.png")),
		Time			(new Image(path + "time.png"));
		
		public Image alertImage;
		
		private Type(Image alertImage) {
			this.alertImage = alertImage;
		}

		public Image getAlertImage() {
			return alertImage;
		}
		
	}

}
