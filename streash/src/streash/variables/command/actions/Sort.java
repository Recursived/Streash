package streash.variables.command.actions;

import streash.variables.VarStream;
import streash.variables.Variable;
import streash.variables.command.AbstractCommand;
import streash.variables.stream.treatment.SortStream;

public class Sort extends AbstractCommand {

	public Sort() {
		super(1, "Sort");
	}

	@Override
	public Variable process() {
		if (super.isProcessable()) {
			Variable[] arr = super.getArgsArray();
			if (arr[0] instanceof VarStream) {
				VarStream s = (VarStream) arr[0];
				if (s.isFinite()) {
					return new SortStream(s);
				} else {
					throw new IllegalArgumentException("The stream should be finite (sliced)");
				}
			} else {
				throw new IllegalArgumentException("Arg should be of type VarStram");
			}
		}
		return null;
	}

}
