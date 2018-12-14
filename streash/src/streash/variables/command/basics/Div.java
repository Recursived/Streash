package streash.variables.command.basics;

import streash.variables.Rational;
import streash.variables.Variable;
import streash.variables.command.AbstractCommand;

public class Div extends AbstractCommand {

	public Div() {
		super(2, "Div");
		}

	@Override
	public Variable process() {
		if (super.isProcessable()) {
			Variable[] arr = super.getArgsArray();
			if (arr[0] instanceof Rational && arr[1] instanceof Rational) {
				Rational r = (Rational) arr[0];
				Rational r2 = (Rational) arr[1];
				return r2.div(r);
			} else {
				throw new IllegalArgumentException("Args of command should be Rationals");
			}
		}
		return null;
	}

}
