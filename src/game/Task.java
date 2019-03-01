
package game;

// @author Jan

import java.util.*;

 
public abstract class Task implements Runnable{
	
	private String argument;
	private Set<String> validArgs = new HashSet<>();

	public Task(String... validArgs) {
		for (String validArg: validArgs) this.validArgs.add(validArg);
	}

	public String getArgument() {
		return argument;
	}

	public Set<String> getValidArgs() {
		return validArgs;
	}

	public void setArgument(String argument) {
		this.argument = argument;
	}
	
	public boolean isValidArgument(String argument) {
		return validArgs.contains(argument);
	}

	@Override
	public abstract void run();

	@Override
	public String toString() {
		return "Task{" + "argument=" + argument + ", validArgs=" + validArgs + '}';
	}
}
