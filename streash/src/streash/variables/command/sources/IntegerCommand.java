package streash.variables.command.sources;

import streash.variables.Rational;
import streash.variables.Variable;
import streash.variables.command.AbstractCommand;
import streash.variables.stream.IntegerStream;

public class IntegerCommand extends AbstractCommand {

	public IntegerCommand() {
		super(1, "Integer");
	}

	@Override
	public Variable process() {
		if (super.isProcessable()) {
			Variable[] arr = super.getArgsArray();
			if (arr[0] instanceof Rational) {
				Rational r = (Rational) arr[0];
				if (r.isInteger()) {
					return new IntegerStream(r.intValue());
				} else {
					throw new IllegalArgumentException("Arg should be an int");
				}
			} else {
				throw new IllegalArgumentException("Arg should be a Rational");
			}
		}
		return null;
	}

}
