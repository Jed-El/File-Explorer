package Control;

import java.io.File;
import java.util.TreeMap;

import Model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.GraphicView;
import View.View;

public class Controller implements ActionListener {
	private TreeMap<String, Command> commandTree;
	private View view;
	private Directory dirctory;
	
	public Controller() {
		dirctory = new Directory();
		view = new GraphicView(this);
		this.commandTree = new TreeMap<String, Command>() {};
		
		// Setting all ToolBar Commands
		TreeMap<String, Command> commands = new TreeMap<String, Command>() {};
		commands.put("Parent Directory", new Up());
		commands.put("Add Directory", new AddDir());
		commands.put("Add File", new AddFile());
		commands.put("Search", new Search());
		view.setToolBar(commands.keySet());
		this.commandTree.putAll(commands);
		
		// Setting all PopupMenu Commands
		commands = new TreeMap<String, Command>() {};
		commands.put("Remove", new Remove());
		commands.put("Rename", new Rename());
		commands.put("Open", new Open());
		view.setPopupMenu(commands.keySet());
		this.commandTree.putAll(commands);
		
		view.setDefaultCommand("Open");		// Setting Open as click Command		
		
		// Setting the files view
		view.setContent(dirctory.getDirctory(), dirctory.getRoots());
	}
	
	public void actionPerformed(ActionEvent event) {
		try {
			// Splitting path and Command key
			String[] str = event.getActionCommand().split("\n");
	        Command cmd = commandTree.get(str[1]);
	        // check input for Command.Execute
	        String inpt = null;
	        if (cmd.CheckInput())
	        	inpt = view.input(cmd.getMessage());
	        // check File for Command.Execute
	        File f;
	        if (str[0].equals("."))
	        	f = dirctory.getDirctory();
	        else
	        	f = new File(str[0]);
	        //  Executing command and resetting directory
	        dirctory.setDirctory(cmd.Execute(f, inpt)); 
		} catch(Exception ex) {
			view.output(ex.getMessage());
		}
		// Refreshing the view
        view.setContent(dirctory.getDirctory(), dirctory.getRoots());
    }
}
