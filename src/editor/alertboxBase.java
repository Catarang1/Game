package editor;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public abstract class alertboxBase extends VBox {

    protected final TextFlow textwrap;
    protected final Text text;
    protected final Button button;

    public alertboxBase() {

        textwrap = new TextFlow();
        text = new Text();
        button = new Button();

        setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setPrefWidth(500.0);
        setSpacing(20.0);

        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setText("erl;kgl;aerj glk;aerg");

        button.setDefaultButton(true);
        button.setMnemonicParsing(false);
        button.setText("Okay");
        setPadding(new Insets(20.0));

        textwrap.getChildren().add(text);
        getChildren().add(textwrap);
        getChildren().add(button);

    }
}
