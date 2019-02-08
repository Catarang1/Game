package commons;

import java.io.*;
import javafx.beans.property.*;
import javafx.beans.value.*;

/**
 *
 * @author Jan
 */
public enum Flag implements Serializable{
	
	Default,
	Flag_A,
	Flag_B,
	Flag_C,
	Flag_D,
	Flag_E;
	
	private final BooleanProperty isSelected = new SimpleBooleanProperty();

	public boolean isIsSelected() {
		return isSelected.get();
	}

	public void setIsSelected(boolean value) {
		isSelected.set(value);
	}

	public BooleanProperty isSelectedProperty() {
		return isSelected;
	}
	
	
	
	
}
