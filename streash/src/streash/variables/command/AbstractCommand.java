package streash.variables.command;

import java.util.ArrayList;
import java.util.List;

import streash.variables.Variable;

public abstract class AbstractCommand implements Command {
	
	private final int maxArg;
	private final List<Variable> args;
	private int compteur = 0;
	private String type;
	
	public AbstractCommand(int maxArg, String type) {
		if (maxArg < 0) {
			throw new IllegalArgumentException("Number of arg cant be negative");
		}
		this.maxArg = maxArg;
		this.args = new ArrayList<Variable>();
		this.type = type;
	}
	
	@Override
	public void takeArg(Variable v) {
		if (compteur == maxArg) {
			throw new IllegalStateException("This command (" + this.type + ") cannot take more argument : "+ compteur + "/" +this.maxArg);
		}
		this.compteur++;
		args.add(v);
	}
	
	@Override
	public void displayCommandArgs() {
		StringBuilder s = new StringBuilder();
		s.append("\n\n********* Args of " +this.type + " ***********\n");
		for(Variable arg : args) {
			s.append(arg.toString()+"\n");
		}
		s.append("*********************************");
		System.out.println(s.toString());
		
	}
	
	@Override
	public int nbArgs() {
		return this.maxArg;
	}
	
	public boolean isProcessable() {
		if (compteur < this.maxArg) {
			throw new IllegalStateException("Too few argument to call this command (" +this.type + ") : "+ compteur + "/" +this.maxArg);
		} else {
			return true;
		}
	}
	
	public Variable[] getArgsArray() {
		Variable[] toReturn = new Variable[this.nbArgs()];
		for (int i = 0; i < toReturn.length; i++)
			toReturn[i] = args.get(i);
		return toReturn;
	}
	
}
