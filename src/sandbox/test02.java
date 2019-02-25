package sandbox;

import javafx.application.*;
import javafx.concurrent.*;
import javafx.stage.Stage;
import javafx.util.*;

public class test02 extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		ScheduledService<Void> ss= new ScheduledService<Void>() {
			@Override
			protected Task<Void> createTask() {
				return new Task<Void>() {
					@Override
					protected Void call() throws Exception {
						System.out.println("hello");
						return null;
					}
				};
			}
		};
		
		ss.setPeriod(Duration.seconds(1));
		ss.start();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
