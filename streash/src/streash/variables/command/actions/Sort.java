package streash.variables.command.actions;

import streash.variables.VarStream;
import streash.variables.Variable;
import streash.variables.command.AbstractCommand;

public class Sort extends AbstractCommand {

	public Sort() {
		super(1);
	}

	@Override
	public Variable process() {
		if (super.isProcessable()) {
			Variable[] arr = super.getArgsArray();
			if (arr[0] instanceof VarStream) {
				VarStream s = (VarStream) arr[0];
				s.sort();
				return s;
			} else {
				throw new IllegalArgumentException("Arg should be of type VarStream");
			}
		}
		return null;
	}

}
