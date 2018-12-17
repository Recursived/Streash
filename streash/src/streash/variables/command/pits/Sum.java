package streash.variables.command.pits;

import streash.variables.VarStream;
import streash.variables.Variable;
import streash.variables.command.AbstractCommand;
import streash.variables.stream.treatment.SliceStream;

public class Sum extends AbstractCommand  {

	public Sum() {
		super(1, "Sum");
	}

	@Override
	public Variable process() {
		if (super.isProcessable()) {
			Variable[] arr = super.getArgsArray();
			if (arr[0] instanceof SliceStream) {
				VarStream s = (VarStream) arr[0];
				return s.sum();
			} else {
				throw new IllegalArgumentException("Args should be of type SliceStream (finite stream)");
			}
		}
		return null;
	}

}
