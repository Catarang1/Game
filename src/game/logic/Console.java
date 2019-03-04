package game.logic;

import com.sun.org.apache.xerces.internal.jaxp.*;
import game.logic.Engine;
import java.util.*;
import javafx.application.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javax.xml.parsers.*;
import org.xml.sax.*;

public class Console {

	private static final Map<String, Map<String, Runnable>> commands = new HashMap<>();

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
		commands.get("alert").put("qlength", () -> Engine.console.write("Queue length: " + Engine.alertManager.queueLength(), INFO));
		commands.get("alert").put("test", () -> Engine.alertManager.queueAlert(new Alert(Alert.AlertType.Teleport, "alert test via console")));
		
		commands.put("timeset", new HashMap<>());
		commands.get("timeset").put("morning", () -> Engine.timekeeper.setTime(6, 59));
		commands.get("timeset").put("evening", () -> Engine.timekeeper.setTime(18, 59));
		commands.get("timeset").put("midday", () -> Engine.timekeeper.setTime(11, 59));
		commands.get("timeset").put("midnight", () -> Engine.timekeeper.setTime(23, 59));
		
		commands.put("loadmap", new HashMap<>());
		commands.get("loadmap").put("test", () -> Engine.boardLoader.loadTest());
		commands.get("loadmap").put("render", () -> Engine.renderActiveBoard());
		
		commands.put("fullscreen", new HashMap<>());
		commands.get("fullscreen").put("true", () -> Engine.window.getStage().setFullScreen(true));
		commands.get("fullscreen").put("false", () -> Engine.window.getStage().setFullScreen(false));

	}

	public void acceptInput(String input) {
		if (input.split(" ").length != 2) {
			Engine.console.write("Console line must follow $command $argument pattern"
				+ "with exception of load command that takes four digits as ID of map to be loaded", ERROR);
			return;
		}

		String command = input.split(" ")[0];
		String argument = input.split(" ")[1];

		if (command.equals("load")) {
			if (argument.matches("\\d{4}")) {
				try {
					Engine.console.write(command + " " + argument, ACCEPT);
					Engine.boardLoader.load(argument);
				} catch (Exception e) {
				} finally {
					return;
				}
			} else {
				Engine.console.write("Illegal argument for command load: " + argument, ERROR);
				return;
			}
		}
		
		if (commands.keySet().contains(command)) {
			Map<String, Runnable> validArgs = commands.get(command);
			if (validArgs.containsKey(argument)) {
				Engine.console.write(command + " " + argument, ACCEPT);
				commands.get(command).get(argument).run();
			} else {
				Engine.console.write("Illegal argument for command " + command + ": " + argument, ERROR);
			}
		} else {
			Engine.console.write("Unknown command " + command, ERROR);
		}
	}

	public void write(String text, Color color) {
		Text out = new Text(text + "\n");
		out.setFill(color);
		Engine.controller.getConsoleOutput().getChildren().add(out);
	}

	public void write(String text) {
		Text out = new Text(text + "\n");
		out.setFill(Color.WHITE);
		Platform.runLater(() -> {
			Engine.controller.getConsoleOutput().getChildren().add(out);
		});
	}

	private static void printKnownCommands() {
		for (String command : commands.keySet()) {
			Engine.console.write("Command " + command, INFO);
			for (String argument : commands.get(command).keySet())
				Engine.console.write("  * " + argument, INFO);
		}
	}

}
