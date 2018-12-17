package streash.variables.command.pits;

import streash.variables.VarStream;
import streash.variables.Variable;
import streash.variables.command.AbstractCommand;
import streash.variables.stream.treatment.SliceStream;

public class Print extends AbstractCommand {

	public Print() {
		super(1, "Print");
	}

	@Override
	public Variable process() {
		if (super.isProcessable()) {
			Variable[] arr = super.getArgsArray();
			if (arr[0] instanceof SliceStream) {
				VarStream s = (VarStream) arr[0];
				return s.print();
			} else {
				throw new IllegalArgumentException("arg should be of type SliceStream (finite stream)");
			}
		}
		return null;
	}

}
