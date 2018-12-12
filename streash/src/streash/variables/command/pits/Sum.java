package streash.variables.command.pits;

import streash.variables.VarStream;
import streash.variables.Variable;
import streash.variables.command.AbstractCommand;

public class Sum extends AbstractCommand  {

	public Sum() {
		super(1);
	}

	@Override
	public Variable process() {
		if (super.isProcessable()) {
			Variable[] arr = super.getArgsArray();
			if (arr[0] instanceof VarStream) {
				VarStream s = (VarStream) arr[0];
				return s.sum();
			} else {
				throw new IllegalArgumentException("Args should be of type VarStream");
			}
		}
		return null;
	}

}
