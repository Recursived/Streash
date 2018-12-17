package streash.variables.command.pits;

import streash.variables.VarStream;
import streash.variables.Variable;
import streash.variables.command.AbstractCommand;
import streash.variables.stream.treatment.SliceStream;

public class Len extends AbstractCommand {

	public Len() {
		super(1, "Len");
	}

	@Override
	public Variable process() {
		if (super.isProcessable()) {
			Variable[] arr = super.getArgsArray();
			if (arr[0] instanceof SliceStream) {
				VarStream s = (VarStream) arr[0];
				return s.len();
			} else {
				throw new IllegalArgumentException("Arg should be a SliceStream (finite stream)");
			}
		}
		return null;
	}

}
