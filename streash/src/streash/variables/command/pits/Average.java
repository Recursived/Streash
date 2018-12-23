package streash.variables.command.pits;

import streash.variables.VarStream;
import streash.variables.Variable;
import streash.variables.command.AbstractCommand;

public class Average extends AbstractCommand {

	public Average() {
		super(1, "Average");
	}

	@Override
	public Variable process() {
		if (super.isProcessable()) {
			Variable[] arr = super.getArgsArray();
			if (arr[0] instanceof VarStream) {
				VarStream s = (VarStream) arr[0];
				if (s.isFinite()) {
					return s.average();
				} else {
					throw new IllegalArgumentException("The stream should be finite (sliced)");
				}
			} else {
				throw new IllegalArgumentException("Arg should be a VarStream");
			}
		}
		return null;
	}

}
