package View;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.io.File;
import java.util.Set;

import javax.swing.*;

public class GraphicView extends JFrame implements View {
	protected static final ImageIcon ICON_DISK = 
	    new ImageIcon("./icons/disk.png");
	// https://www.vecteezy.com/vector-art/105967-digital-driver-line-icon-vectors
	protected static final ImageIcon ICON_FOLDER = 
	    new ImageIcon("./icons/folder.png");
	// https://dryicons.com/icon/closed-folder-office-icon-9759
	protected static final ImageIcon ICON_FILE = 
	    new ImageIcon("./icons/file.png");
	// https://dryicons.com/icon/text-file-icon-5927
	
	protected JToolBar toolBar;
	protected Set<String> popupCommands;
	protected String clickCommand;
	protected ActionListener al;

	public GraphicView(ActionListener al) {
		// Building Frame and setting Location and Size
	    super("File Explorer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200,200,400,400);
        setResizable(false);
        // Setting values
        this.al = al;
        toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setRollover(true);
        setVisible(true);
	}
	
	// Setting all ToollBar Menu Commands
	public void setToolBar(Set<String> commands)
	{
		setMenu(toolBar, commands, ".");
	}
	
	// Setting the default Command for a click on icon
	public void setDefaultCommand(String command)
	{
		this.clickCommand = command;
	}
	
	// Setting all Popup Menu Commands
	public void setPopupMenu(Set<String> commands)
	{
		this.popupCommands = commands;
	}
	
	// Creating new JPopupMenu for a given path
	protected JPopupMenu createPopup(String path)
	{
		JPopupMenu pm = new JPopupMenu();
		setMenu(pm, popupCommands, path);
		return pm;
	}
	
	// Setting all the given Menu Commands
	protected void setMenu(JComponent jc,
			Set<String> commands, String path)
	{
		for(String cmd : commands) {
		    JButton button = new JButton(cmd);
		    button.setActionCommand(path + "\n" + cmd);
		    button.addActionListener(al);
		    jc.add(button);
        }
	}
	
	// Function for Controller input request
	public String input(String str)
	{
		return JOptionPane.showInputDialog(str);
	}
	
	// Function for Controller output
	public void output(String str)
	{
		JOptionPane.showMessageDialog(null, str);
	}

	// Refreshing the view
	public void setContent(File directory, File[] roots)
	{
		// Creating new contentPane and adding the ToolBar Menu
		JPanel contentPane = new JPanel(new BorderLayout());
		contentPane.add(toolBar, BorderLayout.PAGE_START);
		// Creating new inner Pane for icons
		JPanel innerPane = new JPanel();
        for (File f : roots)	// walk-through the roots inside the directory 
        {
        	String path = f.getPath();
        	// Setting name for file/directory icon
        	String name = f.getName();
        	if (name.equals(""))
        		name = path;
        	// Adding the file/directory
	        JButton imageButton = new JButton(name);
        	if (f.isDirectory()) {	// Choosing a fitting icon and ActionCommand
        		if (directory == null)
        			imageButton.setIcon(ICON_DISK);
        		else
        			imageButton.setIcon(ICON_FOLDER);
        		// Setting ActionCommand and ActionListener
        		imageButton.setActionCommand(path + "\n" + this.clickCommand);
        		imageButton.addActionListener(al);
        	} else
    			imageButton.setIcon(ICON_FILE);
        	// Setting file/directory icon values
	        imageButton.setHorizontalTextPosition(AbstractButton.CENTER);
	        imageButton.setVerticalTextPosition(AbstractButton.BOTTOM);
	        imageButton.setComponentPopupMenu(createPopup(path));
	        // Adding the file/directory to the inner Pane
	        innerPane.add(imageButton, BorderLayout.PAGE_END);
		}
        // Building ScrollPane to contain the inner Pane
        JScrollPane scrollPane = new JScrollPane(innerPane);
        scrollPane.setPreferredSize(this.getBounds().getSize());
        // Adding the ScrollPane to the content Pane
        contentPane.add(scrollPane, BorderLayout.WEST);
        // Setting the new content Pane and refreshing the frame
        setContentPane(contentPane);
        setVisible(true);
     }
}
