package streash.variables.command.pits;

import streash.variables.Rational;
import streash.variables.VarStream;
import streash.variables.Variable;
import streash.variables.command.AbstractCommand;
import streash.variables.stream.treatment.SliceStream;

public class Get extends AbstractCommand {

	public Get() {
		super(2, "Get");
	}

	@Override
	public Variable process() {
		if (super.isProcessable()) {
			Variable[] arr = super.getArgsArray();
			if (arr[0] instanceof SliceStream && arr[1] instanceof Rational) {
				VarStream s = (VarStream) arr[0];
				Rational r = (Rational) arr[1];
				if (r.isInteger()) {
					return s.get(r.intValue());
				} else {
					throw new IllegalStateException("Rational should be an int");
				}
			} else if (arr[0] instanceof Rational && arr[1] instanceof SliceStream) {
				VarStream s = (VarStream) arr[1];
				Rational r = (Rational) arr[0];
				if (r.isInteger()) {
					return s.get(r.intValue());
				} else {
					throw new IllegalStateException("Rational should be an int");
				}
			} else {
				throw new IllegalArgumentException("args should be rational and SliceStream");
			}
		}
		return null;
	}

}
