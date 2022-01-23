package Model;

import java.io.File;

public class Rename extends Command{
	
	public Rename() {
		super(true, "Insert new name:");
	}

	public File Execute(File f, String input) throws Exception {
        if (input != null)
        {
			File newPath = new File(f.getParent(), input);
			if(!f.renameTo(newPath))
		        throw new Exception("Couldn’t rename specified path");	
        }
		return f.getParentFile();	
	}
}
