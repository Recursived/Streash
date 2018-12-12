package streash.variables.command.pits;

import streash.variables.VarStream;
import streash.variables.Variable;
import streash.variables.command.AbstractCommand;

public class Max extends AbstractCommand{

	public Max() {
		super(1);
	}

	@Override
	public Variable process() {
		if (super.isProcessable()) {
			Variable[] arr = super.getArgsArray();
			if (arr[0] instanceof VarStream) {
				VarStream s = (VarStream) arr[0];
				return s.max();
			} else {
				throw new IllegalArgumentException("Arg should be a VarStream");
			}
		}
		return null;
	}

}
