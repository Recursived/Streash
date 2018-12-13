package streash.variables.command.basics;

import streash.variables.Rational;
import streash.variables.Variable;
import streash.variables.command.AbstractCommand;

public class Sub extends AbstractCommand {

	public Sub() {
		super(2, "Sub");
	}

	@Override
	public Variable process() {
		if (super.isProcessable()) {
			Variable[] arr = super.getArgsArray();
			if (arr[0] instanceof Rational && arr[1] instanceof Rational) {
				Rational r = (Rational) arr[0];
				Rational r2 = (Rational) arr[1];
				return r.sub(r2);
			} else {
				throw new IllegalArgumentException("Args of command should be Rationals");
			}
		}
		return null;
	}

}
