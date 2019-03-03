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
			Engine.console.write("Console initialized with response, enter 'console help' for full command/arg list\n\n", Color.AQUA);
		});
		
		commands.put("console", new HashMap<>());
		commands.get("console").put("clear", () -> Engine.controller.getConsoleOutput().getChildren().clear());
		commands.get("console").put("help", () -> printKnownCommands());
		
	}

	public void acceptInput(String input) {
		if (input.split(" ").length != 2) {
			Engine.console.write("Console line must follow $command $argument pattern", ERROR);
			return;
		}
		
		String command = input.split(" ")[0];
		String argument = input.split(" ")[1];
		
		if (command.equals("load")&&argument.matches("\\d{4}")) {
			try {
				Engine.console.write(command + " " + argument, ACCEPT);
				Engine.maploader.load(argument);
			} 
			catch (Exception e) {} 
			finally {return;}
		} else if (command.equals("load")&&!argument.matches("\\d{4}")) {
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
		scrollDown();
	}
	
	public void write(String text) {
		Text out = new Text(text + "\n");
		out.setFill(Color.WHITE);
		Engine.controller.getConsoleOutput().getChildren().add(out);
		scrollDown();
	}
	
	private void scrollDown() {
		ScrollPane scroll = Engine.window.controller.getConsoleScroll();
		scroll.layout();
		scroll.setVvalue(1.0);
	}
	
	private static void printKnownCommands(){
		for (String command:commands.keySet()) {
			Engine.console.write("Command " + command, INFO);
			for (String argument:commands.get(command).keySet()) 
				Engine.console.write("  * " + argument, INFO);
			
		}		
	}

}
