package editor.fxml;

import editor.*;
import java.net.URL;
import java.util.*;
import javafx.fxml.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;

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
	@FXML private MenuItem selectBackground;
	@FXML private MenuItem selectGround1;
	@FXML private MenuItem selectGround2;
	@FXML private MenuItem selectActor;
	@FXML private MenuItem selectObject1;
	@FXML private MenuItem selectObject2;
	@FXML private MenuItem selectCollision;
	@FXML private MenuItem selectLight;
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
	@FXML private Canvas ground1Layer1;
	@FXML private Canvas actorLayer;
	@FXML private Canvas object0Layer1;
	@FXML private Canvas object1Layer;
	@FXML private Canvas collisionLayer;
	@FXML private Pane lightLayer;
	@FXML private Pane interactiveLayer;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		System.out.println("hello");
		System.out.println(Arrays.toString(EditorLayer.values()));
		activeLayerComboBox.getItems().setAll(EditorLayer.values());
		activeLayerComboBox.getSelectionModel().selectFirst();
	}	
	
}
