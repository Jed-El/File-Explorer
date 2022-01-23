package Model;

import java.io.File;
import java.util.LinkedList;

public class Search extends Command{
	
	public Search() {
		super(true, "Insert desired file/dirctory name (Go to parent dirctory of):");
	}

	public File Execute(File f, String input) throws Exception {
        if (input == null)
			return f;
        if (f == null)
	    	throw new Exception("Couldn’t search in current directory"); 		
		// Searching inside the directories with FIFO method
        LinkedList<File> fifo = new LinkedList<File>();
	    fifo.add(f);
        while (!fifo.isEmpty()) {
        	// Check inside the next dirctory
        	File current = fifo.removeFirst();
        	File[] inside_current = current.listFiles();
			if (inside_current == null) // Checking for null array
				continue;
        	for (File insidefile : inside_current)
        	{
        		// Check for a file/dirctory with the desired name
        		String name = insidefile.getName();
        		if ((name != null) && name.equals(input))
        			return current;
        		// If the new path is a dirctory, add it to the list
        		if (insidefile.isDirectory())
        			fifo.add(insidefile);
        	}
        }
	    throw new Exception("Couldn’t find specified file");
	}
}
