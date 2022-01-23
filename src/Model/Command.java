package Model;
import java.io.File;

public abstract class Command {
	private boolean check_inpt; // A value that says if the Command requires input
	private String input_message; // A message String for input request
	public Command(boolean c, String im) { // Set Values in Builder
		this.check_inpt = c;
		this.input_message = im;
		}
	public boolean CheckInput() { return check_inpt; } // Function that checks if the Command requires Input
	public String getMessage() { return input_message; } // Function that returns the message
	public abstract File Execute(File f, String input) throws Exception; // Function that will Execute The Command
}