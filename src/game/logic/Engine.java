
package game.logic;

import game.logic.actor.Actor;
import commons.*;
import editor.*;
import game.gui.GameGUIController;
import game.gui.GameWindow;
import java.util.*;
import javafx.scene.canvas.*;
import javafx.scene.image.*;
import javafx.scene.shape.*;
import resources.*;

 
public class Engine {
	
	public static GameWindow window = new GameWindow();
	public static GameGUIController controller = window.controller;
	
	public static InputSlot inputAction = new InputSlot();
	public static InputSlot inputDirection = new InputSlot();
	
	public static List<Actor> actors = new ArrayList<>();
	public static Console console = new Console();
	public static Board activeBoard;
	public static AlertManager alertManager = new AlertManager();
	public static BoardLoader boardLoader = new BoardLoader();
	public static TimeKeeper timekeeper = new TimeKeeper();

	public static void setActiveBoard(Board activeBoard) {
		Engine.activeBoard = activeBoard;
	}
	
	public static void renderActiveBoard() {
		clearCanvases();
		renderBackLayers();
		actors.clear();
		spawnActors();
		renderFrontLayers();
		renderLights();
	}

	private static void renderBackLayers() {
		renderBackground();
		renderGround0();
		renderGround1();
	}

	private static void spawnActors() {
		//TODO impl method
	}

	private static void renderFrontLayers() {
		renderObjects0();
		renderObjects1();
	}

	public static void renderBackground() {
		GraphicsContext brush = controller.getBackLayerBrush();
		for (int y = 0; y < activeBoard.getTiles()[0].length; y++) {
			for (int x = 0; x < activeBoard.getTiles()[0][0].length; x++) {
				char tile = activeBoard.getTiles()[0][y][x];
				Image toDraw = new Image(DataMap.get().backgroundMap.get(tile));
				brush.drawImage(toDraw, x * 32, y * 32);
			}
		}
		Engine.console.write("render background done!");
	}

	private static void renderGround0() {
		GraphicsContext brush = controller.getBackLayerBrush();
		for (int y = 0; y < activeBoard.getTiles()[0].length; y++) {
			for (int x = 0; x < activeBoard.getTiles()[1][0].length; x++) {
				char tile = activeBoard.getTiles()[1][y][x];
				if (DataMap.get().groundMap.keySet().contains(tile)) {
					Image toDraw = new Image(DataMap.get().groundMap.get(tile));
					brush.drawImage(toDraw, x * 32, y * 32);
				}
			}
		}
	}

	private static void renderGround1() {
		GraphicsContext brush = controller.getBackLayerBrush();
		for (int y = 0; y < activeBoard.getTiles()[0].length; y++) {
			for (int x = 0; x < activeBoard.getTiles()[2][0].length; x++) {
				char tile = activeBoard.getTiles()[2][y][x];
				if (DataMap.get().groundMap.keySet().contains(tile)) {
					Image toDraw = new Image(DataMap.get().groundMap.get(tile));
					brush.drawImage(toDraw, x * 32, y * 32);
				}
			}
		}
	}

	private static void renderObjects0() {
		GraphicsContext brush = controller.getFrontLayerBrush();
		for (int y = 0; y < activeBoard.getTiles()[0].length; y++) {
			for (int x = 0; x < activeBoard.getTiles()[4][0].length; x++) {
				char tile = activeBoard.getTiles()[4][y][x];
				if (DataMap.get().objectMap.keySet().contains(tile)) {
					Image toDraw = new Image(DataMap.get().objectMap.get(tile));
					brush.drawImage(toDraw, x * 32, y * 32);
				}
			}
		}
	}
	
	private static void renderObjects1() {
		GraphicsContext brush = controller.getFrontLayerBrush();
		for (int y = 0; y < activeBoard.getTiles()[0].length; y++) {
			for (int x = 0; x < activeBoard.getTiles()[5][0].length; x++) {
				char tile = activeBoard.getTiles()[5][y][x];
				if (DataMap.get().objectMap.keySet().contains(tile)) {
					Image toDraw = new Image(DataMap.get().objectMap.get(tile));
					brush.drawImage(toDraw, x * 32, y * 32);
				}
			}
		}
	}

	private static void renderLights() {
		controller.getLightLayer().getChildren().clear();
		for (int y = 0; y < activeBoard.getTiles()[0].length; y++) {
			for (int x = 0; x < activeBoard.getTiles()[7][0].length; x++) {
				char tile = activeBoard.getTiles()[7][y][x];
				if (DataMap.get().lightMapActual.keySet().contains(tile)) {
					Circle toDraw = new Circle();
					toDraw.setRadius(DataMap.get().lightMapActual.get(tile).getRadius());
					toDraw.setEffect(DataMap.get().lightMapActual.get(tile).getEffect());
					toDraw.setCenterX(x * 32 + 16);
					toDraw.setCenterY(y * 32 + 16);
					controller.getLightLayer().getChildren().add(toDraw);
				}
			}
		}
	}

	private static void clearCanvases() {
		controller.getBackLayerBrush().clearRect(0, 0, 1280, 704);
		controller.getActorLayerBrush().clearRect(0, 0, 1280, 704);
		controller.getFrontLayerBrush().clearRect(0, 0, 1280, 704);
		controller.getLightLayer().getChildren().clear();
	}

	
	

}
