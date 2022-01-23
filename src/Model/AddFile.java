package Model;

import java.io.File;

public class AddFile extends Command{
	
	public AddFile() {
		super(true, "Insert new file name:");
	}

	public File Execute(File f, String input) throws Exception {
        if (input != null)
        {
        	File newFile = new File(f, input);
    	    if(!newFile.createNewFile())			//Creating the file
    	    	throw new Exception("Couldn’t create specified file");		
        }
		return f; 	    	
	}
}
