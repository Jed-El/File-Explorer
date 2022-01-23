package Model;

import java.io.File;

public class Up extends Command{
	
	public Up() {
		super(false, null);
	}

	public File Execute(File f, String input) throws Exception {
		if (f != null)
			return f.getParentFile();
		return f;	
	}
}
