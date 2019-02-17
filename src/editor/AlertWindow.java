
package editor;

// @author Jan

import javafx.fxml.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.text.*;
import javafx.stage.*;

 
public class AlertWindow {
	
	private VBox root;
	private Scene scene;
	private Stage stage;
	protected final TextFlow textwrap;
    protected final Text text;
    protected final Button button;

	public AlertWindow(String alertMessage) {
		root = new VBox();
        
		textwrap = new TextFlow();
        text = new Text(alertMessage);
        button = new Button("Okay");

        root.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
        root.setPrefWidth(500.0);
        root.setSpacing(20.0);

//      text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
//      text.setStrokeWidth(0.0);

        button.setDefaultButton(true);
//      button.setMnemonicParsing(false);
        root.setPadding(new Insets(20.0));

        textwrap.getChildren().add(text);
        root.getChildren().add(textwrap);
        root.getChildren().add(button);
		
		scene = new Scene(root);
		stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Alert");
		stage.show();
	}

	public AlertWindow(String title, String alertMessage) {
		this(alertMessage);
		stage.setTitle(title);
	}
	
	
	
	

}
