package Model;

import java.io.File;

public class Directory {
	
	private File directory;
	private File[] roots;
	
	public Directory() {
		setDirctory(null);
	}
	
	public void setDirctory(File f)
	{	
		directory = f;
		if (f == null)
			roots = File.listRoots();
		else
			roots = f.listFiles();
	}
	
	public File getDirctory()
	{	
		return directory;
	}
	
	public File[] getRoots()
	{	
		return roots;
	}
}
