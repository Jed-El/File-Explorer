package Model;

import java.io.File;

public class Open extends Command{
	
	public Open() {
		super(false, null);
	}

	public File Execute(File f, String input) throws Exception {
	    if(f.isDirectory())			//Checking for directory
	    	return f;
	    throw new Exception("Open command implemented only for a directory");			    	
	}
}
