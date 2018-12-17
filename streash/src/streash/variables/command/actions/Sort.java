package streash.variables.command.actions;

import streash.variables.VarStream;
import streash.variables.Variable;
import streash.variables.command.AbstractCommand;
import streash.variables.stream.treatment.SliceStream;
import streash.variables.stream.treatment.SortStream;

public class Sort extends AbstractCommand {

	public Sort() {
		super(1, "Sort");
	}

	@Override
	public Variable process() {
		if (super.isProcessable()) {
			Variable[] arr = super.getArgsArray();
			if (arr[0] instanceof SliceStream) {
				VarStream s = (VarStream) arr[0];
				return new SortStream(s);
			} else {
				throw new IllegalArgumentException("Arg should be of type SliceStream (finite stream)");
			}
		}
		return null;
	}

}
