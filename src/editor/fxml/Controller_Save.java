package editor.fxml;

import commons.*;
import editor.*;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.*;

public class Controller_Save implements Initializable {

	@FXML public TextField boardTitleField;
	@FXML public TextField boardSubTitleField;
	@FXML public CheckBox cycle;
	@FXML public Button cancelB;
	@FXML public Button saveB;
	@FXML public TextField boardCodeField;

	private static Border failBorder;
	private static Border successBorder;

	static {
		BorderStroke failStroke = new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3), BorderWidths.DEFAULT);
		BorderStroke successStroke = new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, new CornerRadii(3), BorderWidths.DEFAULT);
		failBorder = new Border(failStroke);
		successBorder = new Border(successStroke);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		boardCodeField.setOnKeyReleased(e -> {
			if (codeValid()) {
				boardCodeField.setBorder(successBorder);
			} else {
				boardCodeField.setBorder(failBorder);
			}
			checkSaveButtonElighibility();
		});

		boardTitleField.setOnKeyReleased(e -> {
			if (titleValid()) {
				boardTitleField.setBorder(successBorder);
			} else {
				boardTitleField.setBorder(failBorder);
			}
			checkSaveButtonElighibility();
		});

		boardSubTitleField.setOnKeyReleased(e -> {
			if (subTitleValid()) {
				boardSubTitleField.setBorder(successBorder);
			} else {
				boardSubTitleField.setBorder(failBorder);
			}
			checkSaveButtonElighibility();
		});

		cancelB.setOnAction(e -> {
			SaveWindow.close();
		});

		saveB.setOnAction(e -> {
			Board board = EditorWindow.getBoard();
			board.setCode(boardCodeField.getText());
			board.setTitle(boardTitleField.getText());
			board.setSubTitle(boardSubTitleField.getText());
			if (cycle.isSelected()) board.setCycle(Board.DayCycle.CYCLE);
			else board.setCycle(Board.DayCycle.DARK);

			try {
				File directory = new File("boards/");
				if (!directory.exists()) directory.mkdirs();
				FileOutputStream fos = new FileOutputStream("boards/" + EditorWindow.getBoard().getCode() + ".map");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(EditorWindow.getBoard());
				fos.flush();
				fos.close();
			} catch (IOException ex) {
				System.err.println("failed to save");
			}
			SaveWindow.close();
		});
	}

	private void checkSaveButtonElighibility() {
		if (infoValid()) saveB.setDisable(false);
		else saveB.setDisable(true);
	}

	private boolean codeValid() {
		String code = boardCodeField.getText().trim();
		return code.matches("[\\d]{4}");
	}

	private boolean titleValid() {
		String title = boardTitleField.getText();
		return !title.isEmpty() && title.length() < 25;
	}

	private boolean subTitleValid() {
		String subtitle = boardSubTitleField.getText();
		return !subtitle.isEmpty() && subtitle.length() < 50;
	}

	private boolean infoValid() {
		try {
			if (!boardCodeField.getBorder().equals(successBorder)) {
				return false;
			}
			if (!boardTitleField.getBorder().equals(successBorder)) {
				return false;
			}
			if (!boardSubTitleField.getBorder().equals(successBorder)) {
				return false;
			}
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}

		return true;
	}

	public void reset() {
		boardCodeField.requestFocus();
		boardCodeField.clear();
		boardTitleField.clear();
		boardSubTitleField.clear();
		boardCodeField.setBorder(Border.EMPTY);
		boardTitleField.setBorder(Border.EMPTY);
		boardSubTitleField.setBorder(Border.EMPTY);
	}
}
