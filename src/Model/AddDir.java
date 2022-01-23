package Model;

import java.io.File;

public class AddDir extends Command{
	
	public AddDir() {
		super(true, "Insert new directory name:");
	}

	public File Execute(File f, String input) throws Exception {
        if (input != null)
        {
	    	File newDir = new File(f, input);
		    if(!newDir.mkdir())			//Creating the directory
		    	throw new Exception("Couldn’t create specified directory");	
        }
    	return f;	    	
	}
}
