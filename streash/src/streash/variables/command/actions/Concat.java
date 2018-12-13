package streash.variables.command.actions;

import streash.variables.VarStream;
import streash.variables.Variable;
import streash.variables.command.AbstractCommand;

public class Concat extends AbstractCommand {

	public Concat() {
		super(2, "Concat");
	}

	@Override
	public Variable process() {
		if (super.isProcessable()) {
			Variable[] arr = super.getArgsArray();
			if (arr[0] instanceof VarStream && arr[1] instanceof VarStream) {
				VarStream s = (VarStream) arr[0];
				VarStream s1 = (VarStream) arr[1];
				s.concat(s1);
				return s;
			} else {
				throw new IllegalArgumentException("Args should be of type VarStream");
			}
			
		}
		return null;
	}

}
