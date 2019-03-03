package game.logic;

import game.logic.Engine;
import java.util.*;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;

public class Console {

	private static Map<String, Map<String, Runnable>> commands = new HashMap<>();
	
	
	public static final Color INFO = Color.CORNFLOWERBLUE;
	public static final Color ERROR = Color.CRIMSON;
	public static final Color ACCEPT = Color.LIMEGREEN;
	
	static {
		
		Platform.runLater(() -> {
			Engine.console.write("Console initialized with response, enter 'console help' for full command/arg list\n\n");
		});
		
		commands.put("console", new HashMap<>());
		commands.get("console").put("clear", () -> Engine.controller.getConsoleOutput().getChildren().clear());
		commands.get("console").put("help", () -> printKnownCommands());
		
		commands.put("show", new HashMap<>());
		commands.get("show").put("title", () -> Engine.controller.getMapNameWrapper().setOpacity(1));
		commands.get("show").put("alert", () -> Engine.controller.getAlertText().setOpacity(1));
		commands.get("show").put("dialog", () -> Engine.controller.getDialogText().setOpacity(1));
		
		commands.put("hide", new HashMap<>());
		commands.get("hide").put("title", () -> Engine.controller.getMapNameWrapper().setOpacity(0));
		commands.get("hide").put("alert", () -> Engine.controller.getAlertText().setOpacity(0));
		commands.get("hide").put("dialog", () -> Engine.controller.getDialogText().setOpacity(0));
		commands.get("hide").put("console", () -> Engine.controller.toggleConsole());
		
		commands.put("alert", new HashMap<>());
		commands.get("alert").put("status", () -> Engine.console.write(Engine.alertManager.timerStatus(), INFO));
		commands.get("alert").put("queue_length", () -> Engine.console.write("Queue length: " + Engine.alertManager.queueLength(), INFO));
		commands.get("alert").put("test", () -> Engine.alertManager.queueAlert(new Alert(Alert.AlertType.Teleport, "alert test via console")));
		
	}

	public void acceptInput(String input) {
		if (input.split(" ").length != 2) {
			Engine.console.write("Console line must follow $command $argument pattern", ERROR);
			return;
		}
		
		String command = input.split(" ")[0];
		String argument = input.split(" ")[1];
		
//		if (command.equals("load")&&argument.matches("\\d{4}")) {
//			try {
//				Engine.console.write(command + " " + argument, ACCEPT);
//				Engine.maploader.load(argument);
//			} 
//			catch (Exception e) {} 
//			finally {return;}
//		} else if (!argument.matches("\\d{4}")) {
//			Engine.console.write("Illegal argument for command load: " + argument, ERROR);
//			return;
//		}
		
		if (command.equals("setTime")&&argument.matches("\\d{2}:\\d{2}")) {
			try {
				Engine.console.write(command + " " + argument, ACCEPT);
				int h = Integer.valueOf(argument.split(":")[0]);
				int m = Integer.valueOf(argument.split(":")[1]);
				Engine.timekeeper.setTime(h, m);
			} 
			catch (Exception e) {} 
			finally {return;}
		} else if (!argument.matches("\\d{2}:\\d{2}")) {
			Engine.console.write("Illegal argument for command load: " + argument, ERROR);
			return;
		}
		
		if (commands.keySet().contains(command)) {
			Map<String, Runnable> validArgs = commands.get(command);
			if (validArgs.containsKey(argument)){
				Engine.console.write(command + " " + argument, ACCEPT);
				commands.get(command).get(argument).run();
			} else
				Engine.console.write("Illegal argument for command " + command + ": " + argument, ERROR);
		} else 
			Engine.console.write("Unknown command " + command, ERROR);
	}

	public void write(String text, Color color) {
		Text out = new Text(text + "\n");
		out.setFill(color);
		Engine.controller.getConsoleOutput().getChildren().add(out);
	}
	
	public void write(String text) {
		Text out = new Text(text + "\n");
		out.setFill(Color.WHITE);
		Engine.controller.getConsoleOutput().getChildren().add(out);
	}
	
	private static void printKnownCommands(){
		for (String command:commands.keySet()) {
			Engine.console.write("Command " + command, INFO);
			for (String argument:commands.get(command).keySet()) 
				Engine.console.write("  * " + argument, INFO);
		}		
	}

}
