package streash.variables.command.pits;

import streash.variables.VarStream;
import streash.variables.Variable;
import streash.variables.command.AbstractCommand;

public class Print extends AbstractCommand {

	public Print() {
		super(1, "Print");
	}

	@Override
	public Variable process() {
		if (super.isProcessable()) {
			Variable[] arr = super.getArgsArray();
			if (arr[0] instanceof VarStream) {
				VarStream s = (VarStream) arr[0];
				if (s.isFinite()) {
					return s.print();
				} else {
					throw new IllegalArgumentException("The stream should be finite (sliced)");
				}
			} else {
				throw new IllegalArgumentException("arg should be of type a VarStream");
			}
		}
		return null;
	}

}
