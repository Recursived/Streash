package streash.variables.command;

import java.util.ArrayList;
import java.util.List;

import streash.variables.Variable;

public abstract class AbstractCommand implements Command {
	
	private final int maxArg;
	private final List<Variable> args;
	private static int compteur = 0;
	
	public AbstractCommand(int maxArg) {
		if (maxArg < 0) {
			throw new IllegalArgumentException("Number of arg cant be negative");
		}
		this.maxArg = maxArg;
		this.args = new ArrayList<Variable>();
	}
	
	@Override
	public void takeArg(Variable v) {
		if (compteur == maxArg) {
			throw new IllegalStateException("This command cannot take more argument");
		}
		compteur++;
		args.add(v);
	}
	
	@Override
	public void displayCommandArgs() {
		StringBuilder s = new StringBuilder();
		for(Variable arg : args) {
			s.append(arg.toString()+"\n");
		}
		System.out.println(s.toString());
	}
	
	@Override
	public int nbArgs() {
		return this.maxArg;
	}
	
	public boolean isProcessable() {
		if (compteur < this.maxArg) {
			throw new IllegalStateException("Too few argument to call this command");
		} else {
			return true;
		}
	}
	
	public Variable[] getArgsArray() {
		return (Variable[]) this.args.toArray();
	}
	
}
