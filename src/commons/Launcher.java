package commons;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Jan
 */
public class Launcher extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		new FXMLWindow(getClass().getResource("splashGraph.fxml"), true).show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
