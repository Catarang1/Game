package editor.fxml;

// @author Jan
import commons.*;
import java.io.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;

public class EventCell extends ListCell<GameEvent> {

	@FXML private Rectangle colorMark;
	@FXML private Label triggerXtext;
	@FXML private Label triggerYtext;
	@FXML private Label flagsPresentText;
	@FXML private Label flagsAbsentText;
	@FXML private Label noEventPointsText;
	@FXML private HBox root;
	@FXML private SVGPath triggerSource;
	
	private String svgPathMouse = "M17,0C7.6,0,0,7.6,0,17s7.6,17,17,17c9.4,0,17-7.6,17-17S26.4,0,17,0z M17.1,8.8c2.1-1.9,5.4-1.7,7.3,0.4l0.3,0.4l-1.1,1c-0.6-0.6-1.6-0.6-2.2-0.1l-0.9,0.8c-0.6,0.6-0.7,1.6-0.2,2.2l-1.3,1.1l-3.8-4.3L17.1,8.8z M23.4,12.4l-1,0.9c-0.4,0.3-0.9,0.3-1.2-0.1c-0.3-0.4-0.3-0.9,0.1-1.2l1-0.9c0.4-0.3,0.9-0.3,1.2,0.1h0C23.8,11.6,23.8,12.1,23.4,12.4z M16.9,25.2c-2.1,1.9-5.4,1.7-7.3-0.4l-1.1-1.2c-1.9-2.1-1.7-5.4,0.4-7.3l5.9-5.3l8.1,8.9L16.9,25.2z M25.1,17.8l-1.8,1.6l-3.8-4.2l1.3-1.1c0.6,0.6,1.6,0.6,2.2,0l0.9-0.8c0.6-0.6,0.7-1.5,0.2-2.2l1.1-1l0.3,0.4C27.5,12.6,27.3,15.9,25.1,17.8z";
	private String svgPathComputer = "M17.5,0.5c-9.4,0-17,7.6-17,17s7.6,17,17,17s17-7.6,17-17S26.9,0.5,17.5,0.5z M27.8,21.2c0,0.6-0.5,1.1-1.1,1.1H20v2.2h2.7c0.5,0,0.8,0.4,0.8,0.8v0.9c0,0.5-0.4,0.8-0.8,0.8H12.3c-0.5,0-0.8-0.4-0.8-0.8v-0.9c0-0.5,0.4-0.8,0.8-0.8h2.7v-2.2H8.3c-0.6,0-1.1-0.5-1.1-1.1v-11c0-0.9,0.7-1.7,1.7-1.7h17.8c0.6,0,1.1,0.5,1.1,1.1V21.2zM17.5,0.5c-9.4,0-17,7.6-17,17s7.6,17,17,17s17-7.6,17-17S26.9,0.5,17.5,0.5z M27.8,21.2c0,0.6-0.5,1.1-1.1,1.1H20v2.2h2.7c0.5,0,0.8,0.4,0.8,0.8v0.9c0,0.5-0.4,0.8-0.8,0.8H12.3c-0.5,0-0.8-0.4-0.8-0.8v-0.9c0-0.5,0.4-0.8,0.8-0.8h2.7v-2.2H8.3c-0.6,0-1.1-0.5-1.1-1.1v-11c0-0.9,0.7-1.7,1.7-1.7h17.8c0.6,0,1.1,0.5,1.1,1.1V21.2z";
	private FXMLLoader loader;
	

	@Override
	protected void updateItem(GameEvent item, boolean empty) {
		super.updateItem(item, empty);

		if (empty || item == null) {
			return;
		}

		setText(null);
		setGraphic(null);

		if (loader == null) {
			loader = new FXMLLoader(EventCell.class.getResource("eventCell.fxml"));
			loader.setController(this);
		}
		
		try {
			loader.load();
		} catch (IOException e) {
			System.err.println("failed to load Cell FXML");
		}
		
		triggerXtext.setText(item.getTriggerPosition().getPosX() + "");
		triggerYtext.setText(item.getTriggerPosition().getPosY() + "");
		flagsAbsentText.setText(item.getAbsentFlags().size() + "");
		flagsPresentText.setText(item.getPresentFlags().size() + "");
		noEventPointsText.setText(item.getEventPoints().size() + "");

		if (item.isPlayerTriggered()) triggerSource.setContent(svgPathMouse);
		else triggerSource.setContent(svgPathComputer);

		setGraphic(root);

	}
}
