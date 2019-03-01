package game;

import java.util.*;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;

public class Console {

	private static HashMap<String, Task> knownCommands = new HashMap<>();

	static {
		
		Platform.runLater(() -> {
			Engine.console.write("Console initialized with response, enter 'console help' for full command/arg list", Color.AQUA);
		});
		
		StringBuilder helpTextBuilder = new StringBuilder();
		helpTextBuilder.append("Command 'console':\n");
		helpTextBuilder.append("*arg help: invokes basic help for console\n");
		helpTextBuilder.append("*arg clear: clears out console output\n");
		
		helpTextBuilder.append("\n\nCommand 'show':\n");
		helpTextBuilder.append("*arg title: shows titlebox with main name of map and subtitle\n");
		helpTextBuilder.append("*arg dialogline: shows dialog line element used while flashing dialog events\n");
		helpTextBuilder.append("*arg alertline: shows alert line element used in alert events, usually managed by AlertManager class\n");
		
		helpTextBuilder.append("\n\nCommand 'hide':\n");
		helpTextBuilder.append("*arg title: hides titlebox with main name of map and subtitle\n");
		helpTextBuilder.append("*arg dialogline: hides dialog line element used while flashing dialog events\n");
		helpTextBuilder.append("*arg alertline: hides alert line element used in alert events, usually managed by AlertManager class\n");
		helpTextBuilder.append("*arg console: hides whole console gui parent node\n");
		
		knownCommands.put("console", new Task("help", "clear") {
			@Override
			public void run() { 
				switch (getArgument()) {
					case "help":
						Engine.console.write(helpTextBuilder.toString(), Color.ORANGE);
						break;
					case "clear":
						Engine.controller.getConsoleOutput().getChildren().clear();
						break;
				}
			}
		});
		
		knownCommands.put("show", new Task("title", "dialogline", "alertline") {
			@Override
			public void run() {
				switch (getArgument()) {
					case "title": Engine.window.controller.getMapNameWrapper().setOpacity(1); break;
					case "dialogline": Engine.window.controller.getDialogText().setOpacity(1); break;
					case "alertline": Engine.window.controller.getAlertText().setOpacity(1); break;
				}
			}
		});
		
		knownCommands.put("hide", new Task("title", "dialogline", "alertline", "console") {
			@Override
			public void run() {
				switch (getArgument()) {
					case "title": Engine.window.controller.getMapNameWrapper().setOpacity(0); break;
					case "dialogline": Engine.window.controller.getDialogText().setOpacity(0); break;
					case "alertline": Engine.window.controller.getAlertText().setOpacity(0); break;
					case "console": Engine.window.controller.toggleConsole();
				}
			}
		});
		
		knownCommands.put("alert", new Task("addTest") {
			@Override
			public void run() {
				Engine.alertManager.put(new Alert(Alert.AlertType.Kill, "Test alert"));
			}
		});
		
	}

	public void acceptInput(String input) {
		if (input.split(" ").length <= 1||input.split(" ").length > 2) {
			Engine.console.write("All commands follow pattern: $command (space) $argument", Color.RED);
			return;
		}
		String command = input.split(" ")[0];
		if (knownCommands.containsKey(command)) {
			Task toBeExecuted = knownCommands.get(command);
			String argument = input.split(" ")[1];
			if (toBeExecuted.isValidArgument(argument)) {
				toBeExecuted.setArgument(argument);
				Engine.console.write(command +" "+ argument, Color.GREENYELLOW);
				toBeExecuted.run();
			} else Engine.console.write("Invalid Argument for Command " + command + " : " + argument, Color.RED);
		} else Engine.console.write("Unknown command: " + command, Color.RED);
	}

	public void write(String text, Color color) {
		Text out = new Text(text + "\n");
		out.setFill(color);
		Engine.controller.getConsoleOutput().getChildren().add(out);
		scrollDown();
	}
	
	private void scrollDown() {
		ScrollPane scroll = Engine.window.controller.getConsoleScroll();
		scroll.layout();
		scroll.setVvalue(1.0);
	}

}
