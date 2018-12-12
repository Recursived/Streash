package streash.variables.command;

import streash.variables.Variable;

public interface Command {
	
	Variable process();
	void displayCommandArgs();
	void takeArg(Variable v);
	int nbArgs();
	
}
