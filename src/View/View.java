package View;

import java.io.File;
import java.util.Set;

import Model.Command;

import java.awt.event.ActionListener;

public interface View {
	void output(String str);	// Function for Controller output
	String input(String str);	// Function for Controller input request
	void setContent(File directory, File[] roots);		// Refreshing the view
	void setToolBar(Set<String> commands);	// Setting all ToollBar Menu Commands
	void setPopupMenu(Set<String> commands);	// Setting all Popup Menu Commands
	void setDefaultCommand(String command);	// Setting the default Command for a click on icon
}
