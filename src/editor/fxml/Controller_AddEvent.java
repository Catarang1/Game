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

	private Set<Flag> selectedPresent = new HashSet<>();
	private Set<Flag> selectedAbsent = new HashSet<>();

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
			int x = Integer.parseInt(triggerX.getText());
			int y = Integer.parseInt(triggerY.getText());
			boolean playerTriggered = triggeredByPlayer.isSelected();
			Coords trigger = new Coords(x, y);
			
			System.out.println("absent flags:");
			selectedAbsent.forEach(System.out::println);
			System.out.println("\n");
			System.out.println("present flags");
			selectedPresent.forEach(System.out::println);
			ArrayList<EventScript> events = new ArrayList<>();
			eventScriptList.getItems().forEach(t -> events.add(t));
			GameEvent created = new GameEvent(trigger, playerTriggered, selectedAbsent, selectedPresent, events);
			EditorWindow.getEventsToSave().add(created);
			
			reset();
			Stage stage = (Stage) missingFlagsWrapper.getScene().getWindow();
			stage.close();
		});
	}

	private void setupFlagLists() {
		
		EventHandler<ActionEvent> missingBoxAction = e -> {
			CheckBox changed = (CheckBox)e.getSource();
			if (changed.isSelected()) selectedAbsent.add(Flag.valueOf(changed.getText()));
			else selectedAbsent.remove(Flag.valueOf(changed.getText()));
		};
		
		EventHandler<ActionEvent> presentBoxAction = e -> {
			CheckBox changed = (CheckBox)e.getSource();
			if (changed.isSelected()) selectedPresent.add(Flag.valueOf(changed.getText()));
			else selectedPresent.remove(Flag.valueOf(changed.getText()));
		};
		
		for(Flag flag:Flag.values()) {
			CheckBox missingBox = new CheckBox(flag.name());
			CheckBox presentBox = new CheckBox(flag.name());
			
			missingBox.setOnAction(missingBoxAction);
			presentBox.setOnAction(presentBoxAction);
			
			missingFlagsWrapper.getChildren().add(missingBox);
			presentFlagsWrapper.getChildren().add(presentBox);
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
		
		selectedPresent.clear();
		selectedAbsent.clear();
		
		setupFlagLists();
		
		eventScriptList.getItems().clear();		
	}
}
