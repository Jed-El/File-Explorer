package Model;

import java.io.File;

public class Remove extends Command{
	
	public Remove() {
		super(false, null);
	}

	public File Execute(File f, String input) throws Exception {
	    if(f.delete())
	    	return f.getParentFile();
	    throw new Exception("Couldn’t remove specified path");			    	
	}
}
