package editor.fxml;

import commons.*;
import editor.*;
import java.net.URL;
import java.util.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import resources.*;

/**
 * FXML Controller class
 *
 * @author Jan
 */
public class Controller_Main implements Initializable {

	@FXML private MenuBar menuBar;
	@FXML private Menu gameBoardMenu;
	@FXML private MenuItem newOption;
	@FXML private Menu newFromTemplateMenu;
	@FXML private MenuItem loadOption;
	@FXML private MenuItem saveOption;
	@FXML private MenuItem quitOption;
	@FXML private Menu viewMenu;
	@FXML private CheckMenuItem showCollisionsOption;
	@FXML private CheckMenuItem showLightsOption;
	@FXML private CheckMenuItem showGridOption;
	@FXML private Menu selectMenu;
	@FXML public MenuItem selectBackground;
	@FXML public MenuItem selectGround1;
	@FXML public MenuItem selectGround2;
	@FXML public MenuItem selectActor;
	@FXML public MenuItem selectObject1;
	@FXML public MenuItem selectObject2;
	@FXML public MenuItem selectCollision;
	@FXML public MenuItem selectLight;
	@FXML private Menu eventsMenu;
	@FXML private MenuItem editEventsOption;
	@FXML private MenuItem deleteAllOption;
	@FXML private Menu helpMenu;
	@FXML private MenuItem showDocOption;
	@FXML private Text activeChar;
	@FXML private ImageView activeImage;
	@FXML private ComboBox<EditorLayer> activeLayerComboBox;
	@FXML private ScrollPane activeTilesWrapper;
	@FXML private TilePane activeTilesBox;
	@FXML private Canvas backgroundLayer;
	@FXML private Canvas ground0Layer;
	@FXML private Canvas actorLayer;
	@FXML private Canvas object1Layer;
	@FXML private Canvas collisionLayer;
	@FXML private Pane lightLayer;
	@FXML private Pane interactiveLayer;
	@FXML private Canvas gridLayer;
	@FXML private Canvas ground1Layer;
	@FXML private Canvas object0Layer;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		setupLayerSelection();
		setupShowMenu();
		setupGridLayer();
		setupSelectMenu();
		setupHelpMenu();
		setupInteractiveLayer();

	}

	protected void setupHelpMenu() {
		showDocOption.setOnAction((event) -> DocumentationWindow.get().show());
	}

	protected void setupSelectMenu() {
		selectBackground.setOnAction(e -> activeLayerComboBox.getSelectionModel().select(EditorLayer.Background));
		selectGround1.setOnAction(e -> activeLayerComboBox.getSelectionModel().select(EditorLayer.Ground1));
		selectGround2.setOnAction(e -> activeLayerComboBox.getSelectionModel().select(EditorLayer.Ground2));
		selectActor.setOnAction(e -> activeLayerComboBox.getSelectionModel().select(EditorLayer.Actor));
		selectObject1.setOnAction(e -> activeLayerComboBox.getSelectionModel().select(EditorLayer.Object1));
		selectObject2.setOnAction(e -> activeLayerComboBox.getSelectionModel().select(EditorLayer.Object2));
		selectCollision.setOnAction(e -> activeLayerComboBox.getSelectionModel().select(EditorLayer.Collision));
		selectLight.setOnAction(e -> activeLayerComboBox.getSelectionModel().select(EditorLayer.Light));
	}

	protected void setupGridLayer() {
		GraphicsContext brush = gridLayer.getGraphicsContext2D();
		brush.setFill(Color.GREY);
		for (int x = 0; x < gridLayer.getWidth() / 32; x++) {
			if (x != 0) {
				brush.fillRect(x * 32, 0, 1, gridLayer.getHeight());
			}
		}

		for (int y = 0; y < gridLayer.getHeight() / 32; y++) {
			if (y != 0) {
				brush.fillRect(0, y * 32, gridLayer.getWidth(), 1);
			}
		}
	}

	protected void setupShowMenu() {
		collisionLayer.visibleProperty().bind(showCollisionsOption.selectedProperty());
		lightLayer.visibleProperty().bind(showLightsOption.selectedProperty());
		gridLayer.visibleProperty().bind(showGridOption.selectedProperty());
	}

	protected void setupLayerSelection() {
		EventHandler<ActionEvent> layerSelect = (event) -> {
			activeTilesBox.getChildren().clear();
			EditorLayer selected = getSelectedLayer();

			for (Character c : selected.getLayerMap().keySet()) {
				ImageView iv = new ImageView(selected.getLayerMap().get(c));
				iv.setFitHeight(32);
				iv.setFitWidth(32);
				iv.setOnMouseClicked(ev -> {
					activeChar.setText(String.valueOf(c));
					activeImage.setImage(new Image(selected.getLayerMap().get(c)));
				});
				iv.setPickOnBounds(true);
				activeTilesBox.getChildren().add(iv);
			}

		};
		activeLayerComboBox.getItems().setAll(EditorLayer.values());
		activeLayerComboBox.getSelectionModel().selectFirst();
		activeLayerComboBox.setOnAction(layerSelect);
		layerSelect.handle(new ActionEvent());

	}

	protected void setupInteractiveLayer() {
		interactiveLayer.setOnMouseClicked(e -> {
			Coords clicked = getClickCoords(e);
			EditorLayer activeLayer = getSelectedLayer();
			System.out.println(clicked);
			int layer = activeLayer.ordinal();
			int y = clicked.getPosY();
			int x = clicked.getPosX();
			char active = activeChar.getText().charAt(0);

			if (e.getButton().equals(MouseButton.PRIMARY)) {

				EditorWindow.getBoard().getTiles()[layer][y][x] = active;
				EditorLayer.redrawLayer(activeLayer);
			} else if (e.getButton().equals(MouseButton.SECONDARY)) {
				switch (activeLayer) {
					case Background: EditorWindow.getBoard().getTiles()[layer][y][x] = '#'; break;
					default: EditorWindow.getBoard().getTiles()[layer][y][x] = '0'; break;
				}
			} else if (e.getButton().equals(MouseButton.MIDDLE) && activeLayer == EditorLayer.Background) {
				char[][] backgroundLayer = EditorWindow.getBoard().getTiles()[0];
				for (char[] row : backgroundLayer) Arrays.fill(row, active);
				EditorLayer.redrawLayer(EditorLayer.Background);
			}

		});
	}

	protected Coords getClickCoords(MouseEvent event) {
		int x = ((int) event.getX() - (int) event.getX() % 32) / 32;
		int y = ((int) event.getY() - (int) event.getY() % 32) / 32;
		return new Coords(x, y);
	}

	protected EditorLayer getSelectedLayer() {
		return activeLayerComboBox.getSelectionModel().getSelectedItem();
	}

	public void redrawBackground() {
		GraphicsContext brush = backgroundLayer.getGraphicsContext2D();
		for (int y = 0; y < EditorWindow.getBoard().getTiles()[0].length; y++) {
			for (int x = 0; x < EditorWindow.getBoard().getTiles()[0][0].length; x++) {
				char tile = EditorWindow.getBoard().getTiles()[getSelectedLayer().ordinal()][y][x];
				Image toDraw = new Image(DataMap.get().backgroundMap.get(tile));
				brush.drawImage(toDraw, x * 32, y * 32);
			}
		}
	}

	public void redrawGround0() {
		GraphicsContext brush = ground0Layer.getGraphicsContext2D();
		brush.clearRect(0, 0, brush.getCanvas().getWidth(), brush.getCanvas().getHeight());
		for (int y = 0; y < EditorWindow.getBoard().getTiles()[0].length; y++) {
			for (int x = 0; x < EditorWindow.getBoard().getTiles()[1][0].length; x++) {
				char tile = EditorWindow.getBoard().getTiles()[getSelectedLayer().ordinal()][y][x];
				if (DataMap.get().groundMap.keySet().contains(tile)) {
					Image toDraw = new Image(DataMap.get().groundMap.get(tile));
					brush.drawImage(toDraw, x * 32, y * 32);
				}
			}
		}
	}

	public void redrawGround1() {
		GraphicsContext brush = ground1Layer.getGraphicsContext2D();
		brush.clearRect(0, 0, brush.getCanvas().getWidth(), brush.getCanvas().getHeight());
		for (int y = 0; y < EditorWindow.getBoard().getTiles()[0].length; y++) {
			for (int x = 0; x < EditorWindow.getBoard().getTiles()[1][0].length; x++) {
				char tile = EditorWindow.getBoard().getTiles()[getSelectedLayer().ordinal()][y][x];
				if (DataMap.get().groundMap.keySet().contains(tile)) {
					Image toDraw = new Image(DataMap.get().groundMap.get(tile));
					brush.drawImage(toDraw, x * 32, y * 32);
				}
			}
		}
	}

	public void redrawObjects0() {
		GraphicsContext brush = object0Layer.getGraphicsContext2D();
		brush.clearRect(0, 0, brush.getCanvas().getWidth(), brush.getCanvas().getHeight());
		for (int y = 0; y < EditorWindow.getBoard().getTiles()[0].length; y++) {
			for (int x = 0; x < EditorWindow.getBoard().getTiles()[3][0].length; x++) {
				char tile = EditorWindow.getBoard().getTiles()[getSelectedLayer().ordinal()][y][x];
				if (DataMap.get().objectMap.keySet().contains(tile)) {
					Image toDraw = new Image(DataMap.get().objectMap.get(tile));
					brush.drawImage(toDraw, x * 32, y * 32);
				}
			}
		}
	}

	public void redrawObjects1() {
		GraphicsContext brush = object1Layer.getGraphicsContext2D();
		brush.clearRect(0, 0, brush.getCanvas().getWidth(), brush.getCanvas().getHeight());
		for (int y = 0; y < EditorWindow.getBoard().getTiles()[0].length; y++) {
			for (int x = 0; x < EditorWindow.getBoard().getTiles()[3][0].length; x++) {
				char tile = EditorWindow.getBoard().getTiles()[getSelectedLayer().ordinal()][y][x];
				if (DataMap.get().objectMap.keySet().contains(tile)) {
					Image toDraw = new Image(DataMap.get().objectMap.get(tile));
					brush.drawImage(toDraw, x * 32, y * 32);
				}
			}
		}
	}

	public void redrawCollisions() {
		GraphicsContext brush = collisionLayer.getGraphicsContext2D();
		brush.clearRect(0, 0, brush.getCanvas().getWidth(), brush.getCanvas().getHeight());
		for (int y = 0; y < EditorWindow.getBoard().getTiles()[0].length; y++) {
			for (int x = 0; x < EditorWindow.getBoard().getTiles()[4][0].length; x++) {
				char tile = EditorWindow.getBoard().getTiles()[getSelectedLayer().ordinal()][y][x];
				if (DataMap.get().collisionMap.keySet().contains(tile)) {
					Image toDraw = new Image(DataMap.get().collisionMap.get(tile));
					brush.drawImage(toDraw, x * 32, y * 32);
				}
			}
		}
	}

	public void redrawLights() {
		lightLayer.getChildren().clear();
		for (int y = 0; y < EditorWindow.getBoard().getTiles()[0].length; y++) {
			for (int x = 0; x < EditorWindow.getBoard().getTiles()[5][0].length; x++) {
				char tile = EditorWindow.getBoard().getTiles()[getSelectedLayer().ordinal()][y][x];
				if (DataMap.get().lightMapActual.keySet().contains(tile)) {
					Circle toDraw = new Circle();
					toDraw.setRadius(DataMap.get().lightMapActual.get(tile).getRadius());
					toDraw.setEffect(DataMap.get().lightMapActual.get(tile).getEffect());
					toDraw.setCenterX(x * 32 + 16);
					toDraw.setCenterY(y * 32 + 16);
					lightLayer.getChildren().add(toDraw);
				}
			}
		}
	}
//	
//	public void redrawAll() {
//		redrawBackground();
//		redrawGround();
//		redrawObjects();
//		redrawCollisions();
//		redrawLights();
//	}
//
}
