package game.logic;

import com.sun.org.apache.xerces.internal.jaxp.*;
import commons.*;
import game.logic.Engine;
import java.util.*;
import javafx.application.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javax.xml.parsers.*;
import org.xml.sax.*;

public class Console {

	private static final Map<String, RegExMap<String, Runnable>> commands = new HashMap<>();
	private static String activeArgument;

	public static final Color INFO = Color.CORNFLOWERBLUE;
	public static final Color ERROR = Color.CRIMSON;
	public static final Color ACCEPT = Color.LIMEGREEN;

	static {

		Platform.runLater(() -> {
			Engine.console.write("Console initialized with response, enter 'console help' for full command/arg list\n\n");
		});

		commands.put("console", new RegExMap<>());
		commands.get("console").put("clear", () -> Engine.controller.getConsoleOutput().getChildren().clear());
		commands.get("console").put("help", () -> printKnownCommands());

		commands.put("show", new RegExMap<>());
		commands.get("show").put("title", () -> Engine.controller.getMapNameWrapper().setOpacity(1));
		commands.get("show").put("alert", () -> Engine.controller.getAlertText().setOpacity(1));
		commands.get("show").put("dialog", () -> Engine.controller.getDialogText().setOpacity(1));

		commands.put("hide", new RegExMap<>());
		commands.get("hide").put("title", () -> Engine.controller.getMapNameWrapper().setOpacity(0));
		commands.get("hide").put("alert", () -> Engine.controller.getAlertText().setOpacity(0));
		commands.get("hide").put("dialog", () -> Engine.controller.getDialogText().setOpacity(0));
		commands.get("hide").put("console", () -> Engine.controller.toggleConsole());

		commands.put("alert", new RegExMap<>());
		commands.get("alert").put("status", () -> Engine.console.write(Engine.alertManager.timerStatus(), INFO));
		commands.get("alert").put("qlength", () -> Engine.console.write("Queue length: " + Engine.alertManager.queueLength(), INFO));
		commands.get("alert").put("test", () -> Engine.alertManager.queueAlert(new Alert(Alert.AlertType.Teleport, "alert test via console")));

		commands.put("settime", new RegExMap<>());
		commands.get("settime").put("(\\d){2}:(\\d){2}", () -> Engine.timekeeper.setTime(activeArgument));

		commands.put("loadmap", new RegExMap<>());
		commands.get("loadmap").put("test", () -> Engine.boardLoader.loadTest());
		commands.get("loadmap").put("render", () -> Engine.renderActiveBoard());

		commands.put("fullscreen", new RegExMap<>());
		commands.get("fullscreen").put("true", () -> Engine.window.getStage().setFullScreen(true));
		commands.get("fullscreen").put("false", () -> Engine.window.getStage().setFullScreen(false));

		commands.put("load", new RegExMap<>());
		commands.get("load").put("(\\d){4}", () -> Engine.boardLoader.load(activeArgument));

	}

	public void acceptInput(String input) {
		if (input.split(" ").length != 2) {
			Engine.console.write("Console line must follow $command $argument pattern"
					+ "with exception of load command that takes four digits as ID of map to be loaded", ERROR);
			return;
		}

		String command = input.split(" ")[0];
		String argument = input.split(" ")[1];

		if (commands.keySet().contains(command)) {
			Map<String, Runnable> validArgs = commands.get(command);
			if (validArgs.containsKey(argument)) {
				activeArgument = argument;
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
			Engine.console.write("Command " + "'" + command + "'", INFO);
			for (String argument : commands.get(command).keySet()) {
				Engine.console.write("  * " + argument, INFO);
			}
		}
	}

}
