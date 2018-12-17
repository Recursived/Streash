package streash.variables.command.pits;

import streash.variables.VarStream;
import streash.variables.Variable;
import streash.variables.command.AbstractCommand;
import streash.variables.stream.treatment.SliceStream;

public class Product extends AbstractCommand {

	public Product() {
		super(1, "Product");
	}

	@Override
	public Variable process() {
		if (super.isProcessable()) {
			Variable[] arr = super.getArgsArray();
			if (arr[0] instanceof SliceStream) {
				VarStream s = (VarStream) arr[0];
				return s.product();
			} else {
				throw new IllegalArgumentException("Args should be of type SliceStream (finite stream)");
			}
		}
		return null;
	}

}
