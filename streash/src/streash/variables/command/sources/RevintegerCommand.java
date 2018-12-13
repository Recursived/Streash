package streash.variables.command.sources;

import streash.variables.Rational;
import streash.variables.Variable;
import streash.variables.command.AbstractCommand;
import streash.variables.stream.RevIntegerStream;

public class RevintegerCommand extends AbstractCommand {

	public RevintegerCommand() {
		super(1, "Revinteger");
	}

	@Override
	public Variable process() {
		if (super.isProcessable()) {
			Variable[] arr = super.getArgsArray();
			if (arr[0] instanceof Rational) {
				Rational r = (Rational) arr[0];
				if (r.isInteger()) {
					return new RevIntegerStream(r.intValue());
				} else {
					throw new IllegalArgumentException("arg should be an int");
				}
			}
		}
		return null;
	}

}
