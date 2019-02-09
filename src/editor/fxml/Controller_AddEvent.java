package editor.fxml;

import commons.*;
import commons.eventScript.*;
import editor.*;
import java.net.URL;
import java.util.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class Controller_AddEvent implements Initializable {

	@FXML private TextField triggerX;
	@FXML private TextField triggerY;
	@FXML private CheckBox triggeredByPlayer;
	@FXML private MenuButton addScriptMenu;
	@FXML private MenuItem addDialog;
	@FXML private MenuItem addSwitchBoard;
	@FXML private MenuItem addFlagGain;
	@FXML private MenuItem addFlagLost;
	@FXML private MenuItem addTeleport;
	@FXML private MenuItem addAlert;
	@FXML private Button addB;
	@FXML private Button cancelB;
	@FXML private ListView<EventScript> eventScriptList;
	@FXML private VBox missingFlagsWrapper;
	@FXML private VBox presentFlagsWrapper;
	@FXML private Button deleteScriptB;

	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		setupFlagLists();
		setupAddButton();
		setupDeleteButton();
		setupScriptAddition();
		setupCancelButton();		
	}

	private void setupAddButton() {
		addB.setOnAction(ev -> {
			int x = Integer.parseInt(triggerX.getText().trim());
			int y = Integer.parseInt(triggerY.getText().trim());
			boolean playerTriggered = triggeredByPlayer.isSelected();
			Coords trigger = new Coords(x, y);
			
			Set<Flag> selectedPresent = new HashSet<>();
			Set<Flag> selectedAbsent = new HashSet<>();
			
			for (int i = 0; i <presentFlagsWrapper.getChildren().size(); i++) {
				CheckBox a = (CheckBox)missingFlagsWrapper.getChildren().get(i);
				CheckBox b = (CheckBox)presentFlagsWrapper.getChildren().get(i);
				if (a.isSelected()) selectedAbsent.add(Flag.valueOf(a.getText()));
				if (b.isSelected()) selectedPresent.add(Flag.valueOf(b.getText()));
			}
			
			ArrayList<EventScript> events = new ArrayList<>();
			eventScriptList.getItems().forEach(t -> events.add(t));
			
			GameEvent created = new GameEvent(trigger, playerTriggered, selectedAbsent, selectedPresent, events);
			EditorWindow.addEvent(created);
			
			reset();
			Stage stage = (Stage) missingFlagsWrapper.getScene().getWindow();
			stage.close();
		});
	}

	private void setupFlagLists() {		
		for(Flag flag:Flag.values()) {
			missingFlagsWrapper.getChildren().add(new CheckBox(flag.name()));
			presentFlagsWrapper.getChildren().add(new CheckBox(flag.name()));
		}
	}

	private void setupScriptAddition() {
		addAlert.setOnAction(e -> new CreateEventScriptWindow(getLinkToFXML("alert.fxml")));
		addDialog.setOnAction(e -> new CreateEventScriptWindow(getLinkToFXML("dialog.fxml")));
		addFlagGain.setOnAction(e -> new CreateEventScriptWindow(getLinkToFXML("flagAdd.fxml")));
		addFlagLost.setOnAction(e -> new CreateEventScriptWindow(getLinkToFXML("flagRemove.fxml")));
		addTeleport.setOnAction(e -> new CreateEventScriptWindow(getLinkToFXML("teleport.fxml")));
		addSwitchBoard.setOnAction(e -> new CreateEventScriptWindow(getLinkToFXML("switchBoard.fxml")));
	}
	
	private URL getLinkToFXML(String name) {
		URL toReturn = EventScript.class.getResource(name);
		return toReturn;
	}
	
	public void addEventScript(EventScript e) {
		eventScriptList.getItems().add(e);		
	}

	private void setupDeleteButton() {
		deleteScriptB.setOnAction(e -> {
			EventScript selected = eventScriptList.getSelectionModel().getSelectedItem();
			eventScriptList.getItems().remove(selected);
		});
	}

	private void setupCancelButton() {
		cancelB.setOnAction(e -> AddEventWindow.close());
	}
	
	private void reset() {
		triggerX.clear();
		triggerY.clear();
		
		missingFlagsWrapper.getChildren().clear();
		presentFlagsWrapper.getChildren().clear();
		
		setupFlagLists();
		
		eventScriptList.getItems().clear();		
	}
}
