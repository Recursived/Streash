package streash.variables.command.sources;

import streash.variables.Rational;
import streash.variables.Variable;
import streash.variables.command.AbstractCommand;
import streash.variables.stream.RandomStream;

public class RandomCommand extends AbstractCommand{

	public RandomCommand() {
		super(3, "Random");
	}

	@Override
	public Variable process() {
		if (super.isProcessable()) {
			Variable[] arr = super.getArgsArray();
			if (arr[0] instanceof Rational && arr[1] instanceof Rational && arr[2] instanceof Rational) {
				Rational r = (Rational) arr[0];
				Rational r2 = (Rational) arr[1];
				Rational r3 = (Rational) arr[2];
				if (r.isInteger() && r2.isInteger() && r3.isInteger()) {
					return new RandomStream(r.intValue(), r2.intValue(), r3.intValue());
				} else {
					throw new IllegalArgumentException("Arg for this command should be ints");
				}
			}
		}
		return null;
	}

}
