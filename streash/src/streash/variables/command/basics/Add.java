package streash.variables.command.basics;

import streash.variables.Rational;
import streash.variables.Variable;
import streash.variables.command.AbstractCommand;

public class Add extends AbstractCommand {

	public Add() {
		super(2, "Add");
	}

	@Override
	public Variable process() {
		if (super.isProcessable()) {
			Variable[] arr = super.getArgsArray();
			if (arr[0] instanceof Rational && arr[1] instanceof Rational) {
				Rational r = (Rational) arr[0];
				Rational r2 = (Rational) arr[1];
				return r.add(r2);
			} else {
				throw new IllegalArgumentException("Args of command should be Rationals");
			}
		}
		return null;
	}

}
