package streash.variables.command.sources;

import streash.variables.Rational;
import streash.variables.Variable;
import streash.variables.command.AbstractCommand;
import streash.variables.stream.FiboStream;


public class FiboCommand extends AbstractCommand {

	public FiboCommand() {
		super(2, "Fibo");
	}

	@Override
	public Variable process() {
		if (super.isProcessable()) {
			Variable[] arr = super.getArgsArray();
			if (arr[0] instanceof Rational && arr[1] instanceof Rational) {
				Rational r = (Rational) arr[0];
				Rational r2 = (Rational) arr[1];
				if (r.isInteger() && r2.isInteger()) {
					return new FiboStream(r.intValue(), r2.intValue());
				} else {
					throw new IllegalArgumentException("arg should be an int");
				}
			}
		}
		return null;
	}

}
