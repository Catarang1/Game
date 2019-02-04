package editor.fxml;

import editor.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.scene.layout.*;
import javafx.scene.web.*;

/**
 * FXML Controller class
 *
 * @author Jan
 */
public class Controller_docView implements Initializable {

	@FXML private VBox wrapper;
	@FXML private WebView webview;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		webview.getEngine().load(DocumentationWindow.class.getResource("doc.html").toString());
	}	
	
}
